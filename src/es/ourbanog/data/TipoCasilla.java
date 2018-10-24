package es.ourbanog.data;

public enum TipoCasilla {
	VACIA("VA"), WUMPUS_VIVO("WV"), WUMPUS_MUERTO("WM"), AGUJERO("AG"), PARED("PA"), LINGOTE("LG"), SALIDA("SA"),
	JUG_NORTE("JN"), JUG_SUR("JS"), JUG_EST("JE"), JUG_OEST("JO");
	private String abr;
	 
	private TipoCasilla(String abr) {
		this.abr=abr;
	}


	public String getAbr() {
		return abr;
	}


	public void setAbr(String abr) {
		this.abr = abr;
	}
	
}
