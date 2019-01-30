package mx.qr.sace.finanzas.negocio;

import javax.ejb.Local;

import mx.qr.core.exception.ApplicationException;
import mx.qr.sace.persistencia.entidades.FichaAcademica;
import mx.qr.sace.persistencia.entidades.PagoDiverso;

/**
 * Define el negocio del registro de los pagos variados de conceptos.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Febrero 2017
 * @copyright Q & R
 */
@Local
public interface RegistroPagosVariosLocal {

	/**
	 * Realiza el registro del pago del concepto.
	 * 
	 * @param La información del pagos
	 * @param La ficha academica asaociada al pago
	 * @throws ApplicationException
	 */
	public void realizaPago(PagoDiverso pago, FichaAcademica fichaAcademica) throws ApplicationException;

	public void actualizaPago();
	
	public void calcelaPago();
	
}