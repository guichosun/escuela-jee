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
@Table(name = "datos_personales", catalog = "SACE_DB")
@AttributeOverrides({
	@AttributeOverride(name = "usuario", 
		column = @Column(name = "usuario", nullable = false, length = 45)),
	@AttributeOverride(name = "fechaHora", 
		column = @Column(name = "fecha_hora", nullable = false))
})
public class DatosPersona extends EntidadBase implements java.io.Serializable {

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
	
	@Column(name = "lugar_nacimiento", length = 30)
	private String lugarNacimiento;
	
	@Column(name = "curp", length = 18)
	private String curp;
	
	@Column(name = "nacionalidad", length = 45)
	private String nacionalidad;
	
	@Column(name = "edad", nullable = false)
	private Integer edad;
	
	@Column(name = "estado_civil", length = 45)
	private String estadoCivil;
	
	@Column(name = "ocupacion", length = 45)
	private String ocupacion;
	
	@Column(name = "direccion", length = 100)
	private String direccion;
	
	@Column(name = "colonia", length = 45)
	private String colonia;
	
	@Column(name = "cp", length = 10)
	private String cp;
	
	@Column(name = "ciudad", length = 45)
	private String ciudad;

	@Column(name = "delegacion", length = 45)
	private String delegacion;
	
	@Column(name = "entidad_federativa", length = 45)
	private String entidadFederativa;
	
	@Column(name = "email", length = 50)
	private String email;
	
	@Column(name = "telefono_casa", nullable = false, length = 20)
	private String telefonoCasa;
	
	@Column(name = "telefono_trabajo", length = 20)
	private String telefonoTrabajo;
	
	@Column(name = "telefono_recados", length = 20)
	private String telefonoRecados;
	
	@Column(name = "celular", nullable = false, length = 10)
	private String celular;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "genero", nullable = false, length = 2)
	private Genero genero;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "datoPersonal")
	private Set<DocumentoEntregado> documentoEntregados = new HashSet<DocumentoEntregado>(0);

	public DatosPersona() {
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

	public String getLugarNacimiento() {
		return this.lugarNacimiento;
	}

	public void setLugarNacimiento(String lugarNacimiento) {
		this.lugarNacimiento = lugarNacimiento;
	}
	
	public String getCurp() {
		return this.curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}
	
	public String getNacionalidad() {
		return this.nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Integer getEdad() {
		return this.edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	
	public String getEstadoCivil() {
		return this.estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	
	public String getOcupacion() {
		return this.ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getColonia() {
		return this.colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	
	public String getCp() {
		return this.cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}
	
	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDelegacion() {
		return this.delegacion;
	}

	public void setDelegacion(String delegacion) {
		this.delegacion = delegacion;
	}
	
	public String getEntidadFederativa() {
		return this.entidadFederativa;
	}

	public void setEntidadFederativa(String entidadFederativa) {
		this.entidadFederativa = entidadFederativa;
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
	
	public String getTelefonoTrabajo() {
		return this.telefonoTrabajo;
	}

	public void setTelefonoTrabajo(String telefonoTrabajo) {
		this.telefonoTrabajo = telefonoTrabajo;
	}
	
	public String getTelefonoRecados() {
		return this.telefonoRecados;
	}

	public void setTelefonoRecados(String telefonoRecados) {
		this.telefonoRecados = telefonoRecados;
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

	public Set<DocumentoEntregado> getDocumentoEntregados() {
		return documentoEntregados;
	}

	public void setDocumentoEntregados(Set<DocumentoEntregado> documentoEntregados) {
		this.documentoEntregados = documentoEntregados;
	}
}
