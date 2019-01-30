package mx.qr.sace.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.qr.sace.dominio.EntidadBase;

/**
 */
@Entity
@Table(name = "preguntas_respuestas_alumno", catalog = "SACE_DB")
@AttributeOverrides({
	@AttributeOverride(name = "usuario", 
		column = @Column(name = "usuario", nullable = false, length = 45)),
	@AttributeOverride(name = "fechaHora", 
		column = @Column(name = "fecha_hora", nullable = false))
})
public class RespuestaAPregunta extends EntidadBase implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5430300585714483631L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_pra", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "id_alumno", nullable = false)
	private Integer idAlumno;
	
	@Column(name = "id_pregunta", nullable = false)
	private Integer idPregunta;
	
	@Column(name = "id_respuesta", nullable = false)
	private Integer idRespuesta;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
			@JoinColumn(name = "id_pregunta", referencedColumnName = "id_pregunta", nullable = false, updatable=false, insertable=false),
			@JoinColumn(name = "id_respuesta", referencedColumnName = "id_respuesta", nullable = false, updatable=false, insertable=false) })
	private Respuesta respuesta;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_alumno", nullable = false, updatable=false, insertable=false)
	private Alumno alumno;

	public RespuestaAPregunta() {
	}
	
	public RespuestaAPregunta(int idPregunta) {
		this();
		this.idPregunta = idPregunta;
	}

	public Respuesta getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(Respuesta respuesta) {
		this.respuesta = respuesta;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(Integer idAlumno) {
		this.idAlumno = idAlumno;
	}

	public Integer getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(Integer idPregunta) {
		this.idPregunta = idPregunta;
	}

	public Integer getIdRespuesta() {
		return idRespuesta;
	}

	public void setIdRespuesta(Integer idRespuesta) {
		this.idRespuesta = idRespuesta;
	}


}
