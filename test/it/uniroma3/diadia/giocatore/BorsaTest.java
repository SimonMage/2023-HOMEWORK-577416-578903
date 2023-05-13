package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest {
	Borsa borsa;
	Attrezzo orologio;
	Attrezzo martello;
	Attrezzo palla;
	Attrezzo biglia;
	Attrezzo chiave;
	Attrezzo libro;
	IOConsole IO;
	
	@BeforeEach
	void setUp() throws Exception {
		borsa=new Borsa(20);
		orologio=new Attrezzo("Orologio", 1);
		martello=new Attrezzo("Martello", 3);
		palla=new Attrezzo("Palla", 2);
		biglia=new Attrezzo("Biglia", 1);
		chiave=new Attrezzo("Chiave", 2);
		libro=new Attrezzo("Libro", 2);
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
	
	@Test
	void testGetContenutoOrdinatoPerPeso() {
		borsa.addAttrezzo(orologio);
		borsa.addAttrezzo(martello);
		borsa.addAttrezzo(palla);
		List<Attrezzo> listaOrdinata = this.borsa.getContenutoOrdinatoPerPeso();
		assertEquals("Orologio", listaOrdinata.get(0).getNome());
		assertEquals("Palla", listaOrdinata.get(1).getNome());
		assertEquals("Martello", listaOrdinata.get(2).getNome());
	}
	
	@Test
	void testGetContenutoOrdinatoPerNome() {
		borsa.addAttrezzo(palla);
		borsa.addAttrezzo(orologio);
		borsa.addAttrezzo(martello);
		SortedSet<Attrezzo> setOrdinato = this.borsa.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> i = setOrdinato.iterator();
		assertEquals(martello, i.next());
		assertEquals(orologio, i.next());
		assertEquals(palla, i.next());
	}
	
	@Test
	void testGetContenutoRaggruppatoPerPeso() {
		borsa.addAttrezzo(palla);
		borsa.addAttrezzo(orologio);
		borsa.addAttrezzo(martello);
		borsa.addAttrezzo(biglia);
		borsa.addAttrezzo(chiave);
		borsa.addAttrezzo(libro);
		
		orologio=new Attrezzo("Orologio", 1);
		martello=new Attrezzo("Martello", 3);
		palla=new Attrezzo("Palla", 2);
		biglia=new Attrezzo("Biglia", 1);
		chiave=new Attrezzo("Chiave", 2);
		libro=new Attrezzo("Libro", 2);
		
		Map<Integer,Set<Attrezzo>> mappaRaggruppata=this.borsa.getContenutoRaggruppatoPerPeso();
		//Peso 1 kg: orologio, biglia
		Attrezzo[] attrezzi= mappaRaggruppata.get(orologio.getPeso()).toArray(new Attrezzo[mappaRaggruppata.get(orologio.getPeso()).size()]);
		assertEquals(biglia, attrezzi[0]);
		assertEquals(orologio, attrezzi[1]);
		//Peso 2 kg: palla, chiave, libro
		Attrezzo[] attrezzi2= mappaRaggruppata.get(palla.getPeso()).toArray(new Attrezzo[mappaRaggruppata.get(palla.getPeso()).size()]);
		assertEquals(libro, attrezzi2[0]);
		assertEquals(chiave, attrezzi2[1]);
		assertEquals(palla, attrezzi2[2]);
		//Peso 3 kg: martello
		Attrezzo[] attrezzi3= mappaRaggruppata.get(martello.getPeso()).toArray(new Attrezzo[mappaRaggruppata.get(martello.getPeso()).size()]);
		assertEquals(martello, attrezzi3[0]);
	}
}
