package mx.qr.sace.persistencia.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import mx.qr.core.persistencia.GenericoDAO;
import mx.qr.core.persistencia.JPADAO;
import mx.qr.sace.persistencia.dao.DocumentoEntregadoDAO;
import mx.qr.sace.persistencia.entidades.DocumentoEntregado;

/**
 * Implementacion
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">
 * @since Febrero 2016
 * @copyright Q & R
 */
@JPADAO
public class DocumentoEntregadoDAOImpl extends GenericoDAO<DocumentoEntregado> implements
		DocumentoEntregadoDAO {

	public DocumentoEntregadoDAOImpl(EntityManager em) {
		super(em);
	}

	@Override
	public DocumentoEntregado buscaPorId(Integer id) {
		return null;
	}

	@Override
	public void modifica(DocumentoEntregado entity) {

	}

	@Override
	public void elimina(DocumentoEntregado entity) {

	}

	@Override
	public List<DocumentoEntregado> obtieneTodoPorCriteria(Object... criteria) {
		return null;
	}

}
