package es.ourbanog.data;

/**
 * Espacifica los aromas que el jugador puede percibir si la casilla en concreto est� vac�a pero tiene 
 * vecinas que no est�n vac�as y seg�n el tipo de contenido popondr� un aroma u otro.
 * 
 * Las casillas vecinas s�lo son las (x+1,y), las (x-1,y),las (x,y+1) y las (x,y-1) siempre que �stas 
 * coordenadas se encuentren dentro del tablero de juego.
 * @author ourbanog
 *
 */
public interface IAromas {

	/**
	 * 
	 * @return True si en alguna casilla vecina se encuentra el wumpus
	 */
	public boolean haceHedor();
	
	/**
	 * 
	 * @return True si en alguna casilla vecina se encuentra un agujero
	 */
	public boolean heceFresco();
	
	/**
	 * 
	 * @return True si en alguna casilla vecinase encuantra un lingote
	 */
	public boolean hayResplandor();
	
}
