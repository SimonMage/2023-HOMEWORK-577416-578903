package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {

	private  String attrezzoNecessario;
	
	public StanzaBuia(String nome , String attrezzoNecessario) {
		super(nome);
		this.attrezzoNecessario = attrezzoNecessario;
	}

	@Override
	public String getDescrizione() {
		String buio = new String();
		buio = "Qui c'è un buio pesto";
		if(!this.hasAttrezzo(attrezzoNecessario))
			return buio;
		return super.getDescrizione();
	}
}