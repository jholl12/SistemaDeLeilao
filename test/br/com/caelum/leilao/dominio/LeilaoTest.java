package br.com.caelum.leilao.dominio;

import static br.com.caelum.leilao.dominio.matchers.LeilaoMatcher.temUmLance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import br.com.caelum.leilao.dominio.builders.CriadorDeLeilao;

/**
 * Classe responsável por realizar a cobertura de testes de unidade da classe
 * Leilao de um leilão
 * 
 * @since 21/08/2017 - 21h53
 * @author Jhonata Santos
 * @version 2.0
 */
public class LeilaoTest {

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
	 * CT.000.001: Não deve aceitar doi lances seguidos de um mesmo usuário em
	 * um leilão
	 */
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 4")
				.lance(this.joao, 1000)
				.lance(this.joao, 2000)
				.constroi();

		assertEquals(1, leilao.getLances().size());
	}

	/**
	 * CT.000.002: Não deve aceitar mais que 5 lances em um leilão do mesmo
	 * usuário
	 */
	@Test
	public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 4")
				.lance(this.joao, 1000)
				.lance(this.maria, 2000)
				.lance(this.joao, 3000)
				.lance(this.maria, 4000)
				.lance(this.joao, 5000)
				.lance(this.maria, 6000)
				.lance(this.joao, 7000)
				.lance(this.maria, 8000)
				.lance(this.joao, 9000)
				.lance(this.maria, 10000)

				// Lance deve ser ignorado
				.lance(this.joao, 11000).constroi();

		assertEquals(10, leilao.getLances().size());
	}

	/**
	 * CT.000.003: Deve dobrar o ultimo lance dado pelo usuário
	 */
	@Test
	public void deveDobrarOUltimoLanceDado() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 4")
				.lance(this.joao, 1000)
				.lance(this.maria, 2000)
				.lance(this.joao, 3000)
				.lance(this.maria, 4000)
				.constroi();
		
		leilao.dobraUltimoLanceDado(this.joao);

		int ultimo = leilao.getLances().size() - 1;
		assertEquals(6000, leilao.getLances().get(ultimo).getValor(), 0.0001);
	}

	/**
	 * CT.000.004: Não deve dobrar o valor do lance caso não haja lances
	 * anteriores
	 */
	@Test
	public void naoDeveDobrarCasoNaoHajaLanceAnterior() {
		Leilao leilao = new CriadorDeLeilao()
				.para("Playstation 4")
				.constroi();
				
		leilao.dobraUltimoLanceDado(this.joao);

		assertEquals(0, leilao.getLances().size());
	}

	/**
	 * CT.000.005: Deve receber um lance
	 */
	@Test
	public void deveReceberUmLance() {
		Leilao leilao = new CriadorDeLeilao()
				.para("Mac Book Pro 15")
				.constroi();
		assertEquals(0, leilao.getLances().size());

		Lance lance = new Lance(new Usuario("João Lucas"), 2000.0);
		leilao.propoe(lance);

		assertThat(leilao.getLances().size(), equalTo(1));
		assertThat(leilao, temUmLance(lance));
	}
}
