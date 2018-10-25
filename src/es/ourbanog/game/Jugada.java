package es.ourbanog.game;

/**
 * Modela las acciones que puede realizar un usuario jugador con el juego
 * @author ourbanog
 *
 */

public enum Jugada {
	GIRO_IZDA(1,"Girar a la izquierda"), GIRO_DCHA(2,"Girar a la derecha"), IMP_TAB(3,"Imprimir tablero"), TIR_FLECHA(4,"Tirar flecha"), 
	AVANCE(5,"Avanzar"),IMP_IMPR(6,"Imprimir sensaciones"),SALIR(7,"Salir");
	
	private int id;
	private String descripcion;
	
	 Jugada(int id, String descripcion) {
		this.id=id;
		this.descripcion=descripcion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
