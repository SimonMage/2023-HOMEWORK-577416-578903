package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezziOrdinatiNome;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezziOrdinatiPesoNome;

public class Borsa{
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Map<String,Attrezzo> attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;
	private int peso = 0;
	
	public Borsa() { 
		this(DEFAULT_PESO_MAX_BORSA);	
	}
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new TreeMap<>(); 
		this.numeroAttrezzi = 0;
	}
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		this.peso += attrezzo.getPeso();
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		this.numeroAttrezzi++;
		return true;
	}
	public int getPesoMax() {
		return pesoMax;
	}
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
			if (this.attrezzi.get(nomeAttrezzo)!=null) {
				a = attrezzi.get(nomeAttrezzo);
			}
		return a;
	}
	public int getPeso() {
		return this.peso;
	}
	
	public boolean getPesoRimanente(Attrezzo a) {
		if(a != null && this.getPesoMax()-this.getPeso()>=a.getPeso())
			return true;
		return false; 
	}
	
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		if(nomeAttrezzo!=null){
			a = attrezzi.remove(nomeAttrezzo);
		}
		return a;
	}
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			Collection<Attrezzo> arrAttrezzi = this.attrezzi.values();
			for (Attrezzo attrezzo : arrAttrezzi) {
				s.append(attrezzo.toString()+" ");
			}
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso() {
		List<Attrezzo> listaOrdinata=new ArrayList<>(this.attrezzi.values());
		Collections.sort(listaOrdinata);
		return listaOrdinata;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
		SortedSet<Attrezzo> setOrdinato=new TreeSet<>(new ComparatoreAttrezziOrdinatiNome());
		setOrdinato.addAll(this.attrezzi.values());
		return setOrdinato;
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
		SortedSet<Attrezzo> setOrdinato=new TreeSet<>(new ComparatoreAttrezziOrdinatiPesoNome());
		setOrdinato.addAll(this.attrezzi.values());
		return setOrdinato;
	}
	
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		Set<Attrezzo> setAttrezzi=new TreeSet<>(this.attrezzi.values());
		Iterator<Attrezzo> i = setAttrezzi.iterator();
		Map<Integer,Set<Attrezzo>> mappaRaggruppata=new HashMap<>();
		Attrezzo a;
		while (i.hasNext()) {
			a=i.next();
			if (mappaRaggruppata.containsKey(a.getPeso())) {
				mappaRaggruppata.get(a.getPeso()).add(a);
			}
			else {
				Set<Attrezzo> s=new HashSet<Attrezzo>();
				s.add(a);
				mappaRaggruppata.put(a.getPeso(), s);
			}
		}
		return mappaRaggruppata;
	}
	}