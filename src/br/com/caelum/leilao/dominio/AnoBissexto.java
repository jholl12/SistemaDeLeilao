package br.com.caelum.leilao.dominio;

public class AnoBissexto {
	
	public boolean isBissexto(int ano) {
		if((ano % 4 == 0) || (ano % 4 == 0) && (ano % 100 != 0)){
			return true;
		}
		return false;
	}
}
