package mx.qr.sace.persistencia.entidades;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

import mx.qr.core.persistencia.Entidad;
import mx.qr.sace.dominio.EntidadBase;

/**
 */
@Entity
@Table(name = "datos_laborales", catalog = "SACE_DB")
@AttributeOverrides({
	@AttributeOverride(name = "usuario", 
		column = @Column(name = "usuario", nullable = false, length = 45)),
	@AttributeOverride(name = "fechaHora", 
		column = @Column(name = "fecha_hora", nullable = false))
})
public class DatoLaboral extends EntidadBase implements java.io.Serializable, Entidad {

	private static final long serialVersionUID = 2478898413689213407L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_dato_laboral", unique = true, nullable = false)
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;
	
	@Column(name = "cual_empresa", length = 50)
	private String cualEmpresa;
	
	@Column(name = "lada_trabajo")
	private String ladaTrabajo;
	
	@Column(name = "telefono_trabajo", nullable=true, length = 45)
	private String telefonoTrabajo;
	
	@Column(name = "extension_trabajo", length = 10)
	private String extensionTrabajo;
	
	@Column(name = "correo_trabajo", length = 50)
	private String correoTrabajo;
	
	public DatoLaboral() {
	}
	
	/**
	 * @param usuario
	 */
	public DatoLaboral(String usuario) {
		super(usuario);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getCualEmpresa() {
		return cualEmpresa;
	}

	public void setCualEmpresa(String cualEmpresa) {
		this.cualEmpresa = cualEmpresa;
	}

	public String getLadaTrabajo() {
		return ladaTrabajo;
	}

	public void setLadaTrabajo(String ladaTrabajo) {
		this.ladaTrabajo = ladaTrabajo;
	}

	public String getTelefonoTrabajo() {
		return telefonoTrabajo;
	}

	public void setTelefonoTrabajo(String telefonoTrabajo) {
		this.telefonoTrabajo = telefonoTrabajo;
	}

	public String getExtensionTrabajo() {
		return extensionTrabajo;
	}

	public void setExtensionTrabajo(String extensionTrabajo) {
		this.extensionTrabajo = extensionTrabajo;
	}

	public String getCorreoTrabajo() {
		return correoTrabajo;
	}

	public void setCorreoTrabajo(String correoTrabajo) {
		this.correoTrabajo = correoTrabajo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DatoLaboral other = (DatoLaboral) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
