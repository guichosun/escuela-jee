package mx.qr.sace.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.qr.core.persistencia.Entidad;
import mx.qr.core.persistencia.TipoModulo;

/**
 */
@Entity
@Table(name = "comentarios", catalog = "SACE_DB")
public class Comentario implements java.io.Serializable, Entidad {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4062148375611986859L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_comentario", unique = true, nullable = false)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_alumno", nullable = false)	
	private Alumno alumno;
	
	@Column(name = "comentario")
	private String comentario;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "modulo")
	private TipoModulo modulo;

	public Comentario() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public TipoModulo getModulo() {
		return modulo;
	}

	public void setModulo(TipoModulo modulo) {
		this.modulo = modulo;
	}

	@Override
	public void setUsuario(String usuario) {
		
		
	}

	@Override
	public void setFechaHora(Date fecha) {
		
		
	}
}