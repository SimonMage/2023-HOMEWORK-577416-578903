package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {
	StanzaMagica stanza;
	Attrezzo libro;
	
	@BeforeEach
	void setUp() throws Exception {
		this.stanza=new StanzaMagica("stanza magica");
		this.libro=new Attrezzo("libro", 1);
		
	}
	
	@Test
	void testaddAttrezzoSenzaMagia() {
		this.stanza.addAttrezzo(libro);
		assertEquals(this.stanza.getAttrezzo("libro").toString(), "libro (1kg)");
	}

	@Test
	void testaddAttrezzoConMagia() {
		for (int i = 0; i < StanzaMagica.SOGLIA_MAGICA_DEFAULT; i++) {
			this.stanza.addAttrezzo(new Attrezzo("libro"+i, 1));
		}
		this.stanza.addAttrezzo(libro);
		assertEquals("orbil (2kg)", this.stanza.getAttrezzo("orbil").toString());
	}
}
