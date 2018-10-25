package es.ourbanog.game;

import es.ourbanog.data.Tablero;
import es.ourbanog.util.UserInput;

/** 
 * Esta clase modela las reglas de juego de las partidas del wunpus. Además de los métodos de cobertura (inicializar, solicitar comando, dar resultado
 * @author ourbanog
 *
 */
public class Juego {

	private Tablero tablero;
	
	
	/**
	 * Inicializa el juego. Solicita al usuario el numero de agujeros, las flechas y la dimensión del tablero y assí se inicializará el tablero y le
	 * propondrá las acciones que puede hacer el usuario.
	 */
	public void inicializa() {
		int dimension=0;
		int numFlechas=0;
		int numAgujeros=0;
		boolean isCorrectaEntrada = false;
		System.out.println("Bienvenidos a wumpus, ese maravilloso juego");
		
		while(!isCorrectaEntrada) {
			System.out.println("Introduzca la dimansión del tablero, al menos 4"); 
			dimension = UserInput.leeOpcion();
			if(dimension < 4) {
				System.out.println("Dimensión incorrecta inténtalo otra vez");
			}else {
				isCorrectaEntrada=true;
			}
		}
		
		isCorrectaEntrada=false;
		while(!isCorrectaEntrada) {
			System.out.println("Introduzca el numero de agujeros en el tablero. NumAgujeros+3 >= dimension*dimension");
			numAgujeros= UserInput.leeOpcion();
			if(numAgujeros+3 >= dimension*dimension) {
				System.out.println("El numero de agujeros no es correcto. Inténtalo otra vez");
			}
			else {
				isCorrectaEntrada=true;
			}
		}
		
		System.out.println("Introduzca el numero de flechas del jugador");
		numFlechas=UserInput.leeOpcion();
		
		this.tablero=new Tablero(dimension, numAgujeros, numFlechas);
		System.out.printf("Enhorabuena!!! El juego se ha inicializado con una dimension %d, con %d agujeros y con %d flechas para que intentes matar al wumus\n",
				dimension, numAgujeros, numFlechas);
	}
	
	/**
	 * Imprime las diferentes opciones que tiene el uuario jugador para hacer
	 */
	
	private void muestraOpciones() {
		System.out.println("Estas son las opciones que tienes disponibles:");
		for (Jugada jugada : Jugada.values()) {
			System.out.println(jugada.getId()+". "+jugada.getDescripcion());			
		}
		System.out.println("elija opción");
	}
	
	/**
	 * 
	 * @return El movimiento seleccionado por el usuario
	 */
	private Jugada obtenJugada() {
		boolean entradaCorrecta=false;
		while(!entradaCorrecta) {
			int opcion = UserInput.leeOpcion();
			if(opcion==1) {
				return Jugada.GIRO_IZDA;
			}
			if(opcion==2) {
				return Jugada.GIRO_DCHA; 
			}
			if(opcion==3) {
				return Jugada.IMP_TAB; 
			}
			if(opcion==4) {
				return Jugada.TIR_FLECHA; 
			}
			if(opcion==5) {
				return Jugada.AVANCE; 
			}
			if(opcion==6) {
				return Jugada.IMP_IMPR; 
			}
			if(opcion==7) {
				return Jugada.SALIR; 
			}
			System.out.println("La opción seleccionada no es correcta. Intentalo otra vez");
			muestraOpciones();
		}
		
		return null;
	}
	
	/**
	 * Iterativamente solicita al usuario el movimiento que quiera realizar y mientras no 
	 * seleccione salir modifica adecuadamente el estado del juego (jugador y tablero) 
	 * dandole al usuario el feedback correspondiente 
	 * <ul>
	 * <li>has muerto</li><li>has salido</li><li>has matado al wumpus</li>
	 * <li>has cogido el lingote</li>
	 * <ul>
	 */
	public void juega() {
		boolean salirDefinitivamente=false;			
		while(!salirDefinitivamente) {
			boolean sigoJugando=true;
			while (sigoJugando) {
				muestraOpciones();	
				Jugada jugada = obtenJugada();
				switch (jugada){
					case GIRO_DCHA:
						System.out.println(tablero.giraJugadorDerecha().getDescripcion());
						break;
					case GIRO_IZDA:
						System.out.println(tablero.giraJugadorIzquierda().getDescripcion());
						break;
					case AVANCE:
						System.out.println(tablero.avanza().getDescipcion());
						if(tablero.isGana() || tablero.isPierde()) {
							sigoJugando=false;
						}
						break;
					case IMP_IMPR:
						tablero.informaSensaciones();
						break;
					case IMP_TAB:
						tablero.dibuja();
						break;
					case TIR_FLECHA:
						System.out.println(tablero.tiraFlecha().getDescipcion());
						break;
					default:
						sigoJugando=false;
						break;				
				}
				if(jugada!=Jugada.IMP_IMPR && sigoJugando ) {
					tablero.informaSensaciones();
				}
			}
			System.out.println("¿Está seguro que quieres dejar el juego? (1-SI/0-NO)");
			if(UserInput.leeOpcion()==1) {
				salirDefinitivamente=true;
			}
		}
		System.out.println("Espero que hayas disfrutado. Esperamos verte pronto");
		
	}
	
	public static void main (String[] args) {
		Juego juego = new Juego();
		juego.inicializa();
		juego.juega();
	}

	
}
