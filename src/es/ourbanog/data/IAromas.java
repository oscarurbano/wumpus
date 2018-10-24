package es.ourbanog.data;

/**
 * Espacifica los aromas que el jugador puede percibir si la casilla en concreto está vacía pero tiene 
 * vecinas que no están vacías y según el tipo de contenido popondrá un aroma u otro.
 * 
 * Las casillas vecinas sólo son las (x+1,y), las (x-1,y),las (x,y+1) y las (x,y-1) siempre que éstas 
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
