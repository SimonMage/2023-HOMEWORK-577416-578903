package it.uniroma3.diadia.giocatore;

import java.util.Comparator;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComparatoreAttrezziOrdinatiPerNome implements Comparator<Attrezzo>{
	@Override
    public int compare(Attrezzo a, Attrezzo b) {
		return a.getNome().compareTo(b.getNome());
	}
}
