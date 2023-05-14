package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza{
	private String attrezzoSbloccante;
	private String direzioneBloccata;
	
	public StanzaBloccata(String nome, String attrezzoSbloccante, String DirezioneBloccata) {
		super(nome);
		this.attrezzoSbloccante=attrezzoSbloccante;
		this.direzioneBloccata=DirezioneBloccata;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if(direzione.equals(this.direzioneBloccata) && !super.hasAttrezzo(attrezzoSbloccante)) {
			return this;
		}
		return super.getStanzaAdiacente(direzione);
	}
	
	@Override
	public String getDescrizione() {
		if (super.hasAttrezzo(attrezzoSbloccante)) {
			return super.getDescrizione();
		}
		return "La stanza è bloccata";
	}
}