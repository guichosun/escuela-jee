package mx.qr.sace.marketing.negocio;

import javax.ejb.Local;

import mx.qr.core.exception.ApplicationException;
import mx.qr.core.exception.RegistroAsociadoException;
import mx.qr.core.exception.RegistroNoEncontradoException;
import mx.qr.sace.persistencia.entidades.Beca;
import mx.qr.sace.persistencia.entidades.Descuento;

/**
 * El negocio del modulo de administracion de Marketing.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Diciembre 2015
 * @copyright Q & R
 */
@Local
public interface AdministracionMarketingLocal {

	/**
	 * Registra una beca en la aplicacion
	 * 
	 * @param beca
	 * @param descuentos
	 * @throws ApplicationException
	 */
	public void registraBeca(Beca beca, Descuento[] descuentos) throws ApplicationException;

	/**
	 * 
	 * @param operacion
	 * @param beca
	 * @param descuentos
	 * @throws RegistroAsociadoException
	 * @throws RegistroNoEncontradoException
	 */
	public void alteraBeca(int operacion, Beca beca, Descuento[] descuentos)
			throws RegistroAsociadoException, RegistroNoEncontradoException;
	
	/**
	 * 
	 * @param beca
	 * @throws RegistroAsociadoException
	 * @throws RegistroNoEncontradoException
	 */
	public void eliminaBeca(Beca beca) throws RegistroAsociadoException, RegistroNoEncontradoException;
	
	
	public void registraConvenio() throws ApplicationException;
}