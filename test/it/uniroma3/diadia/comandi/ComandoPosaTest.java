package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPosaTest {
	Comando comando;
	Partita partita;
	Labirinto labirinto;
	Attrezzo orologio;
	private IO io;
	
	@BeforeEach
	void setUp() throws Exception {
		orologio=new Attrezzo("Orologio", 1);

		labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("martello", 3)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.getLabirinto();
		this.comando=new ComandoPosa();
		this.partita=new Partita(labirinto);
		partita.getGiocatore().getBorsa().addAttrezzo(orologio);
		io=new IOConsole();
		comando.setIo(io);
	}


	@Test
	void testEseguiStanzaVuota() {
		comando.setParametro("Orologio");
		comando.esegui(partita);
		assertNull(partita.getGiocatore().getBorsa().getAttrezzo("Orologio"));
		assertEquals(orologio, partita.getStanzaCorrente().getAttrezzo("Orologio"));
	}
	
	@Test
	void testEseguiStanzaPiena() {
		for(int i=0;i<9;i++) {
			partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("orologio"+i, 1));
			this.comando=new ComandoPosa();
			comando.setParametro("orologio"+i);
			comando.setIo(io);
			comando.esegui(partita);
		}
		this.comando=new ComandoPosa();
		comando.setParametro("Orologio");
		comando.setIo(io);
		comando.esegui(partita);
		
		assertNotNull(partita.getGiocatore().getBorsa().getAttrezzo("Orologio"));
		assertNull(partita.getStanzaCorrente().getAttrezzo("Orologio"));
	}

}
