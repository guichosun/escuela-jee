package mx.qr.sace.persistencia.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import mx.qr.core.persistencia.GenericoDAO;
import mx.qr.core.persistencia.JPADAO;
import mx.qr.sace.persistencia.dao.CarreraDAO;
import mx.qr.sace.persistencia.entidades.Carrera;

/**
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">
 * @since Agosto 10 2015
 * @copyright Q & R
 */
@JPADAO
//@Named("carreraDAOImpl")
public class CarreraDAOImpl extends GenericoDAO<Carrera> implements CarreraDAO {


	@Override
	public Carrera buscaPorId(Object[] id) {
		
		return null;
	}

	@Override
	public void modifica(Carrera entity) {
		
		
	}

	@Override
	public void elimina(Carrera entity) {
		
		
	}

	@Override
	public List<Carrera> obtieneTodoPorCriteria(Object... criteria) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sacePU");
		em = emf.createEntityManager();
		
		StringBuffer query = new StringBuffer();
		query.append("SELECT c ");
		query.append("FROM Carrera c ");
		query.append("WHERE ");
		List<String> argumentos = new ArrayList<String>();
		if (criteria[0] != null) {
			argumentos.add("c.id.idEscolaridad = :idEscolaridad");
		}
		if (criteria[0] != null) {
			argumentos.add("c.id.idModalidad = :idModalidad");
		}

		for (int i = 0; i < argumentos.size(); i++) {
			if (i > 0) {
				query.append(" AND ");
			}
			query.append(argumentos.get(i));
		}
		Query q = em.createQuery(query.toString());
		if (criteria[0] != null) {
			q.setParameter("idEscolaridad", criteria[0]);
		}
		if (criteria[1] != null) {
			q.setParameter("idModalidad", criteria[1]);
		}
		return (List<Carrera>) q.getResultList();
	}


}
