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
	Casilla casOrig;
	Casilla casDest;
	
	private void inicializaTableroVacio() {
		tablero = new Tablero(4,3,3);
		for(int x=0;x<4;x++) {
			for(int y=0;y<4;y++) {
				Casilla casilla=null;
				if(x==0 || y==0) {
					casilla=new Casilla(x, y, TipoCasilla.PARED);
				}else {
					casilla=new Casilla(x, y, TipoCasilla.VACIA);
				}
				tablero.getCasillas()[x][y]=casilla;
			}
		}
	}
	
	
	/**
	 *  Los siguientes tests cuemprueban si desde una acción de avance el jugador avanza la casilla y cómo queda
	 *  el tablero. 
	 *  Una casilla es vacia si no está en el perímetro del tablero y no hay nada.
	 *  Una casilla es pared si está en el perímetro del tablero y no hay nada.
	 *  Una casilla es el ligote de oro si en esa casilla esta el lingote de oro (en el perímatro del tablero o no)
	 *  Los posibles avances son 
	 *   	Desde el jugador fuera del perímetro a una vacia 
	 *   	Desde el jugador fuera del perímetro a una pared 
	 *   	Desde el jugador en el perímetro a una vacía 
	 *   	Desde el jugadr en el perímetro a una pared 
	 *   	Desde el jugador fuera del perímetro  a un lingote 
	 *   	Desde el jugador en el perímetroa un lingote 
	 *
	 */
	@Test
	public void avananzaCasillaFueraPer2Vacia() {
		System.out.println("\navananzaCasillaFueraPer2Vacia");
		inicializaTableroVacio();
		tablero.getJugador().getCasilla().setCoord_X(2);
		tablero.getJugador().getCasilla().setCoord_Y(2);
		casOrig = tablero.getJugador().getCasilla();
		casDest=tablero.getCasilla(2, 1);
		casDest.setTipoCasila(TipoCasilla.VACIA);
		tablero.getCasillas()[2][2]=casOrig;
		System.out.println("Antes del avance");
		System.out.println(tablero);
		tablero.avanza();
		System.out.println("Despues del avance");
		System.out.println("Casilla origen: "+ casOrig);
		System.out.println("Casilla destino: "+ casDest);
		assertTrue(casOrig.getTipoCasilla()==TipoCasilla.VACIA && casDest.getTipoCasilla()==TipoCasilla.JUG_NORTE 
				&& !tablero.getJugador().isTieneLingote() && !tablero.isGana() && !tablero.isPierde());		
	}
	@Test
	public void avanzaCasillaFueraPer2Pared() {	
		System.out.println("\navanzaCasillaFueraPer2Pared");
		inicializaTableroVacio();
		tablero.getJugador().getCasilla().setCoord_X(2);
		tablero.getJugador().getCasilla().setCoord_Y(1);
		casOrig = tablero.getJugador().getCasilla();
		casDest=tablero.getCasilla(2, 0);
		casDest.setTipoCasila(TipoCasilla.PARED);
		tablero.getCasillas()[2][1]=casOrig;
		System.out.println("Antes del avance");
		System.out.println(tablero);
		tablero.avanza();
		System.out.println("Despues del avance");
		System.out.println("Casilla origen: "+ casOrig);
		System.out.println("Casilla destino: "+ casDest);
		assertTrue(casOrig.getTipoCasilla()==TipoCasilla.VACIA && casDest.getTipoCasilla()==TipoCasilla.JUG_NORTE 
				&& !tablero.getJugador().isTieneLingote() && !tablero.isGana() && !tablero.isPierde());
	}
	
	@Test
	public void avanzaCasillaPer2Vacia() {
		System.out.println("\navanzaCasillaPer2Vacia");
		inicializaTableroVacio();
		tablero.getJugador().getCasilla().setCoord_X(2);
		tablero.getJugador().getCasilla().setCoord_Y(tablero.getDimension()-1);
		casOrig = tablero.getJugador().getCasilla();
		casDest=tablero.getCasilla(2, tablero.getDimension()-2);
		tablero.getCasillas()[2][tablero.getDimension()-1]=casOrig;
		casDest.setTipoCasila(TipoCasilla.VACIA);
		System.out.println("Antes del avance");
		System.out.println(tablero);
		tablero.avanza();
		System.out.println("Despues del avance");
		System.out.println("Casilla origen: "+ casOrig);
		System.out.println("Casilla destino: "+ casDest);
		assertTrue(casOrig.getTipoCasilla()==TipoCasilla.VACIA && casDest.getTipoCasilla()==TipoCasilla.JUG_NORTE 
				&& !tablero.getJugador().isTieneLingote() && !tablero.isGana() && !tablero.isPierde());	
	}
	@Test 
	public void avanzaCasillaPer2Pared() {
		System.out.println("\navanzaCasillaPer2Pared");
		inicializaTableroVacio();
		tablero.getJugador().getCasilla().setCoord_X(0);
		tablero.getJugador().getCasilla().setCoord_Y(tablero.getDimension()-1);
		casOrig = tablero.getJugador().getCasilla();
		casDest=tablero.getCasilla(0, tablero.getDimension()-2);
		casDest.setTipoCasila(TipoCasilla.PARED);
		tablero.getCasillas()[0][tablero.getDimension()-1]=casOrig;
		System.out.println("Antes del avance");
		System.out.println(tablero);
		tablero.avanza();
		System.out.println("Despues del avance");
		System.out.println("Casilla origen: "+ casOrig);
		System.out.println("Casilla destino: "+ casDest);
		assertTrue(casOrig.getTipoCasilla()==TipoCasilla.PARED && casDest.getTipoCasilla()==TipoCasilla.JUG_NORTE 
				&& !tablero.getJugador().isTieneLingote() && !tablero.isGana() && !tablero.isPierde());
	}
	
	@Test 
	public void avanzaCasillaFueraPer2Lingote() {
		System.out.println("\navanzaCasillaFueraPer2Lingote");
		inicializaTableroVacio();
		tablero.getJugador().getCasilla().setCoord_X(2);
		tablero.getJugador().getCasilla().setCoord_Y(1);
		casOrig = tablero.getJugador().getCasilla();
		casDest=tablero.getCasilla(2, 0);
		casDest.setTipoCasila(TipoCasilla.LINGOTE);
		tablero.getCasillas()[2][1]=casOrig;
		System.out.println("Antes del avance");
		System.out.println(tablero);
		tablero.avanza();
		System.out.println("Despues del avance");
		System.out.println("Casilla origen: "+ casOrig);
		System.out.println("Casilla destino: "+ casDest);
		assertTrue(casOrig.getTipoCasilla()==TipoCasilla.VACIA && casDest.getTipoCasilla()==TipoCasilla.JUG_NORTE 
				&& tablero.getJugador().isTieneLingote() && !tablero.isGana() && !tablero.isPierde());

	}
	
	@Test 
	public void avanzaCasillaPer2Lingote() {
		System.out.println("\navanzaCasillaPer2Lingote");
		inicializaTableroVacio();
		tablero.getJugador().getCasilla().setCoord_X(0);
		tablero.getJugador().getCasilla().setCoord_Y(1);
		casOrig = tablero.getJugador().getCasilla();
		casDest=tablero.getCasilla(0, 0);
		casDest.setTipoCasila(TipoCasilla.LINGOTE);
		tablero.getCasillas()[0][1]=casOrig;
		System.out.println("Antes del avance");
		System.out.println(tablero);
		tablero.avanza();
		System.out.println("Despues del avance");
		System.out.println("Casilla origen: "+ casOrig);
		System.out.println("Casilla destino: "+ casDest);
		assertTrue(casOrig.getTipoCasilla()==TipoCasilla.PARED && casDest.getTipoCasilla()==TipoCasilla.JUG_NORTE 
				&& tablero.getJugador().isTieneLingote() && !tablero.isGana() && !tablero.isPierde());
	}
	
	/**
	 * Los siguientes tests verifica que el jugador intenta avanzar una casilla pero las cirunstanicas del tablero lo impide, sin que
	 * signifique que el usuariogana o pierde la partida. Esto son los escenarios. 
	 * 		El jugador avanza a la casilla de salida pero no tiene el lingote
	 *  	El jugador avanza hacia un wumpus muerto
	 *  	El jugador está en el perímetro del tablero perfilado hacia la pared. No puede avanzar fuera del tablero.
	 * Aquí no se valora si el jugador está en el perímetro o más al interior pues los resultados del avance etán probados en el grupo de 
	 * testanteriores y además este grupo se trata de que no avanza el usuario.
	 */
	
	@Test
	public void haciaSalidaSinLingote() {
		System.out.println("\nhaciaSalidaSinLingote");
		inicializaTableroVacio();
		tablero.getJugador().getCasilla().setCoord_X(0);
		tablero.getJugador().getCasilla().setCoord_Y(1);
		tablero.getJugador().setTieneLingote(false); // En el último test acaba con lingote
		casOrig = tablero.getJugador().getCasilla();
		casDest=tablero.getCasilla(0, 0);
		casDest.setTipoCasila(TipoCasilla.SALIDA);
		tablero.getCasillas()[0][1]=casOrig;
		System.out.println("Antes del avance");
		System.out.println(tablero);
		tablero.avanza();
		System.out.println("Despues del avance");
		System.out.println("Casilla origen: "+ casOrig);
		System.out.println("Casilla destino: "+ casDest);
		assertTrue(casOrig.getTipoCasilla()==TipoCasilla.JUG_NORTE && casDest.getTipoCasilla()==TipoCasilla.SALIDA 
				&& !tablero.getJugador().isTieneLingote() && !tablero.isGana() && !tablero.isPierde());
	}

	@Test 
	public void haciaWumpusMuerto() {
		System.out.println("\nhaciaWumpusMuerto");
		inicializaTableroVacio();
		tablero.getJugador().getCasilla().setCoord_X(0);
		tablero.getJugador().getCasilla().setCoord_Y(1);
		casOrig = tablero.getJugador().getCasilla();
		casDest=tablero.getCasilla(0, 0);
		casDest.setTipoCasila(TipoCasilla.WUMPUS_MUERTO);
		tablero.getCasillas()[0][1]=casOrig;
		System.out.println("Antes del avance");
		System.out.println(tablero);
		tablero.avanza();
		System.out.println("Despues del avance");
		System.out.println("Casilla origen: "+ casOrig);
		System.out.println("Casilla destino: "+ casDest);
		assertTrue(casOrig.getTipoCasilla()==TipoCasilla.JUG_NORTE && casDest.getTipoCasilla()==TipoCasilla.WUMPUS_MUERTO 
				&& !tablero.getJugador().isTieneLingote() && !tablero.isGana() && !tablero.isPierde());
	}
	
	@Test 
	public void haciaFueraTablero() {
		System.out.println("\nhaciaFueraTablero");
		inicializaTableroVacio();
		tablero.getJugador().getCasilla().setCoord_X(0);
		tablero.getJugador().getCasilla().setCoord_Y(0);
		casOrig = tablero.getJugador().getCasilla();
		tablero.getCasillas()[0][0]=casOrig;
		System.out.println("Antes del avance");
		System.out.println(tablero);
		tablero.avanza();
		System.out.println("Despues del avance");
		System.out.println("Casilla origen: "+ casOrig);
		System.out.println("Casilla destino: "+ casDest);
		assertTrue(casOrig.getTipoCasilla()==TipoCasilla.JUG_NORTE  
				&& !tablero.getJugador().isTieneLingote() && !tablero.isGana() && !tablero.isPierde());
	}
	/**
	 * Este grupo de test coprueba que cuando se dan las circunstancias dadas el jugadir muere (pierde la partida)
	 * 		El jugador quiere avanzar al wumpus muerto
	 * 		El jugador quiere avanzar a un agujero.
	 * Aquí no se valora si el jugador está en el perímetro o más al interior pues los resultados del avance etán probados en el grupo de 
	 * testanteriores y además este grupo se trata de que no avanza el usuario.
	 */
	@Test 
	public void haciaWumpusVivo() {
		System.out.println("\nhaciaWumpusVivo");
		inicializaTableroVacio();
		tablero.getJugador().getCasilla().setCoord_X(0);
		tablero.getJugador().getCasilla().setCoord_Y(1);
		casOrig = tablero.getJugador().getCasilla();
		casDest=tablero.getCasilla(0, 0);
		casDest.setTipoCasila(TipoCasilla.WUMPUS_VIVO);
		tablero.getCasillas()[0][1]=casOrig;
		System.out.println("Antes del avance");
		System.out.println(tablero);
		tablero.avanza();
		System.out.println("Despues del avance");
		System.out.println("Casilla origen: "+ casOrig);
		System.out.println("Casilla destino: "+ casDest);
		assertTrue(casOrig.getTipoCasilla()==TipoCasilla.JUG_NORTE && casDest.getTipoCasilla()==TipoCasilla.WUMPUS_VIVO
				&& !tablero.getJugador().isTieneLingote() && !tablero.isGana() && tablero.isPierde());
	}
	
	@Test 
	public void haciaAgujero() {
		System.out.println("\nhaciaAgujero");
		inicializaTableroVacio();
		tablero.getJugador().getCasilla().setCoord_X(0);
		tablero.getJugador().getCasilla().setCoord_Y(1);
		casOrig = tablero.getJugador().getCasilla();
		casDest=tablero.getCasilla(0, 0);
		casDest.setTipoCasila(TipoCasilla.AGUJERO);
		tablero.getCasillas()[0][1]=casOrig;
		System.out.println("Antes del avance");
		System.out.println(tablero);
		tablero.avanza();
		System.out.println("Despues del avance");
		System.out.println("Casilla origen: "+ casOrig);
		System.out.println("Casilla destino: "+ casDest);
		assertTrue(casOrig.getTipoCasilla()==TipoCasilla.JUG_NORTE && casDest.getTipoCasilla()==TipoCasilla.AGUJERO
				&& !tablero.getJugador().isTieneLingote() && !tablero.isGana() && tablero.isPierde());
	}
	
	/**
	 * El ultimo test verifica que cuando se dan las circunstancias apropiadas el jugador gana.
	 * La circunstancia es que cuando el jugador avanza hasta una casilla con el lingote de oro en posesión, el jugador gana.
	 * No se prueba cómo el jugador se hace con el lingote pues ya estáprobado en algún test anterior.
	 */
	@Test
	public void haciaSalidaConLingote() {
		System.out.println("\nhaciaSalidaConLingote");
		inicializaTableroVacio();
		tablero.getJugador().getCasilla().setCoord_X(0);
		tablero.getJugador().getCasilla().setCoord_Y(1);
		tablero.getJugador().setTieneLingote(true); 
		casOrig = tablero.getJugador().getCasilla();
		casDest=tablero.getCasilla(0, 0);
		casDest.setTipoCasila(TipoCasilla.SALIDA);
		tablero.getCasillas()[0][1]=casOrig;
		System.out.println("Antes del avance");
		System.out.println(tablero);
		tablero.avanza();
		System.out.println("Despues del avance\n");
		System.out.println("Casilla origen: "+ casOrig);
		System.out.println("Casilla destino: "+ casDest);
		assertTrue(casOrig.getTipoCasilla()==TipoCasilla.JUG_NORTE && casDest.getTipoCasilla()==TipoCasilla.SALIDA 
				&& tablero.getJugador().isTieneLingote() && tablero.isGana() && !tablero.isPierde());

	}
}
