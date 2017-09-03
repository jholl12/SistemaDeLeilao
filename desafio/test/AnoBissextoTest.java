package test;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import src.AnoBissexto;

/**
 * Classe responsável por realizar a cobertura de testes de unidade da classe
 * AnoBissexto
 * 
 * @since 21/08/2017 - 21h11
 * @author Jhonata Santos
 * @version 2.0
 */
public class AnoBissextoTest {

	/**
	 * Objetos utilizados na classe
	 */
	private AnoBissexto anoBissexto;

	/**
	 * Realiza a inicialização dos objetos antes de cada Teste
	 */
	@Before
	public void inicializa() {
		anoBissexto = new AnoBissexto();
	}
	
	/**
	 * CT.000.001: Deve retornar verdadeiro quando informado um ano bissexto
	 */
	@Test
	public void deveRetornarAnoBissexto() {		
		assertEquals(true, this.anoBissexto.isBissexto(2016));
		assertEquals(true, this.anoBissexto.isBissexto(2020));
	}
	
	/**
	 * CT.000.001: Deve retornar falso quando informado um ano bissexto
	 */
	@Test
	public void naoDeveRetornarAnoBissexto() {		
		assertEquals(false, this.anoBissexto.isBissexto(2011));
		assertEquals(false, this.anoBissexto.isBissexto(2015));
	}
}
