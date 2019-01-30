package mx.qr.sace.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import mx.qr.sace.dominio.EntidadBase;

/**
 */
@Entity
@Table(name = "fichas_pagos", catalog = "SACE_DB")
@AttributeOverrides({
	@AttributeOverride(name = "usuario", 
		column = @Column(name = "usuario", nullable = false, length = 45)),
	@AttributeOverride(name = "fechaHora", 
		column = @Column(name = "fecha_hora", nullable = false))
})
public class FichaPago extends EntidadBase implements java.io.Serializable, Entidad {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4459649621168344844L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_ficha_pago", unique = true, nullable = false)
	private int idFichaPago;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ficha_academica", nullable = false)
	private FichaAcademica fichaAcademica;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_tipo_ficha", nullable = false)
	private TipoFichaPago tipoFichaPago;

	@Column(name = "nombre", length = 45)
	private String nombre;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_elaboracion", nullable = false, length = 19)
	private Date fechaElaboracion;
	
	@Column(name = "vendedor")
	private Integer vendedor;
	
	@Column(name = "total", precision = 10)
	private Float total;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fichaPago")
	private Set<ConceptoFichaPago> conceptoFichaPagos = new HashSet<ConceptoFichaPago>(0);

	public FichaPago() {
	}

	public Set<ConceptoFichaPago> getConceptos() {
		return this.conceptoFichaPagos;
	}

	public void setConceptos(Set<ConceptoFichaPago> conceptoFichaPagos) {
		this.conceptoFichaPagos = conceptoFichaPagos;
	}
	
	public int getIdFichaPago() {
		return this.idFichaPago;
	}

	public void setIdFichaPago(int idFichaPago) {
		this.idFichaPago = idFichaPago;
	}
	
	public FichaAcademica getFichaAcademica() {
		return this.fichaAcademica;
	}

	public void setFichaAcademica(FichaAcademica fichaAcademica) {
		this.fichaAcademica = fichaAcademica;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Date getFechaElaboracion() {
		return this.fechaElaboracion;
	}

	public void setFechaElaboracion(Date fechaElaboracion) {
		this.fechaElaboracion = fechaElaboracion;
	}

	public Integer getVendedor() {
		return this.vendedor;
	}

	public void setVendedor(Integer vendedor) {
		this.vendedor = vendedor;
	}


	public Float getTotal() {
		return this.total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public TipoFichaPago getTipoFichaPago() {
		return tipoFichaPago;
	}

	public void setTipoFichaPago(TipoFichaPago tipoFichaPago) {
		this.tipoFichaPago = tipoFichaPago;
	}
}
