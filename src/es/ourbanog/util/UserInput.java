package es.ourbanog.util;

import java.io.IOException;

/**
 * Una clase utilidad para leer por teclado entradas del usuario
 * 
 * @author ourbanog
 *
 */
public class UserInput {

	/**
	 * Devuelve un entero que ha introducido el usuario por teclado desde consola.
	 * 
	 * @return El entero que ha entrado el usuario
	 */
	public static int leeOpcion() {
		StringBuffer str = new StringBuffer();
		char c;
		try {
			while ((c = (char) System.in.read()) != '\n') {
					str.append(c);
			}
		} catch (IOException ex) {}
		String strLeido = str.toString();
		String numStr = strLeido.substring(0, strLeido.length()-1);
		int resultado;
		try {
			 resultado = Integer.parseInt(numStr);
		}catch(Exception e) {
			resultado = -1;
		}
		return resultado;
	}

}
