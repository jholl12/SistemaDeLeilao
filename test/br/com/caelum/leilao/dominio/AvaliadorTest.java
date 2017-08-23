package br.com.caelum.leilao.dominio;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;

/**
 * Classe responsável por realizar a cobertura de testes de unidade da classe
 * Avalidador de um leilão
 * 
 * @since 20/08/2017 - 15h38
 * @author Jhonata Santos
 * @version 2.0
 */
public class AvaliadorTest {

	/**
	 * Objetos utilizados na classe
	 */
	private Avaliador leiloeiro;
	private Usuario joao, maria, pedro;

	/**
	 * Executado uma vez antes de todos os testes
	 */
	@BeforeClass
	public static void testandoBeforeClass() {
		System.out.println("Before Class");
	}
		
	/**
	 * Realiza a inicialização dos objetos antes de cada Teste
	 */
	@Before
	public void inicializa() {
		this.leiloeiro = new Avaliador();
		this.joao = new Usuario("João Lucas");
		this.maria = new Usuario("Maria Santos");
		this.pedro = new Usuario("Pedro Henrique");
		System.out.println("Inicializando teste!");
	}

	/**
	 * CT.000.001: Deve entender leilão com apenas um lance dado
	 */
	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		// Parte 1: Cenario - Leilão com apenas um lance
		Leilao leilao = new CriadorDeLeilao()
				.para("Xbox ONE")
				.lance(this.joao, 1000.0)
				.constroi();

		// Parte 2 - Ação - Execução
		this.leiloeiro.avalia(leilao);

