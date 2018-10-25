package es.ourbanog.game;

public enum ResultAccion {
	FUERA_PARED (1,"El jugador estaba en una pared y no se mueve"), 
	EN_PARED(2,"El jugador ha llegado a una pared"), 
	AGUJERO(3,"El jugador ha encontrado un agujero y cae. Lo sentimos!!!"), 
	WUMPUS_VIVO(4,"El jugador ha encontrado el WUmpus. Lamentablemente estaba vivo y se come al jugador. Lo sentimos!!!"),
	WUMPUS_MUERTO(5,"El jugador ha encontrado el Wumpus pero está muerto. Menos mal. El jugador no avanza"), 
	LINGOTE(6,"El jugador ha encontrado el lingote de oro. Enhorabuena!!"), 
	FLECHA_NOK(7,"El jugador ha matado al wumpus. Enhorabuena"), 
	FLECHA_OK(8,"El jugador ha matado al wumpus. Enhorabuena"),
	SIN_FLECHAS(9,"El jugador no tiene flechas para lanzar"),
	SAL_CON_LING(10,"El jugador ha encontrado la salida. ENHORABUENA EL JUGADOR HA SALIDO CON EL LINGOTE"),
	SAL_SIN_LING(11,"El jugador ha encontrado la salida. Pero el jugador no tiene el lingote y no puede salir"),
	VACIA(12,"El jugador ha avanzado una casilla");
	
	private int id;
	private String descipcion;

	ResultAccion(int id, String descripcion) {
		this.id=id;
		this.descipcion=descripcion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescipcion() {
		return descipcion;
	}

	public void setDescipcion(String descipcion) {
		this.descipcion = descipcion;
	}
	
}
