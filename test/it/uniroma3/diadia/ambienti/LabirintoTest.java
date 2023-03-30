package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LabirintoTest {
	Labirinto labirinto;
	
	@BeforeEach
	void setUp() throws Exception {
		labirinto=new Labirinto();
	}

	@Test
	void testLabirinto() {
		assertNotNull(labirinto.atrio);
	}

}
