package mx.qr.sace.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.qr.sace.dominio.EntidadBase;

/**
 */
@Entity
@Table(name = "descuentos", catalog = "SACE_DB")
@AttributeOverrides({
	@AttributeOverride(name = "usuario", 
		column = @Column(name = "usuario", nullable = false, length = 45)),
	@AttributeOverride(name = "fechaHora", 
		column = @Column(name = "fecha_hora", nullable = false))
})
public class Descuento extends EntidadBase implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2858763556652459337L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_descuento", unique = true, nullable = false)
	private int idDescuento;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_beca", nullable = false)
	private Beca beca;
	
	/**
	 * El trimete al que se efectura el descuento.
	 * 
	 * Al momento solo son Inscripcion y Mensualidad
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_tramite", nullable = false)
	private Tramite tramite;
	
	/**
	 * El valor del descuento
	 */
	@Column(name = "valor", nullable = false)
	private int valor;
	
	@Column(name = "tiene_descuento_tramite", nullable = false)
	private boolean tieneDescuentoElTramite = false;

	public Descuento() {
	}

	public int getIdDescuento() {
		return this.idDescuento;
	}

	public void setIdDescuento(int idDescuento) {
		this.idDescuento = idDescuento;
	}

	public Beca getBeca() {
		return this.beca;
	}

	public void setBeca(Beca beca) {
		this.beca = beca;
	}

	public Tramite getTramite() {
		return this.tramite;
	}

	public void setTramite(Tramite tramite) {
		this.tramite = tramite;
	}

	public int getValor() {
		return this.valor;
	}

	public void setValor(int descuento) {
		this.valor = descuento;
	}

	public boolean isTieneDescuentoElTramite() {
		return tieneDescuentoElTramite;
	}

	public void setTieneDescuentoElTramite(boolean tieneDescuentoElTramite) {
		this.tieneDescuentoElTramite = tieneDescuentoElTramite;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((beca == null) ? 0 : beca.hashCode());
		result = prime * result + idDescuento;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Descuento other = (Descuento) obj;
		if (beca == null) {
			if (other.beca != null)
				return false;
		} else if (!beca.equals(other.beca))
			return false;
		if (idDescuento != other.idDescuento)
			return false;
		return true;
	}

}
