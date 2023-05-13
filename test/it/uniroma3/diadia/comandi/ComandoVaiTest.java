package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

class ComandoVaiTest {
	Comando comando;
	Partita partita;
	Labirinto labirinto;
	@BeforeEach
	void setUp() throws Exception {
		labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("martello", 3)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.getLabirinto();
		this.comando=new ComandoVai();
		this.partita=new Partita(labirinto);
	}
	
	@Test
	void testEseguiDirezioneInesistente() {
		comando.setParametro("sud");
		comando.esegui(partita);
		assertEquals("Atrio", labirinto.getStanzaCorrente().getNome());
	}
	
	@Test
	void testEseguiDirezioneEsistente() {
		comando.setParametro("nord");
		comando.esegui(partita);
		assertEquals("Biblioteca", labirinto.getStanzaCorrente().getNome());
	}
	
	@Test
	void testEseguiParametroNonSpecificato() {
		comando.esegui(partita);
		assertEquals("Atrio", labirinto.getStanzaCorrente().getNome());
	}

}
