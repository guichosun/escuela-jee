package mx.qr.sace.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

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

import mx.qr.core.persistencia.Entidad;
import mx.qr.sace.dominio.EntidadBase;

/**
 */
@Entity
@Table(name = "pagos_diversos", catalog = "SACE_DB")
@AttributeOverrides({
	@AttributeOverride(name = "usuario", 
		column = @Column(name = "usuario", nullable = false, length = 45)),
	@AttributeOverride(name = "fechaHora", 
		column = @Column(name = "fecha_hora", nullable = false))
})
public class PagoDiverso extends EntidadBase implements Entidad {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5434132226304412095L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_pago_diverso")
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ficha_academica", nullable = false)
	private FichaAcademica fichaAcademica;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_tramite")	
	private Tramite tramite;
	
	@Column(name = "valor")
	private Float valor;
	
	@Column(name = "tipo_pago")
	private int tipoPago;
	
	@Column(name = "observaciones")
	private String observaciones;
	
	@Column(name = "fecha_pago")
	private Date fechaPago;
	
	public PagoDiverso() {
	}
	
	public PagoDiverso(String usuario) {
		super(usuario);
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Tramite getTramite() {
		return this.tramite;
	}

	public void setTramite(Tramite auxTramite) {
		this.tramite = auxTramite;
	}

	public FichaAcademica getFichaAcademica() {
		return fichaAcademica;
	}

	public void setFichaAcademica(FichaAcademica fichaAcademica) {
		this.fichaAcademica = fichaAcademica;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public int getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(int tipoPago) {
		this.tipoPago = tipoPago;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("PagoDiverso [\\n");
		sb.append("fichaAcademica=" + fichaAcademica).append("\\n");
		sb.append("tramite="+ tramite).append("\\n");
		sb.append("tipoPago=" + tipoPago).append("\\n");
		sb.append("observaciones=" + observaciones).append("\\n");
		sb.append("fechaPago=" + fechaPago).append("\\n");
		sb.append("]");
		return sb.toString();
	}


}
