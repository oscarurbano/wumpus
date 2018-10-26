package es.ourbanog.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import es.ourbanog.data.Casilla;
import es.ourbanog.data.Tablero;
import es.ourbanog.data.TipoCasilla;

/**
 * Se verifican cómo queda las porciones de tablero importantes
 * según los avances que hace el usuario.
 * @author ourbanog
 *
 */
public class Movimientos {
	private Tablero tablero;
	Casilla casJugAnteAvance;
	Casilla casJugPostAvance;
	
	/**
	 * Se inicializa un tablero, luego se modificará según intereses.
	 * 
	 */
	@Before
	public void inicializaTablero() {
		 tablero = new Tablero(4,3,3);
	}
	/**
	 *  Cuemprueba si desde una acción de avance el jugador avanza la casilla y cómo queda
	 *  el tablero. 
	 *  Una casilla es vacia si no está en el perímetro del tablero y no hay nada.
	 *  Una casilla es pared si está en el perímetro del tablero y no hay nada.
	 *  Una casilla es el ligote de oro si en esa casilla esta el lingote de oro (en el perímatro del tablero o no)
	 *  Los posibles avances son 
	 *  <ul>
	 *  <li>Desde el jugador fuera del perímetro a una vacia</li>
	 *  <li>Desde el jugador fuera del perímetro a una pared</li>
	 *  <li>Desde el jugador en el perímetro a una vacía</li>
	 *  <li>Desde el jugadr en el perímetro a una pared</li>
	 *  <li>Desde el jugador fuera del perímetro  a un lingote</li>
	 *  <li>Desde el jugador en el perímetroa un lingote</li>
	 */
	@Test
	public void avananzaCasillaFueraPer2Vacia() {
		//Desde el jugador fuera del perímetro a una vacia
		tablero.getJugador().getCasilla().setCoord_X(2);
		tablero.getJugador().getCasilla().setCoord_Y(2);
		casJugAnteAvance = tablero.getJugador().getCasilla();
		casJugPostAvance=tablero.getCasilla(2, 1);
		casJugPostAvance.setTipoCasila(TipoCasilla.VACIA);
		tablero.avanza();
		assertTrue(casJugAnteAvance.getTipoCasilla()==TipoCasilla.VACIA && casJugPostAvance.getTipoCasilla()==TipoCasilla.JUG_NORTE 
				&& !tablero.getJugador().isTieneLingote() && !tablero.isGana() && !tablero.isPierde() 
				&&	casJugPostAvance.getCoord_X()==casJugAnteAvance.getCoord_X() && casJugPostAvance.getCoord_Y()==casJugAnteAvance.getCoord_Y()-1);
	}
	@Test
	public void avanzaCasillaFueraPer2Pared() {	
		//Desde el jugador fuera del perímetro a una pared
		tablero.getJugador().getCasilla().setCoord_X(2);
		tablero.getJugador().getCasilla().setCoord_Y(1);
		casJugAnteAvance = tablero.getJugador().getCasilla();
		casJugPostAvance=tablero.getCasilla(2, 0);
		casJugPostAvance.setTipoCasila(TipoCasilla.PARED);
		tablero.avanza();
		assertTrue(casJugAnteAvance.getTipoCasilla()==TipoCasilla.VACIA && casJugPostAvance.getTipoCasilla()==TipoCasilla.JUG_NORTE 
				&& !tablero.getJugador().isTieneLingote() && !tablero.isGana() && !tablero.isPierde()
				&& casJugPostAvance.getCoord_X()==casJugAnteAvance.getCoord_X() && casJugPostAvance.getCoord_Y()==casJugAnteAvance.getCoord_Y()-1);
	}
	
	@Test
	public void avanzaCasillaPer2Vacia() {
		tablero.getJugador().getCasilla().setCoord_X(2);
		tablero.getJugador().getCasilla().setCoord_Y(tablero.getDimension()-1);
		casJugAnteAvance = tablero.getJugador().getCasilla();
		casJugPostAvance=tablero.getCasilla(2, tablero.getDimension()-2);
		casJugPostAvance.setTipoCasila(TipoCasilla.VACIA);
		tablero.avanza();
		assertTrue(casJugAnteAvance.getTipoCasilla()==TipoCasilla.VACIA && casJugPostAvance.getTipoCasilla()==TipoCasilla.JUG_NORTE 
				&& !tablero.getJugador().isTieneLingote() && !tablero.isGana() && !tablero.isPierde()
				&& casJugPostAvance.getCoord_X()==casJugAnteAvance.getCoord_X() && casJugPostAvance.getCoord_Y()==casJugAnteAvance.getCoord_Y()-1);
		
	}
	@Test 
	public void avanzaCasillaPer2Pared() {
		tablero.getJugador().getCasilla().setCoord_X(0);
		tablero.getJugador().getCasilla().setCoord_Y(tablero.getDimension()-1);
		casJugAnteAvance = tablero.getJugador().getCasilla();
		casJugPostAvance=tablero.getCasilla(0, tablero.getDimension()-2);
		casJugPostAvance.setTipoCasila(TipoCasilla.PARED);
		tablero.avanza();
		assertTrue(casJugAnteAvance.getTipoCasilla()==TipoCasilla.PARED && casJugPostAvance.getTipoCasilla()==TipoCasilla.JUG_NORTE 
				&& !tablero.getJugador().isTieneLingote() && !tablero.isGana() && !tablero.isPierde()
				&& casJugPostAvance.getCoord_X()==casJugAnteAvance.getCoord_X() && casJugPostAvance.getCoord_Y()==casJugAnteAvance.getCoord_Y()-1);
	}
	
	@Test 
	public void avanzaCasillaFueraPer2Lingote() {
		tablero.getJugador().getCasilla().setCoord_X(2);
		tablero.getJugador().getCasilla().setCoord_Y(1);
		casJugAnteAvance = tablero.getJugador().getCasilla();
		casJugPostAvance=tablero.getCasilla(2, 0);
		casJugPostAvance.setTipoCasila(TipoCasilla.LINGOTE);
		tablero.avanza();
		assertTrue(casJugAnteAvance.getTipoCasilla()==TipoCasilla.VACIA && casJugPostAvance.getTipoCasilla()==TipoCasilla.JUG_NORTE 
				&& tablero.getJugador().isTieneLingote() && !tablero.isGana() && !tablero.isPierde()
				&& casJugPostAvance.getCoord_X()==casJugAnteAvance.getCoord_X() && casJugPostAvance.getCoord_Y()==casJugAnteAvance.getCoord_Y()-1);
	}
	
	@Test 
	public void avanzaCasillaPer2Lingote() {
		tablero.getJugador().getCasilla().setCoord_X(0);
		tablero.getJugador().getCasilla().setCoord_Y(1);
		casJugAnteAvance = tablero.getJugador().getCasilla();
		casJugPostAvance=tablero.getCasilla(0, 0);
		casJugPostAvance.setTipoCasila(TipoCasilla.LINGOTE);
		tablero.avanza();
		assertTrue(casJugAnteAvance.getTipoCasilla()==TipoCasilla.PARED && casJugPostAvance.getTipoCasilla()==TipoCasilla.JUG_NORTE 
				&& tablero.getJugador().isTieneLingote() && !tablero.isGana() && !tablero.isPierde()
				&& casJugPostAvance.getCoord_X()==casJugAnteAvance.getCoord_X() && casJugPostAvance.getCoord_Y()==casJugAnteAvance.getCoord_Y()-1);
	}
	
}
