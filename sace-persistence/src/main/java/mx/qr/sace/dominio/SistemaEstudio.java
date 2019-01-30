package mx.qr.sace.dominio;

import java.io.Serializable;

import mx.qr.sace.persistencia.entidades.Escolaridad;
import mx.qr.sace.persistencia.entidades.Modalidad;

/**
 * Representa a un Sistema de Estudios del instituto.
 * 
 * Al concepto que se forma del conjunto de Escolaridad y Modalidad
 * se le conoce como Sistema de Estudios.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Noviembre 2015
 * @copyright Q & R
 */
public class SistemaEstudio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4979877883142418275L;

	/* La escolaridad seleccionada del menu */
	private Escolaridad escolaridad;
	
	/* La modalidad seleccionada del menu */
	private Modalidad modalidad; 

	/**
	 * 
	 */
	public SistemaEstudio() {
		super();
	}

	/**
	 * @param escolaridad
	 * @param modalidad
	 */
	public SistemaEstudio(Escolaridad escolaridad, Modalidad modalidad) {
		this();
		this.escolaridad = escolaridad;
		this.modalidad = modalidad;
	}

	public Escolaridad getEscolaridad() {
		return escolaridad;
	}

	public void setEscolaridad(Escolaridad escolaridadSeleccionada) {
		this.escolaridad = escolaridadSeleccionada;
	}

	public Modalidad getModalidad() {
		return modalidad;
	}

	public void setModalidad(Modalidad modalidadSeleccionada) {
		this.modalidad = modalidadSeleccionada;
	}
}
