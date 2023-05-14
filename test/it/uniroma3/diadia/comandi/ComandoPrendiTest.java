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

class ComandoPrendiTest {
	Comando comando;
	Partita partita;
	Labirinto labirinto;
	private IO io;
	Attrezzo orologio;
	Attrezzo libro;
	
	@BeforeEach
	void setUp() throws Exception {

		labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("libreria", 16)
				.addStanza("Biblioteca")
				.addAttrezzo("Orologio", 1)
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.addStanzaVincente("N11")
				.addAdiacenza("Biblioteca", "N11", "sud")
				.addAttrezzo("Libro", 1)
				.getLabirinto();
		this.comando=new ComandoPrendi();
		this.partita=new Partita(labirinto);
		orologio=new Attrezzo("Orologio", 1);
		libro=new Attrezzo("Libro", 1);
		io=new IOConsole();
		comando.setIo(io);
	}

	@Test
	void testEseguiAttrezzoPesante() {
		comando.setParametro("libreria");
		comando.esegui(partita);
		assertNull(partita.getGiocatore().getBorsa().getAttrezzo("libreria"));
	}
	
	@Test
	void testEseguiAttrezzoLeggero() {
		partita.setStanzaCorrente(partita.getStanzaCorrente().getStanzaAdiacente("nord"));
		comando.setParametro("Orologio");
		comando.esegui(partita);
		assertEquals(orologio, partita.getGiocatore().getBorsa().getAttrezzo("Orologio"));
		partita.setStanzaCorrente(partita.getStanzaCorrente().getStanzaAdiacente("sud"));
		comando.setParametro("Libro");
		comando.esegui(partita);
		assertEquals(libro, partita.getGiocatore().getBorsa().getAttrezzo("Libro"));
	}

}
