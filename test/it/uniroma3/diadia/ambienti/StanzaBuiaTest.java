package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {
	StanzaBuia stanza;
	Attrezzo lanterna;
	Attrezzo pala;
	@BeforeEach
	void setUp() throws Exception {
		this.stanza = new StanzaBuia("stanzaBuia", "Lanterna");
		this.lanterna = new Attrezzo("Lanterna", 1);
		this.pala = new Attrezzo("Pala", 3);
	}

	@Test
	void testGetDescrizioneSenzaLanternaNoAttrezzo() {
		assertEquals(this.stanza.getDescrizione(), StanzaBuia.DESCRIZIONE_STANZA_BUIA);
	}

	@Test
	void testGetDescrizioneConLanterna() {
		this.stanza.addAttrezzo(lanterna);
		assertNotEquals(this.stanza.getDescrizione(), StanzaBuia.DESCRIZIONE_STANZA_BUIA);
	}
	
	@Test
	void testGetDescrizioneSenzaLanternaConAttrezzo() {
		this.stanza.addAttrezzo(pala);
		assertEquals(this.stanza.getDescrizione(), StanzaBuia.DESCRIZIONE_STANZA_BUIA);
	}

}
