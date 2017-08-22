package br.com.caelum.leilao.dominio.matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

/**
 * 
 * @author Jhonata Santos
 *
 */
public class LeilaoMatcher extends TypeSafeMatcher<Leilao> {

	private final Lance lance;

	public LeilaoMatcher(Lance lance){
		this.lance = lance;
	}
	
	public void describeTo(Description descricao) {
		descricao.appendText("Leil�o com lance: " + this.lance.getValor());
	}

	@Override
	protected boolean matchesSafely(Leilao item) {
		return item.getLances().contains(this.lance);
	}
	
	public static Matcher<Leilao> temUmLance(Lance lance) {
		return new LeilaoMatcher(lance);
	}
}
