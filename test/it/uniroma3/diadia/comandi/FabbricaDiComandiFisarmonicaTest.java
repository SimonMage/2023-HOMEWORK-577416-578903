package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;

class FabbricaDiComandiFisarmonicaTest {
	IO io;
	FabbricaDiComandiFisarmonica factory;
	@BeforeEach
	void setUp() throws Exception {
		io = new IOConsole();
		factory = new FabbricaDiComandiFisarmonica(io);
	}

	@Test
	void testCostruisciComandoNonValido() {
		assertEquals(new ComandoNonValido().getNome(), factory.costruisciComando("").getNome());
	}
	
	@Test
	void testCostruisciComandoVai() {
		assertEquals(new ComandoVai().getNome(), factory.costruisciComando("vai").getNome());
	}
	
	@Test
	void testCostruisciComandoVaiConParametro() {
		assertEquals(new ComandoVai().getNome(), factory.costruisciComando("vai nord").getNome());
		assertEquals("nord", factory.costruisciComando("vai nord").getParametro());
	}

	@Test
	void testCostruisciComandoPrendi() {
		assertEquals(new ComandoPrendi().getNome(), factory.costruisciComando("prendi").getNome());
	}

	@Test
	void testCostruisciComandoPosa() {
		assertEquals(new ComandoPosa().getNome(), factory.costruisciComando("posa").getNome());
	}
	
	@Test
	void testCostruisciComandoAiuto() {
		assertEquals(new ComandoAiuto().getNome(), factory.costruisciComando("aiuto").getNome());
	}

	@Test
	void testCostruisciComandoFine() {
		assertEquals(new ComandoFine().getNome(), factory.costruisciComando("fine").getNome());
	}
	
	@Test
	void testCostruisciComandoGuarda() {
		assertEquals(new ComandoGuarda().getNome(), factory.costruisciComando("guarda").getNome());
	}

	@Test
	void testCostruisciComandoGuardaConSpazio() {
		assertEquals(new ComandoGuarda().getNome(), factory.costruisciComando("guarda ").getNome());
	}
}
