package mx.qr.sace.persistencia.dao;

import mx.qr.sace.persistencia.entidades.Tramite;


/**
 * Definicion del acceso a los registros para todos los catalogos en el sistema que no requieran de un DAO formal.<br/>
 * En este DAO se accedera a diferentes tablas catalogos.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Diciembre 2015
 * @copyright Q & R
 */
public interface CatalogosDAO {
	
	/**
	 * Obtiene el tramite por el identificador.
	 * @param pk
	 * @return
	 */
	public Tramite obtenTramitePorId(int pk);
}
