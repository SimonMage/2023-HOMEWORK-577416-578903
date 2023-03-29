package it.uniroma3.diadia;
import java.util.Scanner;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.IOConsole;
/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	private Partita partita;

	public DiaDia() {
		this.partita = new Partita();
	}

	public void gioca(IOConsole IO) {
		String istruzione; 

		IO.mostraMessaggio(MESSAGGIO_BENVENUTO);		
		do		
			istruzione = IO.leggiRiga();
		while (!processaIstruzione(istruzione, IO));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione, IOConsole IO) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome()==null) {
			IO.mostraMessaggio("Comando sconosciuto");
		} else
			if (comandoDaEseguire.getNome().equals("fine")) {
				this.fine(IO); 
				return true;
			} else if (comandoDaEseguire.getNome().equals("vai")) {
				this.vai(comandoDaEseguire.getParametro(), IO);
		
			} else if (comandoDaEseguire.getNome().equals("prendi")) {
				this.prendi(comandoDaEseguire.getParametro(), IO);
			
			} else if (comandoDaEseguire.getNome().equals("posa")) {
				this.posa(comandoDaEseguire.getParametro(), IO);
		
			} else if (comandoDaEseguire.getNome().equals("aiuto"))
				this.aiuto(IO);
			else
				IO.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			IO.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto(IOConsole IO) {
		for(int i=0; i< elencoComandi.length; i++) 
			IO.mostraMessaggio(elencoComandi[i]+" ");
		IO.mostraMessaggio("");
	}

	private void prendi(String nomeAttrezzo, IOConsole IO) {	
		if(this.partita.labirintoDiGioco.stanzaCorrente.getAttrezzi().length != 0) {				
			if(this.partita.labirintoDiGioco.stanzaCorrente.hasAttrezzo(nomeAttrezzo)) {
				Attrezzo a = this.partita.labirintoDiGioco.stanzaCorrente.getAttrezzo(nomeAttrezzo);
				this.partita.labirintoDiGioco.stanzaCorrente.removeAttrezzo(a, IO);
				boolean esito = this.partita.giocatore.getBorsa().addAttrezzo(a);
				if (esito) {
					IO.mostraMessaggio("Attrezzo aggiunto alla borsa");	
				}else {
					IO.mostraMessaggio("Attrezzo non aggiunto alla borsa");						
				}
			}else {
				IO.mostraMessaggio("Non esiste quell'attrezzo nella stanza");				
			}
			
		}else {
			IO.mostraMessaggio("Non ci sono attrezzi in questa stanza");
		}
	}	
	
	private void posa(String nomeAttrezzo, IOConsole IO) {
		if(!this.partita.giocatore.getBorsa().isEmpty()) {				
			if(this.partita.giocatore.getBorsa().hasAttrezzo(nomeAttrezzo)) {
				Attrezzo a = this.partita.giocatore.getBorsa().getAttrezzo(nomeAttrezzo);
				this.partita.giocatore.getBorsa().removeAttrezzo(nomeAttrezzo, IO);
				boolean esito = this.partita.labirintoDiGioco.stanzaCorrente.addAttrezzo(a);
				if (esito) {
					IO.mostraMessaggio("Attrezzo rimosso dalla borsa");	
				}else {
					IO.mostraMessaggio("Attrezzo non rimosso dalla borsa");						
				}
			}else {
				IO.mostraMessaggio("Non esiste quell'attrezzo nella borsa");				
			}
			
		}else {
			IO.mostraMessaggio("Non ci sono attrezzi nella borsa");
		}
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione, IOConsole IO) {
		if(direzione==null)
			IO.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			IO.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.giocatore.getCfu();
			this.partita.giocatore.setCfu(cfu--);
		}
		IO.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine(IOConsole IO) {
		IO.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		IOConsole IO=new IOConsole();
		DiaDia gioco = new DiaDia();
		gioco.gioca(IO);
	}
}