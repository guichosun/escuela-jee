package mx.qr.sace.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 */
@Entity
@Table(name = "c_promedios_beca", catalog = "SACE_DB")
public class PromedioBeca implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1264207726357363200L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_promedio_beca", unique = true, nullable = false)
	private int idPromedioBeca;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_beca", nullable = false)
	private Beca beca;
	
	@Column(name = "prom_min", nullable = false, precision = 3)
	private Float promedioMin;
	
	@Column(name = "prom_max", nullable = false, precision = 3)
	private Float promedioMax;

	public PromedioBeca() {
	}

	public PromedioBeca(Beca beca, Float promMin, Float promMax) {
		this.beca = beca;
		this.promedioMin = promMin;
		this.promedioMax = promMax;
	}

	public int getIdPromedioBeca() {
		return this.idPromedioBeca;
	}

	public void setIdPromedioBeca(int idPromedioBeca) {
		this.idPromedioBeca = idPromedioBeca;
	}

	public Beca getBeca() {
		return this.beca;
	}

	public void setBeca(Beca beca) {
		this.beca = beca;
	}

	public Float getPromedioMin() {
		return this.promedioMin;
	}

	public void setPromedioMin(Float promMin) {
		this.promedioMin = promMin;
	}

	public Float getPromedioMax() {
		return this.promedioMax;
	}

	public void setPromedioMax(Float promMax) {
		this.promedioMax = promMax;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPromedioBeca;
		result = prime * result
				+ ((promedioMax == null) ? 0 : promedioMax.hashCode());
		result = prime * result
				+ ((promedioMin == null) ? 0 : promedioMin.hashCode());
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
		PromedioBeca other = (PromedioBeca) obj;
		if (idPromedioBeca != other.idPromedioBeca)
			return false;
		if (promedioMax == null) {
			if (other.promedioMax != null)
				return false;
		} else if (!promedioMax.equals(other.promedioMax))
			return false;
		if (promedioMin == null) {
			if (other.promedioMin != null)
				return false;
		} else if (!promedioMin.equals(other.promedioMin))
			return false;
		return true;
	}

}
