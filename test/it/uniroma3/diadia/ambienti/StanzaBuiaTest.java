package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {
	StanzaBuia stanza;
	Attrezzo torcia;

	@BeforeEach
	void setUp() throws Exception {
		this.torcia=new Attrezzo("Torcia", 1);
		this.stanza=new StanzaBuia("Stanza buia", "Torcia");
	}

	@Test
	void testgetDescrizioneSenzaTorcia() {
		assertEquals(this.stanza.getDescrizione(), "");
	}
	@Test
	void testgetDescrizioneConTorcia() {
		this.stanza.addAttrezzo(torcia);
		assertEquals(this.stanza.getDescrizione(), "");
	}

}
