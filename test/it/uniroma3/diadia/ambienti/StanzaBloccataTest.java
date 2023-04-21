package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {
	StanzaBloccata stanza;
	Attrezzo chiave;
	Stanza stanzavicina;
	Attrezzo sedia;

	@BeforeEach
	void setUp() throws Exception {
		this.stanza=new StanzaBloccata("Biblioteca", "Chiave", "sud");
		this.stanzavicina=new Stanza("Museo");
		this.stanza.impostaStanzaAdiacente("sud", stanzavicina);
		this.chiave=new Attrezzo("Chiave", 1);
		this.sedia=new Attrezzo("Sedia", 2);
	}

	@Test
	void testgetStanzaAdiacenteVuotaSenzaPassepartout() {
		assertEquals(this.stanza.getStanzaAdiacente("sud"), this.stanza);
	}
	
	@Test
	void testgetStanzaAdiacenteSoloConPassepartout() {
		this.stanza.addAttrezzo(chiave);
		assertEquals(this.stanza.getStanzaAdiacente("sud"), this.stanzavicina);
	}
	
	@Test
	void testgetStanzaAdiacentePienaSenzaPassepartout() {
		this.stanza.addAttrezzo(sedia);
		assertEquals(this.stanza.getStanzaAdiacente("sud"), this.stanza);
	}
	
	@Test
	void testgetStanzaAdiacentePienaConPassepartout() {
		this.stanza.addAttrezzo(sedia);
		this.stanza.addAttrezzo(chiave);
		assertEquals(this.stanza.getStanzaAdiacente("sud"), this.stanzavicina);
	}
	
	@Test
	void testgetDescrizione() {
		assertEquals(stanza.getDescrizione(), "La stanza è bloccata");
	}

}
