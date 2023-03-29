package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {
	Stanza stanza;
	Stanza stanzaAdiacenteNord;
	Stanza stanzaAdiacenteSud;
	IOConsole IO;
	@BeforeEach
	void setUp() throws Exception {
		IO=new IOConsole();
		this.stanza=new Stanza("Stanza");
	}

	@Test
	void testStanza() {
		assertEquals(this.stanza.getNome(), "Stanza");
	}

	@Test
	void testImpostaStanzaAdiacente() {
		this.stanzaAdiacenteNord=new Stanza("StanzaAdiacenteNord");
		this.stanza.impostaStanzaAdiacente("nord", this.stanzaAdiacenteNord);
		assertEquals(this.stanzaAdiacenteNord,this.stanza.getStanzaAdiacente("nord"));
	}

	@Test
	void testGetStanzaAdiacente() {
		this.stanzaAdiacenteNord=new Stanza("StanzaAdiacenteNord");
		this.stanza.impostaStanzaAdiacente("nord", this.stanzaAdiacenteNord);
		assertEquals(this.stanzaAdiacenteNord,this.stanza.getStanzaAdiacente("nord"));
	}


	@Test
	void testGetAttrezzi() {
		Attrezzo orologio=new Attrezzo("Orologio", 1);
		Attrezzo libro=new Attrezzo("Libro", 2);
		this.stanza.addAttrezzo(orologio);
		this.stanza.addAttrezzo(libro);
		assertEquals(this.stanza.getAttrezzi()[0],orologio);
		assertEquals(this.stanza.getAttrezzi()[1],libro);
	}

	@Test
	void testAddAttrezzo() {
		Attrezzo libro=new Attrezzo("Libro", 2);
		this.stanza.addAttrezzo(libro);
		assertEquals(this.stanza.getAttrezzi()[0],libro);
	}

	@Test
	void testToString() {
		this.stanzaAdiacenteNord=new Stanza("StanzaAdiacenteNord");
		this.stanza.impostaStanzaAdiacente("nord", this.stanzaAdiacenteNord);
		this.stanzaAdiacenteSud=new Stanza("StanzaAdiacenteSud");
		this.stanza.impostaStanzaAdiacente("sud", this.stanzaAdiacenteSud);
		Attrezzo libro=new Attrezzo("Libro", 2);
		this.stanza.addAttrezzo(libro);
		Attrezzo orologio=new Attrezzo("Orologio", 1);
		this.stanza.addAttrezzo(orologio);
		String testo=this.stanza.toString().replace("\r", "").replace("\n", " - ");
		assertEquals(testo, "Stanza - Uscite:  nord sud - Attrezzi nella stanza: Libro (2kg) Orologio (1kg) ");
	}

	@Test
	void testHasAttrezzo() {
		Attrezzo orologio=new Attrezzo("Orologio", 1);
		this.stanza.addAttrezzo(orologio);
		assertTrue(this.stanza.hasAttrezzo("Orologio"));
	}

	@Test
	void testGetAttrezzo() {
		Attrezzo orologio=new Attrezzo("Orologio", 1);
		this.stanza.addAttrezzo(orologio);
		assertEquals(this.stanza.getAttrezzo("Orologio"),orologio);
	}

	@Test
	void testRemoveAttrezzo() {
		Attrezzo orologio=new Attrezzo("Orologio", 1);
		this.stanza.addAttrezzo(orologio);
		this.stanza.removeAttrezzo(orologio, IO);
		assertFalse(this.stanza.hasAttrezzo("Orologio"));
	}

	@Test
	void testGetDirezioni() {
		this.stanzaAdiacenteNord=new Stanza("StanzaAdiacenteNord");
		this.stanza.impostaStanzaAdiacente("nord", this.stanzaAdiacenteNord);
		this.stanzaAdiacenteSud=new Stanza("StanzaAdiacenteSud");
		this.stanza.impostaStanzaAdiacente("sud", this.stanzaAdiacenteSud);
		assertTrue("nord".equals(this.stanza.getDirezioni()[0]) && "sud".equals(this.stanza.getDirezioni()[1]));
	}

}
