package mx.qr.sace.persistencia.dao;

import java.util.Date;

import javax.persistence.EntityManager;

import mx.qr.core.persistencia.Entidad;
import mx.qr.core.persistencia.GenericoDAO;
import mx.qr.core.persistencia.JPADAO;
import mx.qr.sace.persistencia.entidades.DatoHistAcademico;
import mx.qr.sace.persistencia.entidades.DatoLaboral;
import mx.qr.sace.persistencia.entidades.DatoSalud;
import mx.qr.sace.persistencia.entidades.DatosPersona;


/**
 * Class concreta para el acceso a datos para las tablas de datos_*. Esto es:
 * datos_personales
 * datos_salud
 * datos_his_academica
 * datos_laborales.
 * 
 * No se requiere una interface para simplificar el desarrollo. 
 * 
 * La justificacion es que no habra mas implementaciones de este comportamiento
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Febrero 2016
 * @copyright Q & R
 */
@JPADAO
public class DatoGeneralDAO extends GenericoDAO<Entidad> {

	/**
	 * @param em
	 */
	public DatoGeneralDAO(EntityManager em) {
		super(em);
	}
	
	public void guardaDatoSalud(DatoSalud entidad) {
		entidad.setFechaHora(new Date());
		super.guarda(entidad);
	}
	
//	DatosPersona dp = prospecto.getDatoPersona();
//	dp.setFechaHora(new Date());
//	em.persist(dp);
//
//	DatoHistAcademico dha = fichaAcademica.getDatoHistAcademico();
//	dha.setFechaHora(new Date());
//	em.persist(dha);
//	
//	// Agregar el Dato laboral del responsable
//	DatoLaboral dl = responsablePago.getDatoLaboral();
//	dl.setFechaHora(new Date());
//	em.persist(dl);
	
}
