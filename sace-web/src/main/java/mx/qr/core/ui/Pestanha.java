package mx.qr.core.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Resprenta a una pestaña dentro del modelo del big menu
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">
 * @since Julio 2014
 * @copyright Direccion de sistemas - IFE
 */
public class Pestanha implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** El nombre de la pestaña */
	private String nombre;
	
	/** Los etapas que tiene la pestanha */
	private List<Etapa> etapas = new ArrayList<Etapa>();

	public Pestanha(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}

	public List<Etapa> getEtapas() {
		return etapas;
	}

	public void setEtapas(List<Etapa> etapas) {
		this.etapas = etapas;
	}

	@Override
	public String toString() {
		return "Pestanha [nombre=" + nombre + ", etapas=" + etapas + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pestanha other = (Pestanha) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
}
