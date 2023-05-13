package it.uniroma3.diadia.attrezzi;

import java.util.Comparator;

public class ComparatoreAttrezziOrdinatiPesoNome implements Comparator<Attrezzo> {
	@Override
	public int compare(Attrezzo a, Attrezzo b) {
		int cmp=a.getPeso()-b.getPeso();
		if (cmp==0) {
			cmp=a.getNome().compareTo(b.getNome());
		}
		return cmp;
	}
}
