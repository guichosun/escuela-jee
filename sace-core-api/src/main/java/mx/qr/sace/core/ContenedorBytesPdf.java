package mx.qr.sace.core;

import java.io.Serializable;

/**
 * Class que sirve como un contenedor para los arreglo de <code>byte</code> de los pdf que se generan en SACE
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Marzo 2016
 * @copyright Q & R
 */
public class ContenedorBytesPdf implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5700450853312222144L;

	private String nombre;
	
	private byte[] bytesPdf;
	
	/**
	 * Construye el contenedor con los bytes especificos. Sin un nombre para identificarlo
	 * 
	 * @param inStreamPdf
	 */
	public ContenedorBytesPdf(byte[] bytesPdf) {
		super();
		this.bytesPdf = bytesPdf;
	}

	/**
	 * Construye el contenedor con los bytes especifico
	 * @param nombre
	 * @param inStreamPdf
	 */
	public ContenedorBytesPdf(String nombre, byte[] bytesPdf) {
		this(bytesPdf);
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public byte[] getBytesPdf() {
		return bytesPdf;
	}
}
