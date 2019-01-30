package mx.qr.sace.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mx.qr.core.persistencia.Entidad;

/**
 * DocumentoEntregado
 */
@Entity
@Table(name = "documentos_entregados", catalog = "SACE_DB")
public class DocumentoEntregado implements Serializable, Entidad {

	/**
	 * 
	 */
	private static final long serialVersionUID = 608333332197506759L;
	
	private int idDocumentoEntregado;
	
	/** El documento entregado */
	private Requisito documento;
	private DatosPersona datoPersonal;
	private Date fechaRecepcion;

	public DocumentoEntregado() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_documento_entregado", unique = true, nullable = false)
	public int getIdDocumentoEntregado() {
		return this.idDocumentoEntregado;
	}

	public void setIdDocumentoEntregado(int idDocumentoEntregado) {
		this.idDocumentoEntregado = idDocumentoEntregado;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_requisito", nullable = false)
	public Requisito getDocumento() {
		return this.documento;
	}

	public void setDocumento(Requisito documentoOficial) {
		this.documento = documentoOficial;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_dato_personal", nullable = false)
	public DatosPersona getDatoPersonal() {
		return this.datoPersonal;
	}

	public void setDatoPersonal(DatosPersona datoPersonal) {
		this.datoPersonal = datoPersonal;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_recepcion", nullable = false, length = 10)
	public Date getFechaRecepcion() {
		return this.fechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idDocumentoEntregado;
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
		DocumentoEntregado other = (DocumentoEntregado) obj;
		if (idDocumentoEntregado != other.idDocumentoEntregado)
			return false;
		return true;
	}

	@Override
	public void setUsuario(String usuario) {
	}

	@Override
	public void setFechaHora(Date fecha) {
	}

}
