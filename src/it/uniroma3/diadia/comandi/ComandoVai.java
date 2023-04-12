package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando {
	private String direzione;
	private final static String NOME = "vai";
	private IO io;
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		if (this.direzione==null) {
			System.out.println("Dove vuoi andare?\nDevi specificare una direzione");
			return;
		}
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
		if (prossimaStanza==null) {
			System.out.println("Direzione inesistene");
			return;
		}
		partita.setStanzaCorrente(stanzaCorrente);
		System.out.println(partita.getStanzaCorrente().getNome());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);		
	}
	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}
	@Override
	public String getNome() {
		return NOME;
	}
	@Override
	public String getParametro() {
		return this.direzione;
	}
	@Override
	public boolean sconosciuto() {
        return (NOME == null);
	}
	@Override
	public void setIo(IO io) {
		this.io = io;
		
	}
}
