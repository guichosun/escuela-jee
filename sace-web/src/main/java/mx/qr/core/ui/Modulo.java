package mx.qr.core.ui;

import java.io.Serializable;

/**
 * Resprenta una opción (modulo) en el big menu
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">
 * @since Julio 2014
 * @copyright Direccion de sistemas - IFE
 */
public class Modulo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nombre;
	
	private String liga;

	public Modulo(String nombre, String liga) {
		this.nombre = nombre;
		this.liga = liga;
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getLiga() {
		return liga;
	}

	@Override
	public String toString() {
		return "Modulo [nombre=" + nombre + ", liga=" + liga + "]";
	}
	
}
