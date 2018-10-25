package es.ourbanog.data;

import java.io.Serializable;

/**
 * Modela el jugador.
 * Contiene una casila (donde se encuantra en cada momento) y las flechas que tiene para intentar matar al wumpus
 * @author ourbanog
 *
 */

public class Jugador implements Serializable {

	
	private static final long serialVersionUID = -8998816878413165223L;

	private Casilla casilla;
	int numFlechas;
	boolean tieneLingote=false;
	
	public Jugador(Casilla casilla, int numFlechas) {
		this.casilla=casilla;
		this.numFlechas=numFlechas;
	}

	/** 
	 * Gira a la derecha.
	 * Si el ususario está mirando al norte mirará al este si está en el este mirará al sur
	 * si está en el sur mirará hacia el oesta si está en el oeste mirará al norte
	 */
	public void giraDerecha() {
		if(this.casilla.getTipoCasilla()==TipoCasilla.JUG_NORTE) {
			this.casilla.setTipoCasila(TipoCasilla.JUG_EST);
		}else if(this.casilla.getTipoCasilla()==TipoCasilla.JUG_EST) {
			this.casilla.setTipoCasila(TipoCasilla.JUG_SUR);
		}else if(this.casilla.getTipoCasilla()==TipoCasilla.JUG_SUR) {
			this.casilla.setTipoCasila(TipoCasilla.JUG_OEST);
		}else if(this.casilla.getTipoCasilla()==TipoCasilla.JUG_OEST) {
			this.casilla.setTipoCasila(TipoCasilla.JUG_NORTE);
		} 
	}
	
	/**
	 * Gira a la izquierda.
	 * Es el metodo simétrico a void giraDerecha()
	 * 
	 * @see  void giraDerecha()
	 */
	public void giraIzda() {
		if(this.casilla.getTipoCasilla()==TipoCasilla.JUG_NORTE) {
			this.casilla.setTipoCasila(TipoCasilla.JUG_OEST);
		}else if(this.casilla.getTipoCasilla()==TipoCasilla.JUG_OEST) {
			this.casilla.setTipoCasila(TipoCasilla.JUG_SUR);
		}else if(this.casilla.getTipoCasilla()==TipoCasilla.JUG_SUR) {
			this.casilla.setTipoCasila(TipoCasilla.JUG_EST);
		}else if(this.casilla.getTipoCasilla()==TipoCasilla.JUG_EST) {
			this.casilla.setTipoCasila(TipoCasilla.JUG_NORTE);
		} 
	}

	public Casilla getCasilla() {
		return casilla;
	}

	public void setCasilla(Casilla casilla) {
		this.casilla = casilla;
	}

	public int getNumFlechas() {
		return numFlechas;
	}

	public void setNumFlechas(int numFlechas) {
		this.numFlechas = numFlechas;
	}
	
	
	public boolean isTieneLingote() {
		return tieneLingote;
	}

	public void setTieneLingote(boolean tieneLingote) {
		this.tieneLingote = tieneLingote;
	}

}
