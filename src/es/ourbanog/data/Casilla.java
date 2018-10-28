package es.ourbanog.data;

import java.io.Serializable;

/**
 * Clase que modela una casilla dentro del tablero.
 * Indica las coordenadas dentro del tablero, el tipo de objeto (wumpus, salida, lingotem agujero) que tiene (si alguno)
 * y los aromas que se perciben según el tipo de las casillas vecinas
 * 
 * @see IAromas
 * @author ourbanog
 *
 */
public class Casilla implements Serializable {

	private static final long serialVersionUID = -709206441201241627L;

	//Las coordenadas X e Y que ocupan en el tablero. La X crece de izquierda a derecha, la Y crece de ariba a abajo.
	private int coord_X;
	private int coord_Y;
	// Indica qué hay en la casilla (wumpus, lingote, salida, agujero, nada, etc)
	private TipoCasilla tipoCasila;
	
	public Casilla(int coord_X, int coodd_Y, TipoCasilla tipoCasila) {
		super();
		this.coord_X = coord_X;
		this.coord_Y = coodd_Y;
		this.tipoCasila = tipoCasila;
	}
	
	public int getCoord_X() {
		return coord_X;
	}
	public void setCoord_X(int coord_X) {
		this.coord_X = coord_X;
	}
	public int getCoord_Y() {
		return coord_Y;
	}
	public void setCoord_Y(int coord_Y) {
		this.coord_Y = coord_Y;
	}
	public TipoCasilla getTipoCasilla() {
		return tipoCasila;
	}
	public void setTipoCasila(TipoCasilla tipoCasilla) {
		this.tipoCasila = tipoCasilla;
	}
	public boolean isEmpty() {
		return this.tipoCasila==TipoCasilla.VACIA;
	}
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer().append("X = ").append(coord_X).append( " Y = ").append(coord_Y).append(" Tipo: ").append(tipoCasila.name());
		return buffer.toString();
	}
}
