package es.ourbanog.play;

public enum Direccion {
	NORTE(0), SUR(1), ESTE(2), OESTE(3);
	private int direccion;
	Direccion(int dir){
		this.direccion = dir;
	}
	public int getDireccion() {
		return this.direccion;
	}
}
