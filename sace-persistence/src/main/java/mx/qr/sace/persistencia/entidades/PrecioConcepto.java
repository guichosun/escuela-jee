package mx.qr.sace.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.qr.core.persistencia.Entidad;
import mx.qr.core.persistencia.EstatusRegistro;
import mx.qr.sace.dominio.EntidadBase;

/**
 */
@Entity
@Table(name = "precios_conceptos", catalog = "SACE_DB")
@AttributeOverrides({
	@AttributeOverride(name = "usuario", 
		column = @Column(name = "usuario", nullable = false, length = 45)),
	@AttributeOverride(name = "fechaHora", 
		column = @Column(name = "fecha_hora", nullable = false))
})
public class PrecioConcepto extends EntidadBase implements Entidad {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5434132226304412095L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_precio_concepto")
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_tramite")	
	private Tramite tramite;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "estatus")
	private EstatusRegistro estatus;
	
	@Column(name = "valor")
	private Float valor;
	
	public PrecioConcepto() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int idCarreraTramite) {
		this.id = idCarreraTramite;
	}

	public Tramite getTramite() {
		return this.tramite;
	}

	public void setTramite(Tramite auxTramite) {
		this.tramite = auxTramite;
	}

	public EstatusRegistro getEstatus() {
		return estatus;
	}

	public void setEstatus(EstatusRegistro estatus) {
		this.estatus = estatus;
	}

	@Override
	public String toString() {
		return "PrecioConcepto [estatus=" + estatus + ", valor=" + valor + "]";
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}


}
