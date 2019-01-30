package mx.qr.sace.persistencia.entidades;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * La PK de l EmpresaConvenio
 */
@Embeddable
public class EmpresaConvenioId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1101039984318529446L;
	
	@Column(name = "id_beca", nullable = false)
	private int idBeca;
	
	@Column(name = "id_empresa", nullable = false)
	private int idEmpresa;

	public EmpresaConvenioId() {
	}

	public int getIdBeca() {
		return idBeca;
	}

	public void setIdBeca(int idBeca) {
		this.idBeca = idBeca;
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idBeca;
		result = prime * result + idEmpresa;
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
		EmpresaConvenioId other = (EmpresaConvenioId) obj;
		if (idBeca != other.idBeca)
			return false;
		if (idEmpresa != other.idEmpresa)
			return false;
		return true;
	}

}
