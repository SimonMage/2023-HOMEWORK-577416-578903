package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {
	StanzaBloccata stanza;
	Attrezzo chiave;
	Stanza stanzavicina;

	@BeforeEach
	void setUp() throws Exception {
		stanza=new StanzaBloccata("Biblioteca", "Chiave", "sud");
		this.stanzavicina= new Stanza("Museo");
		this.stanza.impostaStanzaAdiacente("sud", stanzavicina);
	}

	@Test
	void testgetStanzaAdiacente() {
		fail("Not yet implemented");
	}
	
	@Test
	void testgetDescrizione() {
		assertEquals(stanza.getDescrizione(), "La stanza è bloccata");
	}

}
