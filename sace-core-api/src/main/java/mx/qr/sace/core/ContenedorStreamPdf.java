package mx.qr.sace.core;

import java.io.InputStream;
import java.io.Serializable;

/**
 * Class que sirve como un contenedor para las stream de los pdf que se generan en SACE
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Marzo 2016
 * @copyright Q & R
 */
public class ContenedorStreamPdf implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5700450853312486144L;

	private String nombre;
	
	private InputStream inStreamPdf;
	
	/**
	 * Construye el contenedor con el strean especifico. Sin un nombre para identificarlo
	 * 
	 * @param inStreamPdf
	 */
	public ContenedorStreamPdf(InputStream inStreamPdf) {
		super();
		this.inStreamPdf = inStreamPdf;
	}

	/**
	 * Construye el contenedor con el contenedor especifico
	 * @param nombre
	 * @param inStreamPdf
	 */
	public ContenedorStreamPdf(String nombre, InputStream inStreamPdf) {
		this(inStreamPdf);
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public InputStream getInStreamPdf() {
		return inStreamPdf;
	}

}
