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
	Attrezzo giornale;
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
		giornale=new Attrezzo("Giornale", 1);
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
		assertEquals(orologio, listaOrdinata.get(0));
		assertEquals(palla, listaOrdinata.get(1));
		assertEquals(martello, listaOrdinata.get(2));
	}
	
	@Test
	void testGetContenutoOrdinatoPerPesoConDueOggettiPesoUguale() {
		borsa.addAttrezzo(orologio);
		borsa.addAttrezzo(biglia);
		List<Attrezzo> listaOrdinata = this.borsa.getContenutoOrdinatoPerPeso();
		assertEquals(biglia, listaOrdinata.get(0));
		assertEquals(orologio, listaOrdinata.get(1));	
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
		
		Map<Integer,Set<Attrezzo>> mappaRaggruppata=this.borsa.getContenutoRaggruppatoPerPeso();
		//Peso 1 kg: orologio, biglia
		Set<Attrezzo> s=mappaRaggruppata.get(1);
		assertTrue(s.contains(orologio));
		assertTrue(s.contains(biglia));
		assertFalse(s.contains(libro));
		//Peso 2 kg: palla, chiave, libro
		s=mappaRaggruppata.get(2);
		assertTrue(s.contains(palla));
		assertTrue(s.contains(chiave));
		assertTrue(s.contains(libro));
		assertFalse(s.contains(biglia));
		//Peso 3 kg: martello
		s=mappaRaggruppata.get(3);
		assertTrue(s.contains(martello));
		assertFalse(s.contains(libro));
	}
	
	@Test
	void testGetSortedSetOrdinatoPerPeso() {
		borsa.addAttrezzo(palla); //peso 2
		borsa.addAttrezzo(orologio); //peso 1
		borsa.addAttrezzo(martello); //peso 3
		borsa.addAttrezzo(biglia); //peso 1
		borsa.addAttrezzo(chiave); //peso 2
		borsa.addAttrezzo(libro); //peso 2
		borsa.addAttrezzo(giornale); //peso 1
		SortedSet<Attrezzo> setOrdinato = this.borsa.getSortedSetOrdinatoPerPeso();
		Iterator<Attrezzo> i = setOrdinato.iterator();
		assertEquals(biglia, i.next()); //peso 1
		assertEquals(giornale, i.next()); //peso 1
		assertEquals(orologio, i.next()); //peso 1
		assertEquals(chiave, i.next()); //peso 2
		assertEquals(libro, i.next()); //peso 2
		assertEquals(palla, i.next()); //peso 2
		assertEquals(martello, i.next()); //peso 3
		
	}
}
