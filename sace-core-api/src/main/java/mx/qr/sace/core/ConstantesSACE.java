package mx.qr.sace.core;

import mx.qr.sace.persistencia.entidades.TipoFichaPago;

/**
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Julio 2016
 * @copyright Q & R
 */
public interface ConstantesSACE {

	/**
	 * Identificadores de las constantes de los tramites
	 */
	public static final int IDENTIFICADOR_INSCRIPCION = 1;
	public static final int IDENTIFICADOR_MENSUALIDAD = 4;
	public static final int IDENTIFICADOR_UNIFORME = 5;
	
	/** La ficha de inscripcion */
	public static final TipoFichaPago FICHA_INSCRIPCION = new TipoFichaPago(1);
	
	/** Codigo de error para indica que la excepcion es una excepcion no fatal*/
	public static final int ERROR_RECUPERABLE = 1;
}
