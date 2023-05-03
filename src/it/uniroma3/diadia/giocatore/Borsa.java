package it.uniroma3.diadia.giocatore;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
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
	}