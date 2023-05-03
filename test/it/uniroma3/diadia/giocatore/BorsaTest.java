package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest {
	Borsa borsa;
	Attrezzo orologio;
	IOConsole IO;

	@BeforeEach
	void setUp() throws Exception {
		borsa=new Borsa(20);
		orologio=new Attrezzo("Orologio", 1);
		IO=new IOConsole();
	}

	@Test
	void testBorsa() {
		assertNotNull(borsa);
	}

	@Test
	void testAddAttrezzo() {
		assertTrue(borsa.addAttrezzo(orologio));
	}

	@Test
	void testGetPesoMax() {
		assertEquals(borsa.getPesoMax(), 20);
	}

	@Test
	void testGetAttrezzo() {
		borsa.addAttrezzo(orologio);
		assertEquals(borsa.getAttrezzo("Orologio"), orologio);
	}

	@Test
	void testGetPeso() {
		assertEquals(borsa.getPeso(),0);
		borsa.addAttrezzo(orologio);
		assertEquals(borsa.getPeso(),1);
	}

	@Test
	void testIsEmpty() {
		assertTrue(borsa.isEmpty());
	}

	@Test
	void testHasAttrezzo() {
		assertFalse(borsa.hasAttrezzo("Orologio"));
		borsa.addAttrezzo(orologio);
		assertTrue(borsa.hasAttrezzo("Orologio"));
	}

	@Test
	void testRemoveAttrezzo() {
		borsa.addAttrezzo(orologio);
		assertNotNull(borsa.removeAttrezzo("Orologio"));
	}

	@Test
	void testToString() {
		assertEquals(borsa.toString(), "Borsa vuota");
		borsa.addAttrezzo(orologio);
		assertEquals(borsa.toString(), "Contenuto borsa (1kg/20kg): Orologio (1kg) ");
	}

}
