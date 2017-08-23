package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Avaliador {

	private double menorValor = Double.POSITIVE_INFINITY;
	private double maiorValor = Double.NEGATIVE_INFINITY;
	private double valorMedio = 0;
	private List<Lance> maiores;
	
	public double getMenorLance() {
		return this.menorValor;
	}
	
	public double getMaiorLance() {
		return this.maiorValor;
	}
	
	public double getLanceMedio() {
		return this.valorMedio;
	}
	
	public List<Lance> getTresMaioresLances() {
		return this.maiores;
	}
	
	public void avalia(Leilao leilao) {
		if(leilao.getLances().size() == 0) {
			throw new RuntimeException("Não é possivel avaliar um leilão sem lances!");
		}
		
		double total = 0;
		for (Lance lance : leilao.getLances()) {
			if(lance.getValor() > maiorValor)
				maiorValor = lance.getValor();
			
			if(lance.getValor() < menorValor)
				menorValor = lance.getValor();
			
			total += lance.getValor();
		}
		
		valorMedio = total / leilao.getLances().size();
		pegaOsTresMaioresLances(leilao);
	}
	
	private void pegaOsTresMaioresLances(Leilao leilao) {
		maiores = new ArrayList<Lance>(leilao.getLances());
		
		Collections.sort(maiores, new Comparator<Lance>() {
			public int compare(Lance o1, Lance o2) {
				if(o1.getValor() > o2.getValor()) return -1;
				if(o2.getValor() < o1.getValor()) return 1;
				return 0;
			}
		});
		
		maiores = maiores.subList(0, maiores.size() > 3 ? 3 : maiores.size());
	}	
}
