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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.qr.core.persistencia.Entidad;
import mx.qr.sace.dominio.EntidadBase;

/**
 * Class que representa un responsable de hacer los pagos.
 */
@Entity
@Table(name = "responsables_pago", catalog = "SACE_DB")
@AttributeOverrides({
	@AttributeOverride(name = "usuario", 
		column = @Column(name = "usuario", nullable = false, length = 45)),
	@AttributeOverride(name = "fechaHora", 
		column = @Column(name = "fecha_hora", nullable = false))
})
public class ResponsablePago extends EntidadBase implements java.io.Serializable, Entidad {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2058106006698612952L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_responsable_pago", unique = true, nullable = false)
	private int idTutor;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_dato_laboral", nullable = false)
	private DatoLaboral datoLaboral;
	
	@Column(name = "nombre_tutor")
	private String nombre;
	
	@Column(name = "ape_paterno_tutor")
	private String apePaterno;
	
	@Column(name = "ape_materno_tutor")
	private String apeMaterno;
	
	@Column(name = "celular_tutor")
	private String celular;
	
	@Column(name = "correo_tutor", length = 45)
	private String correo;

	public ResponsablePago() {
	}

	public int getIdTutor() {
		return idTutor;
	}

	public void setIdTutor(int idTutor) {
		this.idTutor = idTutor;
	}

	public DatoLaboral getDatoLaboral() {
		return datoLaboral;
	}

	public void setDatoLaboral(DatoLaboral datoLaboral) {
		this.datoLaboral = datoLaboral;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApePaterno() {
		return apePaterno;
	}

	public void setApePaterno(String apePaterno) {
		this.apePaterno = apePaterno;
	}

	public String getApeMaterno() {
		return apeMaterno;
	}

	public void setApeMaterno(String apeMaterno) {
		this.apeMaterno = apeMaterno;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}


}
