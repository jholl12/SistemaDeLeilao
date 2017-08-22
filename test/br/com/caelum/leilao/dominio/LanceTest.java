package br.com.caelum.leilao.dominio;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.builders.CriadorDeLeilao;

/**
 * Classe responsável por realizar a cobertura de testes de unidade da classe
 * Lance de um leilão
 * 
 * @since 20/08/2017 - 17h15
 * @author Jhonata Santos
 * @version 2.0
 */
public class LanceTest {
	
	/**
	 * Objetos utilizados na classe
	 */
	private Usuario joao, maria;
	
	/**
	 * Realiza a inicialização dos objetos antes de cada Teste
	 */
	@Before
	public void inicializa() {
		this.joao = new Usuario("João Lucas");
		this.maria = new Usuario("Maria Santos");
	}
	
	/**
	 * CT.000.001: Não deve aceitar lances que forem menor que zero
	 */
	@Test (expected = IllegalArgumentException.class)
	public void naoDeveAceitarLancesMenorQueZero() {
		new CriadorDeLeilao()
			.para("Mac Book Pro 15")
			.lance(this.joao, -100)
			.lance(this.maria, -200)
			.constroi();
	}
	
	/**
	 * CT.000.002: Não deve aceitar lances que forem igual a zero
	 */
	@Test (expected = IllegalArgumentException.class)
	public void naoDeveAceitarLancesIgualAZero() {
		new CriadorDeLeilao()
			.para("Mac Book Pro 15")
			.lance(this.joao, 0)
			.lance(this.maria, 0)
			.constroi();
	}
	
	/**
	 * CT.000.003: Deve aceitar lances que forem maior que zero
	 */
	@Test
	public void deveAceitarLancesMaiorQueZero() {
		Leilao leilao = new CriadorDeLeilao()
			.para("Mac Book Pro 15")
			.lance(this.joao, 10)
			.lance(this.maria, 20)
			.constroi();
		
		assertEquals(2, leilao.getLances().size());
		assertEquals(10, leilao.getLances().get(0).getValor(), 0.0001);
		assertEquals(20, leilao.getLances().get(1).getValor(), 0.0001);
	}
}
