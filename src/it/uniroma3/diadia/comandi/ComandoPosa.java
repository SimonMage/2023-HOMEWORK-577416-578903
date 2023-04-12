package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando {
	private IO io;
	private String nomeAttrezzo;
	private final static String NOME = "posa";


	@Override
	public void esegui(Partita partita) {
		if(!partita.getGiocatore().getBorsa().isEmpty()) {				
			if(partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo)) {
				Attrezzo a = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
				partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo, io);
				boolean esito = partita.stanzaCorrente.addAttrezzo(a);
				if (esito) {
					io.mostraMessaggio("Attrezzo rimosso dalla borsa");	
				}else {
					io.mostraMessaggio("Attrezzo non rimosso dalla borsa");						
				}
			}else {
				io.mostraMessaggio("Non esiste quell'attrezzo nella borsa");				
			}
			
		}else {
			io.mostraMessaggio("Non ci sono attrezzi nella borsa");
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