		// Parte 3 - Validação - Comparação da saida
        assertThat(leiloeiro.getMaiorLance(), equalTo(1000.0));
        assertThat(leiloeiro.getMenorLance(), equalTo(1000.0));
	}

	/**
	 * CT.000.002: Deve entender lances dados em ordem crescente
	 */
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		// Parte 1: Cenario - 3 lances em ordem crescente
		Leilao leilao = new CriadorDeLeilao()
				.para("Xbox ONE")
				.lance(this.joao, 150.0)
				.lance(this.maria, 250.0)
				.lance(this.pedro, 350.0)
				.constroi();
	
		// Parte 2 - Ação - Execução
		this.leiloeiro.avalia(leilao);

		// Parte 3 - Validação - Comparação da saida
        assertThat(leiloeiro.getMenorLance(), equalTo(150.0));
		assertThat(leiloeiro.getMaiorLance(), equalTo(350.0));
	}

	/**
	 * CT.000.003: Deve entender lances dados em ordem decrescente
	 */
	@Test
	public void deveEntenderLancesEmOrdemDecrescente() {
		// cenario: 3 lances em ordem crescente
		Leilao leilao = new CriadorDeLeilao()
				.para("Xbox ONE")
				.lance(this.joao, 350.0)
				.lance(this.maria, 250.0)
				.lance(this.pedro, 150.0)
				.constroi();

		// Parte 2 - Ação - Execução
		this.leiloeiro.avalia(leilao);

		// Parte 3 - Validação - Comparação da saida
		assertThat(leiloeiro.getMaiorLance(), equalTo(350.0));
        assertThat(leiloeiro.getMenorLance(), equalTo(150.0));
	}

	/**
	 * CT.000.004: Deve entender lances dados em ordem randômica
	 */
	@Test
	public void deveEntenderLeilaoComLancesEmOrdemRandomica() {
		// cenario: 3 lances em ordem crescente
		Leilao leilao = new CriadorDeLeilao()
				.para("Xbox ONE")
				.lance(this.joao, 200.0)
				.lance(this.maria, 450.0)
				.lance(this.joao, 120.0)
				.lance(this.pedro, 700.0)
				.lance(this.joao, 630.0)
				.lance(this.maria, 230.0)
				.constroi();
	
		// Parte 2 - Ação - Execução
		this.leiloeiro.avalia(leilao);

		// Parte 3 - Validação - Comparação da saida
		assertThat(leiloeiro.getMaiorLance(), equalTo(700.0));
		assertThat(leiloeiro.getMenorLance(), equalTo(120.0));
	}

	/**
	 * CT.000.005: Deve calcular o valor médio dos lances dado
	 */
	@Test
	public void deveCalcularValorMedioDosLances() {
		// cenario: 3 lances em ordem crescente
		Leilao leilao = new CriadorDeLeilao()
				.para("Xbox ONE")
				.lance(this.joao, 100.0)
				.lance(this.maria, 200.0)
				.lance(this.pedro, 300.0)
				.constroi();
		
		// Parte 2 - Ação - Execução
		this.leiloeiro.avalia(leilao);

		// Parte 3 - Validação - Comparação da saida
		assertThat(leiloeiro.getLanceMedio(), equalTo(200.0));
	}

	/**
	 * CT.000.006: Deve encontrar os três maiores lances dado no leilão
	 */
	@Test
	public void deveEncontrarOsTresMaioresLances() {
		// cenario: 3 lances em ordem crescente
		Leilao leilao = new CriadorDeLeilao()
				.para("Xbox ONE")
				.lance(this.joao, 150.0)
				.lance(this.maria, 450.0)
				.lance(this.pedro, 850.0)
				.lance(this.maria, 720.0)
				.constroi();
		
		// Parte 2 - Ação - Execução
		this.leiloeiro.avalia(leilao);

		// Parte 3 - Validação - Comparação da saida
		assertThat(leiloeiro.getTresMaioresLances().size(), equalTo(3));
		assertThat(leiloeiro.getTresMaioresLances().get(0).getValor(), equalTo(850.0));
		assertThat(leiloeiro.getTresMaioresLances().get(1).getValor(), equalTo(720.0));
		assertThat(leiloeiro.getTresMaioresLances().get(2).getValor(), equalTo(450.0));
	}

	/**
	 * CT.000.007: Deve encontrar os três maiores lances dado entre os 5 dado
	 */
	@Test
	public void deveEncontrarOsTresMaioresEntreCinco() {
		// cenario: 3 lances em ordem crescente
		Leilao leilao = new CriadorDeLeilao()
				.para("Xbox ONE")
				.lance(this.joao, 100.0)
				.lance(this.maria, 380.0)
				.lance(this.pedro, 550.0)
				.lance(this.joao, 870.0)
				.lance(this.maria, 1000.0)
				.constroi();

		// Parte 2 - Ação - Execução
		this.leiloeiro.avalia(leilao);

		List<Lance> lances = leilao.getLances();
		List<Lance> maioresLances = leiloeiro.getTresMaioresLances();

		// Parte 3 - Validação - Comparação da saida
		assertThat(lances.size(), equalTo(5));
		assertThat(maioresLances.size(), equalTo(3));
		assertThat(maioresLances.get(0).getValor(), equalTo(1000.0));
		assertThat(maioresLances.get(1).getValor(), equalTo(870.0));
		assertThat(maioresLances.get(2).getValor(), equalTo(550.0));
	}

	/**
	 * CT.000.008: Deve apresentar todos os lances caso haja menos que 3 lances
	 * dado
	 */
	@Test
	public void deveDevolverTodosLancesCasoNaoHajaNoMinimo3() {
		// cenario: 3 lances em ordem crescente
		Leilao leilao = new CriadorDeLeilao()
				.para("Xbox ONE")
				.lance(this.joao, 550.0)
				.lance(this.maria, 800.0)
				.constroi();
		
		// Parte 2 - Ação - Execução
		this.leiloeiro.avalia(leilao);

		List<Lance> lances = leiloeiro.getTresMaioresLances();

		// Parte 3 - Validação - Comparação da saida
		assertThat(lances.size(), equalTo(2));
		assertThat(leiloeiro.getMenorLance(), equalTo(550.0));
		assertThat(leiloeiro.getMaiorLance(), equalTo(800.0));
	}

	/**
	 * CT.000.009: Deve entender leilões sem nenhum lance dado
	 */
	@Test
	public void deveEntenderLeilaoSemLances() {
		// cenario: 3 lances em ordem crescente
		Leilao leilao = new CriadorDeLeilao()
				.para("Xbox ONE")
				.constroi();
		
		// Parte 3 - Validação - Comparação da saida
		assertThat(leilao.getLances().size(), equalTo(0));
	}
	
	/**
	 * CT.000.010: Não deve avaliar um leilão caso não tenha lances dado
	 */
	@Test (expected = RuntimeException.class)
	public void naoDeveAvaliarLeiloesSemNenhumLanceDado() {
		Leilao leilao = new CriadorDeLeilao()
				.para("Xbox ONE")
				.constroi();
		
		this.leiloeiro.avalia(leilao);
	}

	@After
	public void finaliza() {
		System.out.println("Finalizando teste!");
	}
	
	@AfterClass
	public static void testandoAfterClass() {
		System.out.println("After Class");
	}
}
