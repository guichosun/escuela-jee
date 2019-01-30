package mx.qr.sace;

import mx.qr.sace.persistencia.entidades.TipoFichaPago;

/**
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Julio 2016
 * @copyright Q & R
 */
public interface Constantes {
	public static final int INSCRIPCION = 1;
	public static final int MENSUALIDAD = 4;
	
	/** La ficha de inscripcion */
	public static final TipoFichaPago FICHA_INSCRIPCION = new TipoFichaPago(1); 
}
