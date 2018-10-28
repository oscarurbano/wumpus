package es.ourbanog.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import es.ourbanog.data.Casilla;
import es.ourbanog.data.Tablero;
import es.ourbanog.data.TipoCasilla;

/**
 * Este test verifica si los tiros con flechas se comporta como se espera.
 * 		Si el jugador está perfilado hacia el norte y el wumpus está en la misma coordenada X que el jugador pero con menor coordenada Y. Wumpus muere.
 * 		Si el jugador está perfilado hacia el norte y el wumpus está en la misma coordenada X que el jugador pero con mayor coordenada Y. Wumpus vive.
 * 		Si el jugador está perfilado hacia el este y el wumpus está en la misma coordenada Y que el jugador pero con mayor coordenada X. Wumpus muere.
 * 		Si el jugador está perfilado hacia el este y el wumpus está en la misma coordenada Y que el jugador pero con menor coordenada Y. Wumpus vive
 * 		Si el jugador está perfilado hacia el sur y el wumpus está en la misma coordenada X que el jugador pero con mayor coordenada Y. Wumpus muere.
 * 		Si el jugador está perfilado hacia el sur y el wumpus está en la misma coordenada X que el jugador pero con menor coordenada Y. Wumpus vive.
 * 		Si el jugador está perfilado hacia el oeste y el wumpus está en la misma coordenada Y que el jugador pero con menor coordenada X. Wumpus muere.
 * 		Si el jugador está perfilado hacia el oeste y el wumpus está en la misma coordenada Y que el jugador pero con mayor coordenada X. Wumpus vive.
 * 		Si el Wumpus no coincide en ninguna coordenada con el jugador el wumpus vive.
 * El jugador siempre tiene que tener alguna flecha para poder lanzarla
 * El jugador siempre que lanza una flecha le queda una menos en stock independientemente de si acierta o no el tiro.
 * @author ourbanog
 *
 */
public class TirosFlechas {
	private Tablero tablero;
	Casilla casJugador;
	Casilla casWumpus;
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
	@Test
	public void JugNorteWumpusMasNorteMismaX() {
		System.out.println("\nJugNorteWumpusMasNorteMismaX");
		inicializaTableroVacio();
		casJugador=tablero.getJugador().getCasilla();
		casJugador.setCoord_X(2);
		casJugador.setCoord_Y(2);
		tablero.getCasillas()[2][2]=casJugador;
		casWumpus=tablero.getCasilla(2, 0);//Puede que no sea wumpus vivo
		casWumpus.setTipoCasila(TipoCasilla.WUMPUS_VIVO);//Ahora si que es wumpus vivo.
		System.out.println("Antes del tiro");
		System.out.println(tablero);
		tablero.tiraFlecha();
		assertTrue(casWumpus.getTipoCasilla()==TipoCasilla.WUMPUS_MUERTO);
	}
	@Test
	public void JugNorteWumpusMasSurMismaX() {
		System.out.println("\nJugNorteWumpusMasSurMismaX");
		inicializaTableroVacio();
		casJugador=tablero.getJugador().getCasilla();
		casJugador.setCoord_X(2);
		casJugador.setCoord_Y(2);
		tablero.getCasillas()[2][2]=casJugador;
		casWumpus=tablero.getCasilla(2, tablero.getDimension()-1);//Puede que no sea wumpus vivo
		casWumpus.setTipoCasila(TipoCasilla.WUMPUS_VIVO);//Ahora si que es wumpus vivo.
		System.out.println("Antes del tiro");
		System.out.println(tablero);
		tablero.tiraFlecha();
		assertTrue(casWumpus.getTipoCasilla()==TipoCasilla.WUMPUS_VIVO);
	}
	
	@Test
	public void JugEsteWumpusMasEsteMismaY() {
		System.out.println("\nJugEsteWumpusMasEsteMismaY");
		inicializaTableroVacio();
		casJugador=tablero.getJugador().getCasilla();
		casJugador.setCoord_X(2);
		casJugador.setCoord_Y(2);
		casJugador.setTipoCasila(TipoCasilla.JUG_EST);
		tablero.getCasillas()[2][2]=casJugador;
		casWumpus=tablero.getCasilla(tablero.getDimension()-1, 2);//Puede que no sea wumpus vivo
		casWumpus.setTipoCasila(TipoCasilla.WUMPUS_VIVO);//Ahora si que es wumpus vivo.
		System.out.println("Antes del tiro");
		System.out.println(tablero);
		tablero.tiraFlecha();
		assertTrue(casWumpus.getTipoCasilla()==TipoCasilla.WUMPUS_MUERTO);
	}
	
