package mx.qr.sace.marketing.ejb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.qr.core.exception.ApplicationException;
import mx.qr.core.persistencia.EstatusAlumno;
import mx.qr.sace.core.negocio.BuscarAlumnoLocal;
import mx.qr.sace.dominio.SistemaEstudio;
import mx.qr.sace.persistencia.dao.AlumnoDAO;
import mx.qr.sace.persistencia.dao.impl.AlumnoDAOImpl;
import mx.qr.sace.persistencia.entidades.Alumno;
import mx.qr.sace.persistencia.entidades.ResponsablePago;

@Stateless(name = "BuscarProspectoEJB", 
	mappedName="java:global/sace-ear/sace-marketing-ejb/BuscarProspectoEJB")
public class BuscarProspectoEJB implements BuscarAlumnoLocal {

//	@JPADAO
	public AlumnoDAO prospectoDAO;
	
	@PersistenceContext(unitName = "sacePU")
	protected EntityManager em;
	
	@PostConstruct
	public void init() {
		prospectoDAO = new AlumnoDAOImpl(em);
	}

	/**
	 * @see BuscarAlumnoLocal#recuperaPorMatricula(SistemaEstudio, EstatusAlumno, String)
	 */
	public Alumno recuperaPorMatricula(SistemaEstudio sisEstudio,
			EstatusAlumno estatus, String matricula) throws ApplicationException {
		
		List<Alumno> alumnos = prospectoDAO.obtieneTodoPorCriteria(
				sisEstudio.getEscolaridad().getIdEscolaridad(),
				sisEstudio.getModalidad().getIdModalidad(), 
				estatus, matricula);
		
		if(alumnos.size() > 0) {
			Alumno a = alumnos.get(0);
			ResponsablePago rp = a.getResponsable();
			rp.getIdTutor();
			rp.getApeMaterno();
			return a;
		}
		// No Encontro nada
		return null;
	}
}
