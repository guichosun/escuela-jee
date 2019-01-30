package mx.qr.sace.ce.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mx.qr.sace.ce.negocio.CatalogosCELocal;
import mx.qr.sace.persistencia.dao.CarreraDAO;
import mx.qr.sace.persistencia.entidades.Carrera;
import mx.qr.sace.persistencia.entidades.Escolaridad;
import mx.qr.sace.persistencia.entidades.Modalidad;
import mx.qr.sace.persistencia.entidades.TramiteCarrera;

/**
 * Inplementacion del negocio para los catalogos del control escolar.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del
 *         Campo</a>
 * @since Julio 2015
 * @copyright Q & R
 */
@Stateless(name = "catalogosControlEscolar")
public class CatalogosCEEJB implements CatalogosCELocal {

//	@Inject
//	@JPADAO
	private CarreraDAO carreraDAO;

	@PersistenceContext(unitName = "sacePU")
	private EntityManager em;

	public List<Escolaridad> obtenEscolaridades() {
		
		// TODO Sacarlos de la BD
		List<Escolaridad> regs = new ArrayList<Escolaridad>();
		regs.add(new Escolaridad("Bachillerato", 1));
		regs.add(new Escolaridad("Licenciatura", 2));
		regs.add(new Escolaridad("Maestría", 3));
		
		return regs;
	}

	@Override
	public List<Modalidad> obtenModalidadesPorEscolaridad(int idEscolaridad) {
		List<Modalidad> regs = new ArrayList<Modalidad>();
		
		// Para bachillerato hay Escolarizado y ejecutivo
		if(idEscolaridad == 1) {
			regs.add(new Modalidad(1, "Escolarizado"));
			regs.add(new Modalidad(2, "Ejecutivo"));
			regs.add(new Modalidad(3, "Linea"));
		}
		if(idEscolaridad == 2) {
			regs.add(new Modalidad(1, "Escolarizado"));
			regs.add(new Modalidad(2, "Ejecutivo"));
			regs.add(new Modalidad(3, "Linea"));
		}
		if(idEscolaridad == 3) {
			regs.add(new Modalidad(2, "Ejecutivo"));
		}
		return regs;
	}
	
	public Carrera obtenCarrera(int idCarrera, int idEscolaridad,
			int idModalidad) {
		List<Carrera> carreras = obtenCarrerasPorEscolaridadYModalidad(idEscolaridad, idModalidad);
		for(Carrera c : carreras) {
			if(c.getId().getIdCarrera() == idCarrera) {
				for(TramiteCarrera tc : c.getTramites()) {
					System.out.println(tc.getCuota());
					System.out.println(tc.getTramite().getIdTramite());
					System.out.println(tc.getTramite().getTipoTramite());
				}
				return c;
			}
		}
		return null;
	}

	public List<Carrera> obtenCarrerasPorEscolaridadYModalidad(
			Integer idEscolaridad, Integer idModalidad) {

		StringBuffer query = new StringBuffer();
		query.append("SELECT c ");
		query.append("FROM Carrera c ");
		query.append("WHERE ");
		List<String> criteria = new ArrayList<String>();
		if (idEscolaridad != null) {
			criteria.add("c.id.idEscolaridad = :idEscolaridad");
		}
		if (idModalidad != null) {
			criteria.add("c.id.idModalidad = :idModalidad");
		}

		for (int i = 0; i < criteria.size(); i++) {
			if (i > 0) {
				query.append(" AND ");
			}
			query.append(criteria.get(i));
		}
		Query q = em.createQuery(query.toString());
		if (idEscolaridad != null) {
			q.setParameter("idEscolaridad", idEscolaridad);
		}
		if (idModalidad != null) {
			q.setParameter("idModalidad", idModalidad);
		}
		return (List<Carrera>) q.getResultList();
	}
	// public List<Carrera> obtenCarrerasPorEscolaridadYModalidad(
	// Integer idEscolaridad, Integer idModalidad) {
	// // return null;
	// CriteriaBuilder cb = em.getCriteriaBuilder();
	// CriteriaQuery<Carrera> cq = cb.createQuery(Carrera.class);
	// Root<Carrera> car = cq.from(Carrera.class);
	// EntityType<Carrera> carr_ = car.getModel();
	// cq = cq.select(car);
	// Join<Carrera,Escolaridad> project =
	// car.join("escolaridad", JoinType.LEFT);
	// // cq = cq.where(cb.equal(car.get("id.idEscolaridad"), idEscolaridad));
	//
	// List<Predicate> criteria = new ArrayList<Predicate>();
	// if (idEscolaridad != null) {
	// ParameterExpression<Integer> p = cb.parameter(Integer.class,
	// "idEscolaridad");
	// criteria.add(cb.equal(car.get("id").get("idEscolaridad"), p));
	// }
	// if (idModalidad != null) {
	// ParameterExpression<Integer> p = cb.parameter(Integer.class,
	// "idModalidad");
	// criteria.add(cb.equal(car.get("id").get("idModalidad"), p));
	// }
	//
	// if (criteria.size() == 0) {
	// throw new RuntimeException("no criteria");
	// } else if (criteria.size() == 1) {
	// cq.where(criteria.get(0));
	// } else {
	// cq.where(cb.and(criteria.toArray(new Predicate[0])));
	// }
	//
	// TypedQuery<Carrera> q = em.createQuery(cq);
	// if(idEscolaridad != null) {
	// q.setParameter("idEscolaridad", idEscolaridad);
	// }
	// if(idModalidad != null) {
	// q.setParameter("idModalidad", idModalidad);
	// }
	// return q.getResultList();
	// // carr.add(new Carrera(new CarreraId(1, 1, 1), "Diseño Gráfico"));
	// // carr.add(new Carrera(new CarreraId(2, 2, 1), "Pedagogía"));
	// // carr.add(new Carrera(new CarreraId(3, 2, 1), "Psicología"));
	// // return carr;
	//
	// // return carreraDAO.obtieneTodoPorCriteria(idEscolaridad, idModalidad);
	// }
}
