package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GiocatoreTest {
	Giocatore giocatore;
	@BeforeEach
	void setUp() throws Exception {
		giocatore = new Giocatore();
	}

	@Test
	void testGiocatore() {
		assertNotNull(giocatore.getBorsa());
	}

	@Test
	void testGetCfu() {
		assertEquals(giocatore.getCfu(), 20);
	}

	@Test
	void testSetCfu() {
		giocatore.setCfu(10);
		assertEquals(giocatore.getCfu(), 10);
	}

	@Test
	void testGetBorsa() {
		assertNotNull(giocatore.getBorsa());
	}

}
