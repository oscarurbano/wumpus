package es.ourbanog.data;

import java.io.Serializable;

public class Jugador implements Serializable {

	
	private static final long serialVersionUID = -8998816878413165223L;

	private Casilla casilla;
	int numFlechas;
	
	public Jugador(Casilla casilla, int numFlechas) {
		this.casilla=casilla;
		this.numFlechas=numFlechas;
	}

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
	
	

}
