package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;

class PartitaTest {
	
	private Partita partita;
	@BeforeEach
	void setUp() throws Exception {
		this.partita=new Partita();
	}

	@Test
	void testPartita() {
		assertFalse(this.partita.isFinita());
	}

	@Test
	void testGetStanzaVincente() {
		assertEquals(this.partita.getStanzaVincente(), this.partita.getLabirinto().stanzaVincente);
	}

	@Test
	void testSetStanzaCorrente() {
		Stanza stanzatest=this.partita.getStanzaCorrente().getStanzaAdiacente("sud");
		this.partita.setStanzaCorrente(stanzatest);
		assertEquals(this.partita.getStanzaCorrente(), stanzatest);
	}

	@Test
	void testGetStanzaCorrente() {
		Stanza stanzatest=this.partita.getStanzaCorrente();
		this.partita.setStanzaCorrente(stanzatest.getStanzaAdiacente("sud"));
		assertNotEquals(this.partita.getStanzaCorrente(), stanzatest);
	}

	@Test
	void testVinta() {
		this.partita.setStanzaCorrente(this.partita.getStanzaVincente());
		assertTrue(this.partita.vinta());
	}

	@Test
	void testIsFinita() {
		this.partita.setStanzaCorrente(this.partita.getStanzaVincente());
		assertTrue(this.partita.isFinita());
	}

	@Test
	void testSetFinita() {
		this.partita.setFinita();
		assertTrue(this.partita.isFinita());
	}

}
