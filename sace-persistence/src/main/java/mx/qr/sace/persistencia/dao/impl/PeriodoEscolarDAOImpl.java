package mx.qr.sace.persistencia.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import mx.qr.core.persistencia.GenericoDAO;
import mx.qr.core.persistencia.JPADAO;
import mx.qr.sace.persistencia.PeriodoEscolar;
import mx.qr.sace.persistencia.dao.PeriodoEscolarDAO;
import mx.qr.sace.persistencia.entidades.CicloEscolar;

/**
 * Implementacion
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">
 * @since Mayo 2016
 * @copyright Q & R
 */
@JPADAO
public class PeriodoEscolarDAOImpl extends GenericoDAO<PeriodoEscolar> implements
		PeriodoEscolarDAO {

	/**
	 * @param em
	 */
	public PeriodoEscolarDAOImpl(EntityManager em) {
		super(em);
	}

	@Override
	public void guarda(CicloEscolar cic, List<PeriodoEscolar> periodos) {
		for(PeriodoEscolar pe : periodos) {
			pe.setCicloEscolar(cic);
			guarda(pe);
		}
	}
	
	@Override
	public void guarda(PeriodoEscolar entity) {
		entity.setFechaHora(new Date());
		super.guarda(entity);
	}

	@Override
	public PeriodoEscolar buscaPorId(Integer id) {
		return null;
	}

	@Override
	public void modifica(PeriodoEscolar entity) {

	}

	@Override
	public void elimina(PeriodoEscolar entity) {
	}

	@Override
	public List<PeriodoEscolar> obtieneTodoPorCriteria(Object... criteria) {
		return null;
	}
}
