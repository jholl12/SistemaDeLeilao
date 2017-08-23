package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class Leilao {

	private String descricao;
	private Calendar data;
	private List<Lance> lances;
	private boolean encerrado;
	private int id;
	
	public Leilao(String descricao) {
		this(descricao, Calendar.getInstance());
	}
	
	public Leilao(String descricao, Calendar data) {
		this.descricao = descricao;
		this.data = data;
		this.lances = new ArrayList<Lance>();
	}

	public String getDescricao() {
		return descricao;
	}
	
	public Calendar getData() {
		return data;
	}
	
	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}
	
	public boolean isEncerrado() {
		return encerrado;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void encerra() {
		this.encerrado = true;
	}
	
	public void propoe(Lance lance) {
		if(lances.isEmpty() || podeDarLance(lance.getUsuario())) {
			lances.add(lance);	
		}
	}

	private boolean podeDarLance(Usuario usuario) {
		return !ultimoLanceDado().getUsuario().equals(usuario) && quantidadeDeLancesDoUsuario(usuario) < 5;
	}
	
	private int quantidadeDeLancesDoUsuario(Usuario usuario) {
		int total = 0;
		for(Lance lance : lances) {
			if(lance.getUsuario().equals(usuario)) {
				total++;
			}	
		}
		return total;
	}
	
	private Lance ultimoLanceDado() {
		return lances.get(lances.size()-1);
	}
	
	public void dobraUltimoLanceDado(Usuario usuario) { 
		if (usuario != null && lances.size() > 0) {
			Lance ultimoLance = ultimoLanceDado(usuario);
			propoe(new Lance(usuario, ultimoLance.getValor() * 2));
		}
	}

	private Lance ultimoLanceDado(Usuario usuario) {
		Lance ultimoLance =  null ;
		for(Lance lance : lances) {
			if(lance.getUsuario().equals(usuario)) {
				ultimoLance = lance;
			}
		}
		return ultimoLance;
	}
}
