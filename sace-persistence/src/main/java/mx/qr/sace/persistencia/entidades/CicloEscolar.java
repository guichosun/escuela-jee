package mx.qr.sace.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mx.qr.core.persistencia.Entidad;
import mx.qr.sace.dominio.EntidadBase;
import mx.qr.sace.persistencia.PeriodoEscolar;

/**
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Mayo 2016
 * @copyright Q & R
 */
@Entity
@Table(name = "ciclos_escolares", catalog = "SACE_DB")
@AttributeOverrides({
	@AttributeOverride(name = "usuario", 
		column = @Column(name = "usuario", nullable = false, length = 45)),
	@AttributeOverride(name = "fechaHora", 
		column = @Column(name = "fecha_hora", nullable = false))
})
public class CicloEscolar extends EntidadBase implements Entidad {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6933051331522718284L;
	
	private Integer id;
	private Carrera carrera;
	private String clave;
	private String descripcion;
	private Date fechaInicio;
	private Date fechaTermino;
	private boolean esVigente = false;
	private List<PeriodoEscolar> periodosEscolares = new ArrayList<PeriodoEscolar>(0);

	public CicloEscolar() {
	}
	
	/**
	 * @param idCiclo
	 */
	public CicloEscolar(Integer idCiclo) {
		this();
		this.id = idCiclo;
	}



	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_ciclo", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer idCiclo) {
		this.id = idCiclo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "id_carrera", referencedColumnName = "id_carrera", nullable = false),
			@JoinColumn(name = "id_escolaridad", referencedColumnName = "id_escolaridad", nullable = false),
			@JoinColumn(name = "id_modalidad", referencedColumnName = "id_modalidad", nullable = false) })
	public Carrera getCarrera() {
		return this.carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	@Column(name = "clave", nullable = false, length = 15)
	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@Column(name = "descripcion", length = 45)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_inicio", nullable = false, length = 10)
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_termino", nullable = false, length = 10)
	public Date getFechaTermino() {
		return this.fechaTermino;
	}

	public void setFechaTermino(Date fechaTermino) {
		this.fechaTermino = fechaTermino;
	}

	@Column(name = "es_vigente", nullable = false)
	public boolean getEsVigente() {
		return this.esVigente;
	}

	public void setEsVigente(boolean esVigente) {
		this.esVigente = esVigente;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "cicloEscolar")
	public List<PeriodoEscolar> getPeriodosEscolares() {
		return this.periodosEscolares;
	}

	public void setPeriodosEscolares(List<PeriodoEscolar> periodosEscolareses) {
		this.periodosEscolares = periodosEscolareses;
	}

}
