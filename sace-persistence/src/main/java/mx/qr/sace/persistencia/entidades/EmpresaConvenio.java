package mx.qr.sace.persistencia.entidades;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.qr.core.persistencia.Entidad;

/**
 */
@Entity
@Table(name = "c_empresas_convenio", catalog = "SACE_DB")
public class EmpresaConvenio implements java.io.Serializable, Entidad {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8764554956027550932L;
	
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "idEmpresa", column = @Column(name = "id_empresa", nullable = false)),
			@AttributeOverride(name = "idBeca", column = @Column(name = "id_beca", nullable = false)) })
	private EmpresaConvenioId id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_beca", nullable = false, insertable=false, updatable=false)
	private Beca beca;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_empresa", nullable = false, insertable=false, updatable=false)
	private Empresa empresa;
	
	@Column(name = "nombre_contacto", length = 45)
	private String nombreContacto;
	
	@Column(name = "telefono", length = 45)
	private String telefono;

	public EmpresaConvenio() {
	}


	@Override
	public void setUsuario(String usuario) {
		
	}

	@Override
	public void setFechaHora(Date fecha) {
		
	}


	public EmpresaConvenioId getId() {
		return id;
	}


	public void setId(EmpresaConvenioId id) {
		this.id = id;
	}


	public Beca getBeca() {
		return beca;
	}


	public void setBeca(Beca beca) {
		this.beca = beca;
	}


	public Empresa getEmpresa() {
		return empresa;
	}


	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}


	public String getNombreContacto() {
		return nombreContacto;
	}


	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
