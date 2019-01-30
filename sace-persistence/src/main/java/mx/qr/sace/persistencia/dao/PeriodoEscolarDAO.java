package mx.qr.sace.persistencia.dao;

import java.util.List;

import mx.qr.core.persistencia.EntidadDAO;
import mx.qr.sace.persistencia.PeriodoEscolar;
import mx.qr.sace.persistencia.entidades.CicloEscolar;

/**
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Mayo 2016
 * @copyright Q & R
 */
public interface PeriodoEscolarDAO extends EntidadDAO<Integer, PeriodoEscolar>{

	/**
	 * Guarda los periodos de un ciclo
	 * @param cic
	 * @param periodos
	 */
	public void guarda(CicloEscolar cic, List<PeriodoEscolar> periodos);
}
