
import java.util.Scanner;

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

	public void gioca() {
		String istruzione; 
		Scanner scannerDiLinee;

		System.out.println(MESSAGGIO_BENVENUTO);
		scannerDiLinee = new Scanner(System.in);		
		do		
			istruzione = scannerDiLinee.nextLine();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome()==null) {
			System.out.println("Comando sconosciuto");
		} else
			if (comandoDaEseguire.getNome().equals("fine")) {
				this.fine(); 
				return true;
			} else if (comandoDaEseguire.getNome().equals("vai")) {
				this.vai(comandoDaEseguire.getParametro());
		
			} else if (comandoDaEseguire.getNome().equals("prendi")) {
				this.prendi(comandoDaEseguire.getParametro());
			
			} else if (comandoDaEseguire.getNome().equals("posa")) {
				this.posa(comandoDaEseguire.getParametro());
		
			} else if (comandoDaEseguire.getNome().equals("aiuto"))
				this.aiuto();
			else
				System.out.println("Comando sconosciuto");
		if (this.partita.vinta()) {
			System.out.println("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			System.out.print(elencoComandi[i]+" ");
		System.out.println();
	}

	private void prendi(String nomeAttrezzo) {
		/*if(direzione==null)
			System.out.println("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			System.out.println("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.giocatore.getCfu();
			this.partita.giocatore.setCfu(cfu--);
		}
		System.out.println(partita.getStanzaCorrente().getDescrizione());*/
		//System.out.println("Ho preso l'oggetto: " + nomeAttrezzo);		
		if(this.partita.labirintoDiGioco.stanzaCorrente.getAttrezzi().length != 0) {				
			if(this.partita.labirintoDiGioco.stanzaCorrente.hasAttrezzo(nomeAttrezzo)) {
				Attrezzo a = this.partita.labirintoDiGioco.stanzaCorrente.getAttrezzo(nomeAttrezzo);
				this.partita.labirintoDiGioco.stanzaCorrente.removeAttrezzo(a);
				boolean esito = this.partita.giocatore.getBorsa().addAttrezzo(a);
				if (esito) {
					System.out.println("Attrezzo aggiunto alla borsa");	
				}else {
					System.out.println("Attrezzo non aggiunto alla borsa");						
				}
			}else {
				System.out.println("Non esiste quell'attrezzo nella stanza");				
			}
			
		}else {
			System.out.println("Non ci sono attrezzi in questa stanza");
		}
	}	
	
	private void posa(String nomeAttrezzo) {
		if(!this.partita.giocatore.getBorsa().isEmpty()) {				
			if(this.partita.giocatore.getBorsa().hasAttrezzo(nomeAttrezzo)) {
				Attrezzo a = this.partita.giocatore.getBorsa().getAttrezzo(nomeAttrezzo);
				this.partita.giocatore.getBorsa().removeAttrezzo(nomeAttrezzo);
				boolean esito = this.partita.labirintoDiGioco.stanzaCorrente.addAttrezzo(a);
				if (esito) {
					System.out.println("Attrezzo rimosso dalla borsa");	
				}else {
					System.out.println("Attrezzo non rimosso dalla borsa");						
				}
			}else {
				System.out.println("Non esiste quell'attrezzo nella borsa");				
			}
			
		}else {
			System.out.println("Non ci sono attrezzi nella borsa");
		}
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			System.out.println("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			System.out.println("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.giocatore.getCfu();
			this.partita.giocatore.setCfu(cfu--);
		}
		System.out.println(partita.getStanzaCorrente().getDescrizione());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		System.out.println("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}