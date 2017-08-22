package br.com.caelum.leilao.desafio;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.desafio.MatematicaMaluca;

/**
 * Classe responsável por realizar a cobertura de testes de unidade da classe
 * MatematicaMaluca
 * 
 * @since 21/08/2017 - 22h24
 * @author Jhonata Santos
 * @version 2.0
 */
public class MatematicaMalucaTest {
	
	/**
	 * Objetos utilizados na classe
	 */
	private MatematicaMaluca matematica;

	/**
	 * Realiza a inicialização dos objetos antes de cada Teste
	 */
	@Before
	public void inicializa() {
		this.matematica = new MatematicaMaluca();
	}
	
	/**
	 * CT.000.001: Deve validar se os número são iguais a 30
	 */
	@Test
	public void deveValidarNumerosIguaisA30() {
		assertEquals(30*3, matematica.contaMaluca(30));
	}
	
	/**
	 * CT.000.002: Deve validar se os número são iguais a 10
	 */
	@Test
	public void deveValidarNumerosIguaisA10() {
		assertEquals(10*2,	matematica.contaMaluca(10));
	}
	
	/**
	 * CT.000.003: Deve validar números que são maiores que 30
	 */
	@Test
	public void deveValidarNumerosMaioresQue30() {
		assertEquals(40*4, matematica.contaMaluca(40));
	}
	
	/**
	 * CT.000.004: Deve validar números que são maiores que 10 e menores que 30
	 */
	@Test
	public void deveValidarNumerosMaioresQue10EmenoresQue30() {
		assertEquals(20*3, matematica.contaMaluca(20));
	}
}
