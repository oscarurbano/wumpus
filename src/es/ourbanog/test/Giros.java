package es.ourbanog.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.ourbanog.data.Jugador;
import es.ourbanog.data.Tablero;
import es.ourbanog.data.TipoCasilla;

/**
 * Test para verificar los jiros que puede realizar el jugador
 * y ver en qué perfil despues de cada giro. Inicialmente el
 * jugador siempre se perfila hacia el norte.
 * @author ourbanog
 *
 */

public class Giros {

	private Tablero tablero;
	/**
	 * Se inicializa un tablero, luego se modificará según intereses.
	 * @author ourbanog
	 */
	@Before
	public void inicializaTablero() {
		 tablero = new Tablero(4,3,3);
	}
	
	/**
	 * Se va a probar el giro del ususario a la izda y ver su perfil (hacia donde mira)
	 * en un principio el usuario siempre se perfila hacia el norte
	 */
	@Test
	public void giraIzquierda() {
		/*La primera vez el usuario está perfilado hacia el norte. Luego después de girar
		 * se perfilará haciael oeste 
		 */
		Jugador jugador = tablero.getJugador();
		tablero.giraJugadorIzquierda();
		assertTrue(jugador.getCasilla().getTipoCasilla()==TipoCasilla.JUG_OEST);
		
		/*Ahora cuando se vuelva a girar a la izquierda.El jugador se perfilará hacia el sur*/
		tablero.giraJugadorIzquierda();
		assertTrue(jugador.getCasilla().getTipoCasilla()==TipoCasilla.JUG_SUR);
		
		/*Ahora cuando se vuelva a girar a la izquierda.El jugador se perfilará hacia el este*/
		tablero.giraJugadorIzquierda();
		assertTrue(jugador.getCasilla().getTipoCasilla()==TipoCasilla.JUG_EST);
		
		/*Ahora cuando se vuelva a girar a la izquierda.El jugador se perfilará hacia el este*/
		tablero.giraJugadorIzquierda();
		assertTrue(jugador.getCasilla().getTipoCasilla()==TipoCasilla.JUG_NORTE);		
	}
	
	/**
	 * Se va a probar el giro del ususario a la derecha y ver su perfil (hacia donde mira)
	 * en un principio el usuario siempre se perfila hacia el norte (como acabó el test 
	 * anterior
	 */
	@Test
	public void giraDerecha() {
		/*La primera vez el usuario está perfilado hacia el norte. Luego después de girar
		 * se perfilará haciael este 
		 */
		Jugador jugador = tablero.getJugador();
		tablero.giraJugadorDerecha();
		assertTrue(jugador.getCasilla().getTipoCasilla()==TipoCasilla.JUG_EST);
		
		/*Ahora cuando se vuelva a girar a la derecha.El jugador se perfilará hacia el sur*/
		tablero.giraJugadorDerecha();
		assertTrue(jugador.getCasilla().getTipoCasilla()==TipoCasilla.JUG_SUR);
		
		/*Ahora cuando se vuelva a girar a la izquierda.El jugador se perfilará hacia el este*/
		tablero.giraJugadorDerecha();
		assertTrue(jugador.getCasilla().getTipoCasilla()==TipoCasilla.JUG_OEST);
		
		/*Ahora cuando se vuelva a girar a la izquierda.El jugador se perfilará hacia el este*/
		tablero.giraJugadorDerecha();
		assertTrue(jugador.getCasilla().getTipoCasilla()==TipoCasilla.JUG_NORTE);		
	}
	
}
