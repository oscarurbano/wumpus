package es.ourbanog.data;

public enum TipoCasilla {
	VACIA("VA", "Vacia"), WUMPUS_VIVO("WV","WUMPUS vivo"), WUMPUS_MUERTO("WM","WUMPUS muerto"), AGUJERO("AG","Agujero"), PARED("PA","Pared"), 
	LINGOTE("LG","Lingote de oro"), SALIDA("SA","salida"), JUG_NORTE("JN","El jugador está mirando al norte"), JUG_SUR("JS","El jugador está mirando al sur"), 
	JUG_EST("JE","El jugador está mirando al este"), JUG_OEST("JO","El jugador está mirando al oeste");
	private String abr;
	private String descripcion;
	 
	private TipoCasilla(String abr, String descripcion) {
		this.abr=abr;
		this.descripcion=descripcion;
	}

	public String getAbr() {
		return abr;
	}

	public void setAbr(String abr) {
		this.abr = abr;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
}
