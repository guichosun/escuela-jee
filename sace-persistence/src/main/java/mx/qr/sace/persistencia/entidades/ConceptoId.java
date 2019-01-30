package mx.qr.sace.persistencia.entidades;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 */
@Embeddable
public class ConceptoId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1101039984318529699L;
	
	@Column(name = "id_ficha_pago", nullable = false)
	private int idFichaPago;
	
	@Column(name = "id_tramite", nullable = false)
	private int idTramite;

	public ConceptoId() {
	}

	/**
	 * @param idFichaPago
	 * @param idTramite
	 */
	public ConceptoId(int idFichaPago, int idTramite) {
		super();
		this.idFichaPago = idFichaPago;
		this.idTramite = idTramite;
	}


	public int getIdFichaPago() {
		return idFichaPago;
	}

	public void setIdFichaPago(int idFichaPago) {
		this.idFichaPago = idFichaPago;
	}

	public int getIdTramite() {
		return idTramite;
	}

	public void setIdTramite(int idTramite) {
		this.idTramite = idTramite;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idFichaPago;
		result = prime * result + idTramite;
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
		ConceptoId other = (ConceptoId) obj;
		if (idFichaPago != other.idFichaPago)
			return false;
		if (idTramite != other.idTramite)
			return false;
		return true;
	}

}
