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
@Table(name = "conceptos_ficha_pago", catalog = "SACE_DB")
public class ConceptoFichaPago implements java.io.Serializable, Entidad {

	private static final long serialVersionUID = -4062148375611986859L;
	
//	@Id
//	@GeneratedValue(strategy = IDENTITY)
//	@Column(name = "id_concepto", unique = true, nullable = false)
//	private int idConcepto;
	
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "idFichaPago", column = @Column(name = "id_ficha_pago", nullable = false)),
			@AttributeOverride(name = "idTramite", column = @Column(name = "id_tramite", nullable = false)) })
	private ConceptoId id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ficha_pago", nullable = false,insertable=false, updatable=false)	
	private FichaPago fichaPago;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tramite", nullable = false,insertable=false, updatable=false)	
	private Tramite tramite;
	
	@Column(name = "monto")
	private Float monto;
	
	public ConceptoFichaPago() {
	}

//	public int getIdConcepto() {
//		return this.idConcepto;
//	}
//
//	public void setIdConcepto(int idConcepto) {
//		this.idConcepto = idConcepto;
//	}

	/**
	 * @param id
	 */
	public ConceptoFichaPago(ConceptoId id) {
		super();
		this.id = id;
	}

	public Float getMonto() {
		return this.monto;
	}

	public void setMonto(Float monto) {
		this.monto = monto;
	}

	public FichaPago getFichaPago() {
		return fichaPago;
	}

	public void setFichaPago(FichaPago fichaPago) {
		this.fichaPago = fichaPago;
	}

	public Tramite getTramite() {
		return tramite;
	}

	public void setTramite(Tramite tramite) {
		this.tramite = tramite;
	}

	public ConceptoId getId() {
		return id;
	}

	public void setId(ConceptoId id) {
		this.id = id;
	}

	@Override
	public void setUsuario(String usuario) {
		
	}

	@Override
	public void setFechaHora(Date fecha) {
		
	}
}
