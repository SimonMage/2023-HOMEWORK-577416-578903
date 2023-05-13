package it.uniroma3.diadia.attrezzi;

import java.util.Comparator;

public class ComparatoreAttrezziOrdinatiNome implements Comparator<Attrezzo>{
	@Override
    public int compare(Attrezzo a, Attrezzo b) {
		return a.getNome().compareTo(b.getNome());
	}
}
