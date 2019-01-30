package mx.qr.sace.persistencia.dao;

import mx.qr.core.persistencia.EntidadDAO;
import mx.qr.sace.persistencia.entidades.Beca;

/**
 * Definicion del acceso a datos para la tabla de Becas
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Diciembre 2015
 * @copyright Q & R
 */
public interface BecaDAO extends EntidadDAO<Integer, Beca> {
	
	/**
	 * Determina si una beca esta asociada con alguna empresa o Alumno
	 * @param beca
	 * @return
	 */
	public boolean estaBecaAsociada(Beca beca);
}
