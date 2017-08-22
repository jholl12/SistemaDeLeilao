package br.com.caelum.leilao.builders;

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
	private Leilao leilao;

	/**
	 * Insere a descri��o do produto leiloado
	 * 
	 * @param descricao
	 * @return um objeto do tipo CriadorDeLeilao
	 */
	public CriadorDeLeilao para(String descricao) {
		this.leilao = new Leilao(descricao);
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
		leilao.propoe(new Lance(usuario, valor));
		return this;
	}

	/**
	 * Realiza a constru��o de um leil�o
	 * 
	 * @return um leil�o construido
	 */
	public Leilao constroi() {
		return this.leilao;
	}
}