	@Test
	public void JugEsteWumpusMasOesteMismaY() {
		System.out.println("\nJugEsteWumpusMasOesteMismaY");
		inicializaTableroVacio();
		casJugador=tablero.getJugador().getCasilla();
		casJugador.setCoord_X(2);
		casJugador.setCoord_Y(2);
		casJugador.setTipoCasila(TipoCasilla.JUG_EST);
		tablero.getCasillas()[2][2]=casJugador;
		casWumpus=tablero.getCasilla(0, 2);//Puede que no sea wumpus vivo
		casWumpus.setTipoCasila(TipoCasilla.WUMPUS_VIVO);//Ahora si que es wumpus vivo.
		System.out.println("Antes del tiro");
		System.out.println(tablero);
		tablero.tiraFlecha();
		assertTrue(casWumpus.getTipoCasilla()==TipoCasilla.WUMPUS_VIVO);
	}
	
	@Test
	public void JugSurWumpusMasSurMismaX() {
		System.out.println("\nJugSurWumpusMasSurMismaX");
		inicializaTableroVacio();
		casJugador=tablero.getJugador().getCasilla();
		casJugador.setCoord_X(2);
		casJugador.setCoord_Y(2);
		casJugador.setTipoCasila(TipoCasilla.JUG_SUR);
		tablero.getCasillas()[2][2]=casJugador;
		casWumpus=tablero.getCasilla(2, tablero.getDimension()-1);//Puede que no sea wumpus vivo
		casWumpus.setTipoCasila(TipoCasilla.WUMPUS_VIVO);//Ahora si que es wumpus vivo.
		System.out.println("Antes del tiro");
		System.out.println(tablero);
		tablero.tiraFlecha();
		assertTrue(casWumpus.getTipoCasilla()==TipoCasilla.WUMPUS_MUERTO);
	}
	
	@Test
	public void JugSurWumpusMasNorteMismaX() {
		System.out.println("\nJugSurWumpusMasNorteMismaX");
		inicializaTableroVacio();
		casJugador=tablero.getJugador().getCasilla();
		casJugador.setCoord_X(2);
		casJugador.setCoord_Y(2);
		casJugador.setTipoCasila(TipoCasilla.JUG_SUR);
		tablero.getCasillas()[2][2]=casJugador;
		casWumpus=tablero.getCasilla(2, 0);//Puede que no sea wumpus vivo
		casWumpus.setTipoCasila(TipoCasilla.WUMPUS_VIVO);//Ahora si que es wumpus vivo.
		System.out.println("Antes del tiro");
		System.out.println(tablero);
		tablero.tiraFlecha();
		assertTrue(casWumpus.getTipoCasilla()==TipoCasilla.WUMPUS_VIVO);
	}
	
	@Test
	public void JugOesteWumpusMasOesteMismaY() {
		System.out.println("\nJugOesteWumpusMasOesteMismaY");
		inicializaTableroVacio();
		casJugador=tablero.getJugador().getCasilla();
		casJugador.setCoord_X(2);
		casJugador.setCoord_Y(2);
		casJugador.setTipoCasila(TipoCasilla.JUG_OEST);
		tablero.getCasillas()[2][2]=casJugador;
		casWumpus=tablero.getCasilla(0, 2);//Puede que no sea wumpus vivo
		casWumpus.setTipoCasila(TipoCasilla.WUMPUS_VIVO);//Ahora si que es wumpus vivo.
		System.out.println("Antes del tiro");
		System.out.println(tablero);
		tablero.tiraFlecha();
		assertTrue(casWumpus.getTipoCasilla()==TipoCasilla.WUMPUS_MUERTO);
	}
	
	@Test
	public void JugOesteWumpusMenosOesteMismaY() {
		System.out.println("\nJugOesteWumpusMenosOesteMismaY");
		inicializaTableroVacio();
		casJugador=tablero.getJugador().getCasilla();
		casJugador.setCoord_X(2);
		casJugador.setCoord_Y(2);
		casJugador.setTipoCasila(TipoCasilla.JUG_OEST);
		tablero.getCasillas()[2][2]=casJugador;
		casWumpus=tablero.getCasilla(2, tablero.getDimension()-1);//Puede que no sea wumpus vivo
		casWumpus.setTipoCasila(TipoCasilla.WUMPUS_VIVO);//Ahora si que es wumpus vivo.
		System.out.println("Antes del tiro");
		System.out.println(tablero);
		tablero.tiraFlecha();
		assertTrue(casWumpus.getTipoCasilla()==TipoCasilla.WUMPUS_VIVO);
	}
	
	@Test
	public void JugComoseaWumpusDiferentesCoordenadasXY() {
		System.out.println("\nJugComoseaWumpusDiferentesCoordenadasXY");
		inicializaTableroVacio();
		casJugador=tablero.getJugador().getCasilla();
		casJugador.setCoord_X(2);
		casJugador.setCoord_Y(2);
		tablero.getCasillas()[2][2]=casJugador;
		casWumpus=tablero.getCasilla(0, tablero.getDimension()-1);//Puede que no sea wumpus vivo
		casWumpus.setTipoCasila(TipoCasilla.WUMPUS_VIVO);//Ahora si que es wumpus vivo.
		System.out.println("Antes del tiro");
		System.out.println(tablero);
		tablero.tiraFlecha();
		assertTrue(casWumpus.getTipoCasilla()==TipoCasilla.WUMPUS_VIVO);
	}

}
