package es.ourbanog.data;

import java.io.Serializable;
import java.util.Random;

import javax.net.ssl.HandshakeCompletedEvent;

import es.ourbanog.play.Direccion;

/**
 * Modela el tablero donde se desarrolla la partida. Tiene las casillas (y lo que contiene), tiene la facultad de dibujarsse (por si se desquicia el jugador) y 
 * de inicializarse seg�n par�metros.
 * 
 * @author ourbanog
 *
 */


public class Tablero implements Serializable {

	private static final long serialVersionUID = -8476575433674525676L;
	
	private Casilla[][]casillas;
	private Jugador jugador;
	private int dimension=4;
	private int numAgujeros=2;
	private int numFlechas=1;
	
	public Tablero() throws Exception {
		inicializa();
	}
	public Tablero(int dimension, int numAgujeros, int numFlechas) throws Exception {
		this.dimension=dimension;
		this.numAgujeros=numAgujeros;
		this.numFlechas=numFlechas;
		inicializa();
	}

	/**
	 * Devuelve la casilla en la posicón (x,y)
	 */
	public Casilla getCasilla(int x, int y) {
		Casilla resultado = null;
		try {
			resultado=casillas[x][y];
		}catch (Exception e) {
			resultado=null;
		}
		return resultado;
	}
	
	/**
	 * Configura un tablero de juego en funci�n de sus par�metros (miembros)
	 * @throws Exception 
	 */
	public void inicializa() throws Exception {
		casillas = new Casilla[dimension][dimension];
		int coordX=-1;
		int coordY=-1;
		
		
		if(numAgujeros+3 >= dimension*dimension) {
			throw new Exception ("No hay suficientes casillas para inicilizar el tablero como se requiere");
		}
		
		//Comenzamos poniendo todas las casillas vacias y pared
		
		for (int x=0;x<dimension;x++) {
			for (int y=0;y<dimension;y++) {
				if(x ==0 || y == 0 || x == dimension -1 || y == dimension -1 ) {
					casillas[x][y]=new Casilla(x,y,TipoCasilla.PARED);
				}else {
					casillas[x][y]=new Casilla(x,y,TipoCasilla.VACIA);
				}
			}
		}
		
		
		/* Con Ramdom, cuando pido un nuevo n�mero aleatorio le paso la dimensi�n y me dara un numero entre 0 y dimensi�n -1 lo que 
		 * no tengo que comprobar si la casilla est� dentro o fuera del tablero( si estuviera fuera no ser�a correcta) Siempre est� dentro
		 */
		Random rndm = new Random();
		
		//SALIDA. Vemos por random si la salida est� en la pared norte, sur, este u oeste. Despu�s veremos en qu� punto de dicha pared se encuentra.
		int pared = rndm.nextInt(4);
		int laOtraCoordenada = rndm.nextInt(this.dimension);
		if(pared == Direccion.NORTE.getDireccion()) {
			casillas[laOtraCoordenada][0] = new Casilla(laOtraCoordenada,0,TipoCasilla.SALIDA);
		}else if(pared == Direccion.SUR.getDireccion()) {
			casillas[laOtraCoordenada][dimension -1] =  new Casilla(laOtraCoordenada, dimension+-1,TipoCasilla.SALIDA);
		}else if(pared == Direccion.ESTE.getDireccion()) {
			casillas[dimension-1][laOtraCoordenada] =  new Casilla(dimension-1,laOtraCoordenada,TipoCasilla.SALIDA);
		}else if(pared == Direccion.OESTE.getDireccion()) {
			casillas[0][laOtraCoordenada] =  new Casilla(0,laOtraCoordenada,TipoCasilla.SALIDA);
		}
				
		//LINGOTE DE ORO
		boolean lingoteHoroAsignado = false;
		while (!lingoteHoroAsignado) {
			coordX=rndm.nextInt(this.dimension);
			coordY=rndm.nextInt(this.dimension);
			if(casillas[coordX][coordY].isEmpty()||casillas[coordX][coordY].getTipoCasilla()==TipoCasilla.PARED) {
				casillas[coordX][coordY] = new Casilla(coordX, coordY,TipoCasilla.LINGOTE);
				lingoteHoroAsignado=true;
			}			
		}

		//WUMPUS (vivo o muerto)
		boolean wumpusAsignado = false;
		while (!wumpusAsignado) {
			coordX=rndm.nextInt(this.dimension); 
			coordY=rndm.nextInt(this.dimension);
			if(casillas[coordX][coordY].isEmpty()||casillas[coordX][coordY].getTipoCasilla()==TipoCasilla.PARED) {
				casillas[coordX][coordY] = new Casilla(coordX, coordY,TipoCasilla.WUMPUS_VIVO);
				wumpusAsignado=true;
			}			
		}
		
		//AGUJEROS
		for(int i= 0;i< numAgujeros;i++) {
			boolean agujeroAsignado = false;
			while (!agujeroAsignado) {
				coordX=rndm.nextInt(this.dimension); 
				coordY=rndm.nextInt(this.dimension);
				if(casillas[coordX][coordY].isEmpty()||casillas[coordX][coordY].getTipoCasilla()==TipoCasilla.PARED) {
					casillas[coordX][coordY] = new Casilla(coordX,coordY,TipoCasilla.AGUJERO);
					agujeroAsignado=true;
				}			
			}
		}
		
		// JUGADOR. Inicialmente siempre mirando al norte.
		boolean jugadorAsignado=false;
		while(!jugadorAsignado) {
			coordX=rndm.nextInt(this.dimension); 
			coordY=rndm.nextInt(this.dimension);
			if(casillas[coordX][coordY].isEmpty()||casillas[coordX][coordY].getTipoCasilla()==TipoCasilla.PARED) {
				Casilla newCasilla = new Casilla(coordX,coordY,TipoCasilla.JUG_NORTE);
				casillas[coordX][coordY]= newCasilla;
				this.jugador=new Jugador(newCasilla, this.numFlechas);
				jugadorAsignado=true;
			}
		}
	}
	
