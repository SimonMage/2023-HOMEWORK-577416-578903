package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {	

	Stanza s1 = new Stanza("Stanza 1");
	Stanza s2= new Stanza("Stanza 2");
	Attrezzo sword = new Attrezzo("Spada", 58);
	@Test
	public void testGetStanzaAdiacente() {
		assertNull(s1.getStanzaAdiacente("est"));
	}
	

	@Test
	public void testImpostaStanzaAdiacente() {
		s1.impostaStanzaAdiacente("est", s2);
		assertEquals(s2, s1.getStanzaAdiacente("est"));
	}
	
	@Test
	public void testAddAttrezzo() {
		
		assertTrue(s1.addAttrezzo(sword));
	}
	

}