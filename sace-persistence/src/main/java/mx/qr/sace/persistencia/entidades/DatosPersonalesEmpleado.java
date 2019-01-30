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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mx.qr.core.persistencia.Genero;
import mx.qr.sace.dominio.EntidadBase;

/**
 */
@Entity
@Table(name = "datos_personales_empleado", catalog = "SACE_DB")
@AttributeOverrides({
	@AttributeOverride(name = "usuario", 
		column = @Column(name = "usuario", nullable = false, length = 45)),
	@AttributeOverride(name = "fechaHora", 
		column = @Column(name = "fecha_hora", nullable = false))
})
public class DatosPersonalesEmpleado extends EntidadBase implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1303484425658980968L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_dato_personal", unique = true, nullable = false)
	private int idDatoPersonal;
	
	@Column(name = "nombre", nullable = false, length = 50)
	private String nombre;
	
	@Column(name = "ape_paterno", length = 50)
	private String apePaterno;
	
	@Column(name = "ape_materno", length = 50)
	private String apeMaterno;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_nacimiento", length = 10)
	private Date fechaNacimiento;
	
	@Column(name = "curp", length = 18)
	private String curp;

	@Column(name = "rfc", length = 18)
	private String rfc;
	
	@Column(name = "edad", nullable = false)
	private Integer edad;
	
	@Column(name = "direccion", length = 100)
	private String direccion;
	
	@Column(name = "email", length = 50)
	private String email;
	
	@Column(name = "telefono_casa", nullable = false, length = 20)
	private String telefonoCasa;
	
	@Column(name = "celular", nullable = false, length = 10)
	private String celular;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "genero", nullable = false, length = 2)
	private Genero genero;
	
	public DatosPersonalesEmpleado() {
	}

	public int getIdDatoPersonal() {
		return this.idDatoPersonal;
	}

	public void setIdDatoPersonal(int idDatoPersonal) {
		this.idDatoPersonal = idDatoPersonal;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApePaterno() {
		return this.apePaterno;
	}

	public void setApePaterno(String apePaterno) {
		this.apePaterno = apePaterno;
	}

	public String getApeMaterno() {
		return this.apeMaterno;
	}

	public void setApeMaterno(String apeMaterno) {
		this.apeMaterno = apeMaterno;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCurp() {
		return this.curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}
	
	public Integer getEdad() {
		return this.edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	
	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefonoCasa() {
		return this.telefonoCasa;
	}

	public void setTelefonoCasa(String telefonoCasa) {
		this.telefonoCasa = telefonoCasa;
	}
	
	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	public Genero getGenero() {
		return this.genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}
}

