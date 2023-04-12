package it.uniroma3.diadia;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;
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

	private Partita partita;
	
	private IO io;

	public DiaDia(IO console) {
		this.io = console;
		this.partita = new Partita();
	}

	public void gioca() {
		String istruzione; 
//		Scanner scannerDiLinee;
		io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do {
			istruzione = io.leggiRiga();

		}while (!processaIstruzione(istruzione) );

	}    


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire;
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica(this.io);
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.vinta())
		io.mostraMessaggio("Hai vinto!");
		if (!this.partita.giocatoreIsVivo())
			io.mostraMessaggio("Hai esaurito i CFU...");
		return this.partita.isFinita();
		}
	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
/*	private void aiuto(IO IO) {
		for(int i=0; i< elencoComandi.length; i++) 
			IO.mostraMessaggio(elencoComandi[i]+" ");
		IO.mostraMessaggio("");
	}

	private void prendi(String nomeAttrezzo, IO IO) {	
		if(this.partita.stanzaCorrente.getAttrezzi().length != 0) {				
			if(this.partita.stanzaCorrente.hasAttrezzo(nomeAttrezzo)) {
				Attrezzo a = this.partita.stanzaCorrente.getAttrezzo(nomeAttrezzo);
				this.partita.stanzaCorrente.removeAttrezzo(a, IO);
				boolean esito = this.partita.getGiocatore().getBorsa().addAttrezzo(a);
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
	
	private void posa(String nomeAttrezzo, IO IO) {
		if(!this.partita.getGiocatore().getBorsa().isEmpty()) {				
			if(this.partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo)) {
				Attrezzo a = this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
				this.partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo, IO);
				boolean esito = this.partita.stanzaCorrente.addAttrezzo(a);
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
/*	private void vai(String direzione, IO IO) {
		if(direzione==null)
			IO.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			IO.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu--);
		}
		IO.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	/**
	 * Comando "Fine".
	 */
/*	private void fine(IO IO) {
		IO.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}
*/
	public static void main(String[] argc) {
		IO console = new IOConsole();
		DiaDia gioco = new DiaDia(console);
		gioco.gioca();
	}
}