	public void dibuja() {
		
		
		for(int i=0;i<dimension;i++) { //Filas
			System.out.print("|");
			for (int x=0; x<dimension;x++) { //Primera fila
				System.out.print("-----|");
			}
			System.out.println();
			System.out.print("|");
			for (int j=0; j<dimension;j++) { //Columnas intermedias
				System.out.print("  ");
				System.out.print(casillas[j][i].getTipoCasilla().getAbr());
				System.out.print(" |");
			}
			System.out.println();
			System.out.print("|");
			for (int x=0; x<dimension;x++) { //Primera columna
				System.out.print("-----|");
			}
			System.out.println();
		}
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("PA -> Pared\n");
		strBuff.append("AG -> Agujero\n");
		strBuff.append("EX -> Salida\n");
		strBuff.append("LG -> Lingote\n");
		strBuff.append("WV -> WUMPUS Vivo\n");
		strBuff.append("WM -> WUMPUS Muerto\n");
		strBuff.append("JN -> Jugador mirando al norte\n");
		strBuff.append("JS -> Jugador mirando al sur\n");
		strBuff.append("JE -> Jugador mirando al este\n");
		strBuff.append("JO -> Jugador mirando al oeste\n");
		System.out.println();
		System.out.println(strBuff.toString());
	}
	
	public boolean haceHedor() {
		Casilla vecina = getCasilla(jugador.getCasilla().getCoord_X()-1,jugador.getCasilla().getCoord_Y());
		if(null!= vecina &&  (vecina.getTipoCasilla() == TipoCasilla.WUMPUS_VIVO || vecina.getTipoCasilla()==TipoCasilla.WUMPUS_MUERTO)) {
			return true;
		}
		vecina = getCasilla(jugador.getCasilla().getCoord_X()+1,jugador.getCasilla().getCoord_Y());
		if(null!= vecina &&  (vecina.getTipoCasilla() == TipoCasilla.WUMPUS_VIVO || vecina.getTipoCasilla()==TipoCasilla.WUMPUS_MUERTO)) {
			return true;
		}
		vecina = getCasilla(jugador.getCasilla().getCoord_X(),jugador.getCasilla().getCoord_Y()-1);
		if(null!= vecina &&  (vecina.getTipoCasilla() == TipoCasilla.WUMPUS_VIVO || vecina.getTipoCasilla()==TipoCasilla.WUMPUS_MUERTO)) {
			return true;
		}
		vecina = getCasilla(jugador.getCasilla().getCoord_X(),jugador.getCasilla().getCoord_Y()+1);
		if(null!= vecina &&  (vecina.getTipoCasilla() == TipoCasilla.WUMPUS_VIVO || vecina.getTipoCasilla()==TipoCasilla.WUMPUS_MUERTO)) {
			return true;
		}
		return false;
	}
	
