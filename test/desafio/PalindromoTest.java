package desafio;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import desafio.Palindromo;

/**
 * Classe respons�vel por realizar a cobertura de testes de unidade da classe
 * Palindromo
 * 
 * @since 21/08/2017 - 22h36
 * @author Jhonata Santos
 * @version 2.0
 */
public class PalindromoTest {

	/**
	 * Objetos utilizados na classe
	 */
	private String frasePalindromo;
	private String fraseNaoPalindromo;
	private Palindromo palindromo;

	/**
	 * Realiza a inicializa��o dos objetos antes de cada Teste
	 */
	@Before
	public void inicializa() {
		this.frasePalindromo = "Anotaram a data da maratona";
		this.fraseNaoPalindromo = "Testando uma frase n�o palindromo";
		this.palindromo = new Palindromo();
	}
	
	/**
	 * CT.000.001: Deve validar se uma frase � palindromo
	 */
	@Test
	public void deveValidarUmaFrasePalindromo() {
		boolean ehPalindromo = this.palindromo.ehPalindromo(frasePalindromo);
		assertTrue(ehPalindromo);
	}

	/**
	 * CT.000.002: Deve validar se uma frase n�o � palindromo
	 */
	@Test
	public void deveValidarUmaFraseNaoPalindromo() {
		boolean ehPalindromo = this.palindromo.ehPalindromo(fraseNaoPalindromo);
		assertFalse(ehPalindromo);
	}
	
	/**
	 * CT.000.003: Deve filtrar todos os caracteres especiais de uma frase antes
	 * de validar
	 */
	@Test
	public void deveFiltrarCaracteresInvalidosNaFrase() {
		boolean ehPalindromo = this.palindromo.ehPalindromo(frasePalindromo);
		assertTrue(ehPalindromo);
	}
}
