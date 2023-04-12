package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {

	private IO io;
	private String nomeAttrezzo;
	private final static String NOME = "prendi";

	
	@Override
	public void esegui(Partita partita) {
		if(partita.stanzaCorrente.getAttrezzi().length != 0) {				
			if(partita.stanzaCorrente.hasAttrezzo(nomeAttrezzo)) {
				Attrezzo a = partita.stanzaCorrente.getAttrezzo(nomeAttrezzo);
				partita.stanzaCorrente.removeAttrezzo(a, io);
				boolean esito = partita.getGiocatore().getBorsa().addAttrezzo(a);
				if (esito) {
					io.mostraMessaggio("Attrezzo aggiunto alla borsa");	
				}else {
					io.mostraMessaggio("Attrezzo non aggiunto alla borsa");						
				}
			}else {
				io.mostraMessaggio("Non esiste quell'attrezzo nella stanza");				
			}
			
		}else {
			io.mostraMessaggio("Non ci sono attrezzi in questa stanza");
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo  = parametro;

	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

	@Override
	public void setIo(IO io) {
		this.io = io;
	}
	
	@Override
	public String getNome() {
		return NOME;
	}

	@Override
	public boolean sconosciuto() {
        return (NOME == null);
	}

}