	public boolean heceFresco() {
		Casilla vecina = getCasilla(jugador.getCasilla().getCoord_X()-1,jugador.getCasilla().getCoord_Y());
		if(null!= vecina &&  vecina.getTipoCasilla() == TipoCasilla.AGUJERO ){
			return true;
		}
		vecina = getCasilla(jugador.getCasilla().getCoord_X()+1,jugador.getCasilla().getCoord_Y());
		if(null!= vecina &&  vecina.getTipoCasilla() == TipoCasilla.AGUJERO ){
			return true;
		}
		vecina = getCasilla(jugador.getCasilla().getCoord_X(),jugador.getCasilla().getCoord_Y()-1);
		if(null!= vecina &&  vecina.getTipoCasilla() == TipoCasilla.AGUJERO ){
			return true;
		}
		vecina = getCasilla(jugador.getCasilla().getCoord_X(),jugador.getCasilla().getCoord_Y()+1);
		if(null!= vecina &&  vecina.getTipoCasilla() == TipoCasilla.AGUJERO ){
			return true;
		}
		return false;
	}

	public boolean hayResplandor() {
		Casilla vecina = getCasilla(jugador.getCasilla().getCoord_X()-1,jugador.getCasilla().getCoord_Y());
		if(null!= vecina &&  vecina.getTipoCasilla() == TipoCasilla.LINGOTE ){
			return true;
		}
		vecina = getCasilla(jugador.getCasilla().getCoord_X()+1,jugador.getCasilla().getCoord_Y());
		if(null!= vecina &&  vecina.getTipoCasilla() == TipoCasilla.LINGOTE ){
			return true;
		}
		vecina = getCasilla(jugador.getCasilla().getCoord_X(),jugador.getCasilla().getCoord_Y()-1);
		if(null!= vecina &&  vecina.getTipoCasilla() == TipoCasilla.LINGOTE ){
			return true;
		}
		vecina = getCasilla(jugador.getCasilla().getCoord_X(),jugador.getCasilla().getCoord_Y()+1);
		if(null!= vecina &&  vecina.getTipoCasilla() == TipoCasilla.LINGOTE ){
			return true;
		}		
		return false;
	}
	
	public boolean hueleAYeso() {
		Casilla vecina = getCasilla(jugador.getCasilla().getCoord_X()-1,jugador.getCasilla().getCoord_Y());
		if(null!= vecina &&  (vecina.getTipoCasilla() == TipoCasilla.PARED || vecina.getTipoCasilla()==TipoCasilla.SALIDA)){
			return true;
		}
		vecina = getCasilla(jugador.getCasilla().getCoord_X()+1,jugador.getCasilla().getCoord_Y());
		if(null!= vecina &&  (vecina.getTipoCasilla() == TipoCasilla.PARED || vecina.getTipoCasilla()==TipoCasilla.SALIDA)){
			return true;
		}
		vecina = getCasilla(jugador.getCasilla().getCoord_X(),jugador.getCasilla().getCoord_Y()-1);
		if(null!= vecina &&  (vecina.getTipoCasilla() == TipoCasilla.PARED || vecina.getTipoCasilla()==TipoCasilla.SALIDA)){
			return true;
		}
		vecina = getCasilla(jugador.getCasilla().getCoord_X(),jugador.getCasilla().getCoord_Y()+1);
		if(null!= vecina &&  (vecina.getTipoCasilla() == TipoCasilla.PARED || vecina.getTipoCasilla()==TipoCasilla.SALIDA)){
			return true;
		}
		return false;
	}
	public void informaSensaciones() {
		System.out.println();
		StringBuffer buffer = new StringBuffer();
		if(hueleAYeso()) {
			buffer.append("Huele a yeso, estoy cerca de una pared\n");
		}
		if(haceHedor()) {
			buffer.append("Huele fatal, esoy cerca del wumpus, no me acuerdo si lo he matado\n");
		}
		if(heceFresco()) {
			buffer.append("Hace brisita, estoy cerca de un agujero. A ver si me voy a caer\n");
		}
		if(hayResplandor()) {
			buffer.append("Ese resplandor es dorado. Puede que saque algo bueno de esto\n");
		}
		
		System.out.println(buffer.toString());
		
	}
	public static void main (String [] args) throws Exception {
		Tablero tab = new Tablero(5,3,2);
		tab.dibuja();
		tab.informaSensaciones();
	}
}
