package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao {

	private String descricao;
	private List<Lance> lances;
	
	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}
	
	public void propoe(Lance lance) {
		if(lances.isEmpty() || ultimoUsuario(lance.getUsuario()) && podeDarLances(lance.getUsuario()) < 5) {
			lances.add(lance);	
		}
	}
	
	public void dobraUltimoLanceDado(Usuario usuario) { 
		if (usuario != null && lances.size() > 0) {
			Lance ultimoLance = ultimoLanceDoUsuario(usuario);
			propoe(new Lance(usuario, ultimoLance.getValor() * 2));
		}
	}

	private Lance ultimoLanceDoUsuario(Usuario usuario) {
		Lance ultimoLance = null;
		for (Lance lance : lances) {
			if (lance.getUsuario().equals(usuario)) {
				ultimoLance = lance;
			}
		}
		return ultimoLance;
	}

	private int podeDarLances(Usuario usuario) {
		int total = 0;
		for(Lance lance : lances) {
			if(lance.getUsuario().equals(usuario)) {
				total++;
			}	
		}
		return total;
	}

	private boolean ultimoUsuario(Usuario usuario) {
		return usuario != lances.get(lances.size() - 1).getUsuario();
	}
}
