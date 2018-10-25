package es.ourbanog.game;

/**
 * Modela las diferentes direcciones geográficas para ver hacia dónde está viendo el jugador, hacia dónde puede avanzar, y la dirección que
 * tomarña la flecha cuando se lance.
 * @author ourbanog
 *
 */

public enum Direccion {
	NORTE(0,"Norte"), SUR(1,"Sur"), ESTE(2,"Este"), OESTE(3,"Oeste");
	private int id;
	private String descripcion;
	Direccion(int id, String descripcion){
		this.id = id;
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
