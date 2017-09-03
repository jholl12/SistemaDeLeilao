package br.com.caelum.leilao.servico;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.FiltroDeLances;

/**
 * Classe responsável por realizar a cobertura de testes de unidade da classe
 * FiltroDeLances de um leilão
 * 
 * @since 20/08/2017 - 21h15
 * @author Jhonata Santos
 * @version 2.0
 */
public class FiltroDeLancesTest {

	/**
	 * Objetos utilizados na classe
	 */
	private Usuario joao, maria;
	private FiltroDeLances filtro;

	/**
	 * Realiza a inicialização dos objetos antes de cada Teste
	 */
	@Before
	public void inicializa() {
		this.joao = new Usuario("João Lucas");
		this.maria = new Usuario("Maria Santos");
		this.filtro = new FiltroDeLances();
	}
	
	/**
	 * CT.000.001: Deve selecionar somente lances com valores entre 1000 e 30000
	 */
	@Test
	public void deveSelecionarLancesEntre1000E3000() {
		List<Lance> lances = new CriadorDeLeilao()
								.para("Mac Book Pro 15")
								.lance(this.joao, 1000)
								.lance(this.maria, 2000)
								.lance(this.joao, 3000)
								.lance(this.maria, 500)
								.constroi()
								.getLances();
			
		List<Lance>lancesFiltrados = this.filtro.filtra(lances);

		assertEquals(1, lancesFiltrados.size());
		assertEquals(2000, lancesFiltrados.get(0).getValor(), 0.0001);
	}

	/**
	 * CT.000.002: Deve selecionar somente lances com valores entre 500 e 700
	 */
	@Test
	public void deveSelecionarLancesEntre500E700() {		
		List<Lance> lances = new CriadorDeLeilao()
								.para("Mac Book Pro 15")
								.lance(this.joao, 500)
								.lance(this.maria, 600)
								.lance(this.joao, 700)
								.lance(this.maria, 50)
								.constroi()
								.getLances();

		List<Lance> lancesFiltrados = this.filtro.filtra(lances);
		
		assertEquals(1, lancesFiltrados.size());
		assertEquals(600, lancesFiltrados.get(0).getValor(), 0.0001);
	}

	/**
	 * CT.000.003: Deve selecionar somente lances maiores que 5000
	 */
	@Test
	public void deveSelecionarLancesMaioresQue5000() {
		List<Lance> lances = new CriadorDeLeilao()
								.para("Mac Book Pro 15")
								.lance(this.joao, 5000)
								.lance(this.maria, 6000)
								.constroi()
								.getLances();
		
		List<Lance> lancesFiltrados = this.filtro.filtra(lances);
		
		assertEquals(1, lancesFiltrados.size());
		assertEquals(6000, lancesFiltrados.get(0).getValor(), 0.0001);
	}
	
	/**
	 * CT.000.004: Deve eliminar qualquer lance que seja menor que 500
	 */
	@Test
	public void deveEliminarMenoresQue500() {
		List<Lance> lances = new CriadorDeLeilao()
								.para("Mac Book Pro 15")
								.lance(this.joao, 500)
								.lance(this.maria, 200)
								.constroi()
								.getLances();

		List<Lance> lancesFiltrados = this.filtro.filtra(lances);
		
		assertEquals(0, lancesFiltrados.size());
	}
	
	/**
	 * CT.000.005: Deve eliminar qualquer lance que esteja entre 700 e 1000
	 */
	@Test
	public void deveEliminarEntre700E1000() {
		List<Lance> lances = new CriadorDeLeilao()
								.para("Mac Book Pro 15")
								.lance(this.joao, 700)
								.lance(this.maria, 800)
								.lance(this.joao, 900)
								.lance(this.maria, 1000)
								.constroi()
								.getLances();

		List<Lance> lancesFiltrados = this.filtro.filtra(lances);
		
		assertEquals(0, lancesFiltrados.size());
	}
	
	/**
	 * CT.000.006: Deve eliminar qualquer lance que esteja entre 3000 e 5000
	 */
	@Test
	public void deveEliminarEntre3000E5000() {
		List<Lance> lances = new CriadorDeLeilao()
								.para("Mac Book Pro 15")
								.lance(this.joao, 3000)
								.lance(this.maria, 4000)
								.constroi()
								.getLances();
		
		List<Lance> lancesFiltrados = this.filtro.filtra(lances);
		
		assertEquals(0, lancesFiltrados.size());
	}
}
