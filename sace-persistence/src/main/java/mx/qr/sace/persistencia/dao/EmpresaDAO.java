package mx.qr.sace.persistencia.dao;

import mx.qr.core.persistencia.EntidadDAO;
import mx.qr.sace.persistencia.entidades.Empresa;
import mx.qr.sace.persistencia.entidades.EmpresaConvenio;

/**
 * Definicion del acceso a datos para la tabla Empresas
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Noviembre 2015
 * @copyright Q & R
 */
public interface EmpresaDAO extends EntidadDAO<Object[], Empresa> {
	
	/**
	 * Obtiene el registro de convenio de la tabla c_empresa_convenio
	 * 
	 * @param idEmpresa
	 * @return
	 */
	public EmpresaConvenio obtenConvenio(int idEmpresa);
}
