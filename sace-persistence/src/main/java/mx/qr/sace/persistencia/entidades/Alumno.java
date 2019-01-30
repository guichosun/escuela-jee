package mx.qr.sace.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mx.qr.core.persistencia.Entidad;
import mx.qr.core.persistencia.EstatusAlumno;
import mx.qr.core.util.Utilerias;
import mx.qr.sace.dominio.EntidadBase;

/**
 */
@Entity
@Table(name = "alumnos", catalog = "SACE_DB")
@AttributeOverrides({
	@AttributeOverride(name = "usuario", 
		column = @Column(name = "usuario", nullable = false, length = 45)),
	@AttributeOverride(name = "fechaHora", 
		column = @Column(name = "fecha_hora", nullable = false))
})
public class Alumno extends EntidadBase implements java.io.Serializable, Entidad {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1843809296466098537L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_alumno", unique = true, nullable = false)
	private int idAlumno;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_dato_personal", nullable = false)
	private DatosPersona datoPersona;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_responsable_pago", nullable = false)
	private ResponsablePago responsable;
	
	@Column(name = "matricula", nullable = false, length = 20)
	private String matricula;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_registro", nullable = false)
	private Date fechaRegistro;
	
	@Column(name = "tiene_tutor")
	private boolean tieneTutor;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "estatus")
	private EstatusAlumno estatus;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "alumno")
	private Set<FichaAcademica> fichas = new HashSet<FichaAcademica>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "alumno")
	private Set<RespuestaAPregunta> respuestasAPreguntas = new HashSet<RespuestaAPregunta>(0);
	
	public Alumno() {
	}
	
	/**
	 * Metodo helper que ayuda a regresar una cadena formateada con la matricula
	 *  y el nombre completo del alumno
	 *  
	 * @return Una etiqueta formateada con MATRICULA - NOMBRE COMPLETO
	 */
	public String etiquetaMatriculaYNombre() {
		String r = "";
		if(datoPersona != null) {
			StringBuilder sb = new StringBuilder();
			sb.append(getMatricula()).append(" - ");
			
			sb.append(Utilerias.estaVacio(datoPersona.getApePaterno())
					? "" :datoPersona.getApePaterno()).append(" ");
			
			sb.append(Utilerias.estaVacio(datoPersona.getApeMaterno())
					? "" :datoPersona.getApeMaterno()).append(" ");
			
			sb.append(datoPersona.getNombre());
			
			r = sb.toString();
		}
		return r;
	}
	
	public int getIdAlumno() {
		return this.idAlumno;
	}

	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}

	public DatosPersona getDatoPersona() {
		return this.datoPersona;
	}

	public void setDatoPersona(DatosPersona auxDatoPersonal) {
		this.datoPersona = auxDatoPersonal;
	}

	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public EstatusAlumno getEstatus() {
		return estatus;
	}

	public void setEstatus(EstatusAlumno estatus) {
		this.estatus = estatus;
	}

	public ResponsablePago getResponsable() {
		return responsable;
	}

	public void setResponsable(ResponsablePago tutor) {
		this.responsable = tutor;
	}

	public Set<FichaAcademica> getFichas() {
		return fichas;
	}

	public void setFichas(Set<FichaAcademica> fichas) {
		this.fichas = fichas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + idAlumno;
		result = prime * result
				+ ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		if (idAlumno != other.idAlumno)
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Alumno [\nidAlumno=" + idAlumno + "\nmatricula=" + matricula
				+ "]";
	}

	public boolean isTieneTutor() {
		return tieneTutor;
	}

	public void setTieneTutor(boolean tieneTutor) {
		this.tieneTutor = tieneTutor;
	}

	public Set<RespuestaAPregunta> getRespuestasAPreguntas() {
		return respuestasAPreguntas;
	}

	public void setRespuestasAPreguntas(Set<RespuestaAPregunta> respuestasAPreguntas) {
		this.respuestasAPreguntas = respuestasAPreguntas;
	}
}
