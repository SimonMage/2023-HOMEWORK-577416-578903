


/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	//static final private int CFU_INIZIALI = 20;

	private boolean finita;
	//private int cfu;
	public Labirinto labirintoDiGioco;
	
	Giocatore giocatore = new Giocatore();
	
	public Partita(){
		this.labirintoDiGioco = new Labirinto();
		this.finita = false;
		//this.cfu = CFU_INIZIALI;
	}

	public Stanza getStanzaVincente() {
		return labirintoDiGioco.stanzaVincente;
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		labirintoDiGioco.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return labirintoDiGioco.stanzaCorrente;
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente()== this.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

	/*public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}	*/
}
