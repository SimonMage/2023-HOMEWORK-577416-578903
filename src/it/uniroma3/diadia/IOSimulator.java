package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.List;

public class IOSimulator implements IO{
	private List<String> righeLette;
	int indiceRigheLette;
	private List<String> messaggiProdotti;
	int indiceMsgProdotti;
	
	public IOSimulator(List<String> righeDaLeggere) {
		this.righeLette = righeDaLeggere;
		this.indiceRigheLette = 0;
		this.indiceMsgProdotti = 0;
		this.messaggiProdotti = new ArrayList<String>();
	}
	
	@Override
	public void mostraMessaggio(String msg) {
		this.messaggiProdotti.add(this.indiceMsgProdotti, msg);
		this.indiceMsgProdotti++;
	}
	
	@Override
	public String leggiRiga() {
		String riga = null;
		riga = this.righeLette.get(indiceRigheLette);
		this.indiceRigheLette++;
		return riga;
	}
	
	public List<String> getMessaggiProdotti() {
		return this.messaggiProdotti;
	}
	
	public List<String> getRigheLette() {
		return this.righeLette;
	}
	
}
