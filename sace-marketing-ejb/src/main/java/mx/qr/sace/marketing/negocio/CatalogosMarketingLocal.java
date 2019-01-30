package mx.qr.sace.marketing.negocio;

import java.util.List;

import javax.ejb.Local;

import mx.qr.core.exception.ApplicationException;
import mx.qr.core.exception.RegistroNoEncontradoException;
import mx.qr.core.persistencia.EstatusRegistro;
import mx.qr.sace.persistencia.entidades.Beca;
import mx.qr.sace.persistencia.entidades.Descuento;
import mx.qr.sace.persistencia.entidades.Empresa;
import mx.qr.sace.persistencia.entidades.EmpresaConvenio;

/**
 * Para obtener los catalogos para el modulo de Marketing.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Noviembre 2015
 * @copyright Q & R
 */
@Local
public interface CatalogosMarketingLocal {

	/**
	 * Recupera la empresa por medio de su identificador
	 * 
	 * @param id
	 * @return
	 */
	public Empresa recuperaEmpresa(int id) throws ApplicationException;
	
	/**
	 * 
	 * @return
	 */
	public List<Empresa> recuperaEmpresas();
	
	/**
	 * Recupera el convenio que tiene una empresa con el Instituto
	 * para saber la beca asociada al convenio.
	 * 
	 * @param idEmpresa
	 * @return Un ejemplar de EmpresaConvenio, de lo contrario null
	 */
	public EmpresaConvenio recuperaConvenioDeEmpresa(int idEmpresa);
	
	/**
	 * Recupera la beca mediante el id
	 * 
	 * @return
	 * @throws ApplicationException
	 */
	public Beca recuperaBeca(int idBeca) throws RegistroNoEncontradoException;
	
	/**
	 * Recupera todas las becas activas para ofrecer.
	 * 
	 * @return Todas las becas disponibles y activas.
	 * @throws ApplicationException
	 */
	public List<Beca> recuperaBecas() throws ApplicationException;
	
	/**
	 * Recupera las becas dependiendo del estatus
	 * @param estatusBeca
	 * @return
	 * @throws ApplicationException
	 */
	public List<Beca> recuperaBecas(EstatusRegistro estatusBeca) throws ApplicationException;
	
	/**
	 * Recupera los descuentos que son aplicados a una beca.
	 * Ojo Por requerimiento de la institucion siempre seran dos descuentos: Ins y Mens
	 * 
	 * @param idBeca
	 * @return El arreglo de los descuentos. con dos posiciones.
	 * @throws ApplicationException
	 */
	public Descuento[] recuperaDescuentosDeUnaBeca(int idBeca) throws ApplicationException;
}