package br.com.caelum.leilao.builder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

/**
 * Classe respons�vel para facilitar a cria��o de leil�es utilizada Data
 * Builders
 * 
 * @since 21/08/2017 - 22h46
 * @author Jhonata Santos
 * @version 1.0
 */
public class CriadorDeLeilao {

	/**
	 * Objetos utilizados na classe
	 */
	private String descricao;
	private Calendar data;
	private boolean encerrado;
	private List<Lance> lances;

	public CriadorDeLeilao() {
		this.data = Calendar.getInstance();
		this.lances = new ArrayList<Lance>();
	}
	
	/**
	 * Insere a descri��o do produto leiloado
	 * 
	 * @param descricao
	 * @return um objeto do tipo CriadorDeLeilao
	 */
	public CriadorDeLeilao para(String descricao) {
		this.descricao = descricao;
		return this;
	}
	
	/**
	 * Insere a data de leilao do produto
	 * 
	 * @param data
	 * @return um objeto do tipo CriadorDeLeilao
	 */
	public CriadorDeLeilao naData(Calendar data) {
		this.data = data;
		return this;
	}

	/**
	 * Realiza um lance no leil�o criado
	 * 
	 * @param usuario
	 * @param valor
	 * @return um objeto do tipo CriadorDeLeilao
	 */
	public CriadorDeLeilao lance(Usuario usuario, double valor) {
		this.lances.add(new Lance(usuario, valor));
		return this;
	}
	
	/**
	 * Realiza o encerramento de um leil�o
	 * 
	 * @return um objeto do tipo CriadorDeLeilao
	 */
	public CriadorDeLeilao encerrado() {
		this.encerrado = true;
		return this;
	}

	/**
	 * Realiza a constru��o de um leil�o
	 * 
	 * @return um leil�o construido
	 */
	public Leilao constroi() {
		Leilao leilao = new Leilao(descricao, data);
		for (Lance lance : lances) {
			leilao.propoe(lance);
		}
		if(this.encerrado) {
			leilao.encerra();
		}
		return leilao;
	}
}
