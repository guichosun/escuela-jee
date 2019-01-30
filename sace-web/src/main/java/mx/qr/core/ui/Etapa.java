package mx.qr.core.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Resprenta a la etapa en el big menu
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">
 * @since Julio 2014
 * @copyright Q&R
 */
public class Etapa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** El nombre de la etapa */
	private String nombre;
	
	/** Los modulos que tiene la etapa */
	private List<Modulo> modulos = new ArrayList<Modulo>();

	public Etapa(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}

	public List<Modulo> getModulos() {
		return modulos;
	}

	public void setModulos(List<Modulo> modulos) {
		this.modulos = modulos;
	}
	
}
