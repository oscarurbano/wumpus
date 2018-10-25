package es.ourbanog.data;

import java.io.Serializable;
import java.util.Random;

import es.ourbanog.game.Direccion;
import es.ourbanog.game.ResultAccion;

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

	private boolean gana=false;

	private boolean pierde=false;
	
	public Tablero() {
		inicializa();
	}
	
	public Tablero(int dimension, int numAgujeros, int numFlechas){
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
	public void inicializa() {
		casillas = new Casilla[dimension][dimension];
		int coordX=-1;
		int coordY=-1;
		
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
		if(pared == Direccion.NORTE.getId()) {
			casillas[laOtraCoordenada][0] = new Casilla(laOtraCoordenada,0,TipoCasilla.SALIDA);
		}else if(pared == Direccion.SUR.getId()) {
			casillas[laOtraCoordenada][dimension -1] =  new Casilla(laOtraCoordenada, dimension+-1,TipoCasilla.SALIDA);
		}else if(pared == Direccion.ESTE.getId()) {
			casillas[dimension-1][laOtraCoordenada] =  new Casilla(dimension-1,laOtraCoordenada,TipoCasilla.SALIDA);
		}else if(pared == Direccion.OESTE.getId()) {
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
	
	/**
	 * Dibuja el tablero en modo texto mostrando las casillas y su contenido
	 */
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
	
	/**
	 * 
	 * @return Si hay un wumpru en alguna casilla vecina a la que se encuentra el jugador.
	 */
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
	
	/**
	 * 
	 * @return Si hay un agujero en alguna casilla vecina a la que se encuentra el jugador.
	 */
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
	/**
	 * 
	 * @return Si hay un lingote de horo en alguna casilla vecina a la que se encuentra el jugador.
	 */
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
	
	/**
	 * 
	 * @return Si hay una pared en alguna casilla vecina a la que se encuentra el jugador.
	 */
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
	/**
	 * Imprime las sensaciones que se perciben en la casilla donde se encuentra el jugador (hedor, fresco, resplandor, huele a yeso) 
	 */
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
	
	/**
	 * Gira a la derecha 90 grados al usuario
	 * @return Devuelve hacia donde mira el usuario (norte, sur, este, oeste)
	 */

	public TipoCasilla giraJugadorDerecha() {
		this.jugador.giraDerecha();
		return this.jugador.getCasilla().getTipoCasilla();
	}
	
	/**
	 * Gira a la izquierda 90 grados al usuario
	 * @return Devuelve hacia donde mira el usuario (norte, sur, este, oeste)
	 */

	public TipoCasilla giraJugadorIzquierda() {
		this.jugador.giraIzda();
		return this.jugador.getCasilla().getTipoCasilla();
	}
	
	/**
	 * Avanza una casilla hacia la dirección donde está enfocado el usuario. Puede encontrarse en una pared y no pueda avanzar, o con el WUMUS muero y
	 * tampoco puea avanzar. Puedeencontrarse con un uagujero y Pierde la partida o se encuentra con el Wumpus vivo y también pierde la partida.
	 * Puede acceder a la salida y se le avisa si tiene el lingote o no Si no tiene el lingote no puede salir, si tiene el lingote puede salir y gana la partida
	 * @return
	 */
	
	@SuppressWarnings("incomplete-switch")
	public ResultAccion avanza() {
		TipoCasilla dir = jugador.getCasilla().getTipoCasilla();
		Casilla orig = jugador.getCasilla();
		Casilla dest = jugador.getCasilla();
		switch(dir){
			case JUG_NORTE:
				dest=getCasilla(orig.getCoord_X(), orig.getCoord_Y()-1);
				break;
			case JUG_SUR:
				dest=getCasilla(orig.getCoord_X(), orig.getCoord_Y()+1);
				break;
			case JUG_EST:
				dest=getCasilla(orig.getCoord_X()+1, orig.getCoord_Y());
				break;
			case JUG_OEST:
				dest=getCasilla(orig.getCoord_X()-1, orig.getCoord_Y());
				break;
		}
		ResultAccion resultado=null;
		boolean cambiarTiposCasillas = false;
		if(null==dest) {
			resultado=ResultAccion.FUERA_PARED;
		}
		if(null!=dest && dest.getTipoCasilla()==TipoCasilla.PARED) {
			resultado= ResultAccion.EN_PARED;
			cambiarTiposCasillas=true;
		}
		if(null!=dest && dest.getTipoCasilla()==TipoCasilla.AGUJERO) {
			resultado= ResultAccion.AGUJERO;
			this.pierde=true;			
		}
		if(null!=dest && dest.getTipoCasilla()==TipoCasilla.SALIDA) {			
			resultado= jugador.isTieneLingote() ? ResultAccion.SAL_CON_LING : ResultAccion.SAL_SIN_LING;
			this.gana=jugador.isTieneLingote()?true:false;
		}
		if(null!=dest && dest.getTipoCasilla()==TipoCasilla.WUMPUS_MUERTO) {
			resultado= ResultAccion.WUMPUS_MUERTO;
		}
		if(null!=dest && dest.getTipoCasilla()==TipoCasilla.WUMPUS_VIVO) {
			resultado= ResultAccion.WUMPUS_VIVO;
			this.pierde=true;
		}
		if(null!=dest && dest.getTipoCasilla()==TipoCasilla.VACIA) {
			resultado= ResultAccion.VACIA;
			cambiarTiposCasillas=true;
		}
		if(null!=dest && dest.getTipoCasilla()==TipoCasilla.LINGOTE) {
			resultado= ResultAccion.LINGOTE;
			jugador.setTieneLingote(true);
			cambiarTiposCasillas=true;
		}
		if(cambiarTiposCasillas) {
			//La casilla destino es de tipo jugador ahora
			dest.setTipoCasila(dir);
			//La casilla de origen ahora será vacía (si no está en el perímetro) o pared (si está en el perímetro).
			if(orig.getCoord_X()==0 || orig.getCoord_X() == this.dimension-1 ||orig.getCoord_X()==0 || orig.getCoord_X() == this.dimension-1 ) { //Esta en el perímetro
				orig.setTipoCasila(TipoCasilla.PARED);
			}else { // No está en el perímetro.
				orig.setTipoCasila(TipoCasilla.VACIA);
			}
			jugador.setCasilla(dest);
		}
		return resultado;
	}

	@SuppressWarnings("incomplete-switch")
	public ResultAccion tiraFlecha() {
		ResultAccion resultado = null;
		boolean wumpusMuerto=false;
		if(jugador.getNumFlechas()==0) {
			return ResultAccion.SIN_FLECHAS;
		}
		int numflechas = jugador.getNumFlechas();
		jugador.setNumFlechas(--numflechas);
		
		Casilla casillaAux= null;
		switch(jugador.getCasilla().getTipoCasilla()) {
			case JUG_NORTE:
				for(int i = jugador.getCasilla().getCoord_Y()-1; i >=0; i--) {
					casillaAux=getCasilla(jugador.getCasilla().getCoord_X(), i);
					if(null!=casillaAux) {
						if(casillaAux.getTipoCasilla()==TipoCasilla.WUMPUS_VIVO) {
							casillaAux.setTipoCasila(TipoCasilla.WUMPUS_MUERTO);
							resultado=ResultAccion.FLECHA_OK;
							wumpusMuerto=true;
							break;
						}
					}
				}
				if (!wumpusMuerto) {
					resultado=ResultAccion.FLECHA_NOK;
				}
				break;
			case JUG_SUR:
				for(int i = jugador.getCasilla().getCoord_Y()+1; i < this.dimension; i++) {
					casillaAux=getCasilla(jugador.getCasilla().getCoord_X(), i);
					if(null!=casillaAux) {
						if(casillaAux.getTipoCasilla()==TipoCasilla.WUMPUS_VIVO) {
							casillaAux.setTipoCasila(TipoCasilla.WUMPUS_MUERTO);
							resultado=ResultAccion.FLECHA_OK;
							wumpusMuerto=true;
							break;
						}
					}
				}
				if (!wumpusMuerto) {
					resultado=ResultAccion.FLECHA_NOK;
				} 
				break;
			case JUG_EST:
				for(int i = jugador.getCasilla().getCoord_X()+1; i < this.dimension; i++) {
					casillaAux=getCasilla(i,jugador.getCasilla().getCoord_Y());
					if(null!=casillaAux) {
						if(casillaAux.getTipoCasilla()==TipoCasilla.WUMPUS_VIVO) {
							casillaAux.setTipoCasila(TipoCasilla.WUMPUS_MUERTO);
							resultado=ResultAccion.FLECHA_OK;
							wumpusMuerto=true;
							break;
						}
					}
				}
				if (!wumpusMuerto) {
					resultado=ResultAccion.FLECHA_NOK;
				} 
				break;
			case JUG_OEST:
				for(int i = jugador.getCasilla().getCoord_X()-1; i >= 0; i--) {
					casillaAux=getCasilla(i,jugador.getCasilla().getCoord_Y());
					if(null!=casillaAux) {
						if(casillaAux.getTipoCasilla()==TipoCasilla.WUMPUS_VIVO) {
							casillaAux.setTipoCasila(TipoCasilla.WUMPUS_MUERTO);
							resultado=ResultAccion.FLECHA_OK;
							wumpusMuerto=true;
							break;
						}
					}
				}
				if (!wumpusMuerto) {
					resultado=ResultAccion.FLECHA_NOK;
				} 
				break;
		}
		return resultado;
	}
	
	public boolean isGana() {
		return gana;
	}

	public boolean isPierde() {
		return pierde;
	}

	
	
	
}
