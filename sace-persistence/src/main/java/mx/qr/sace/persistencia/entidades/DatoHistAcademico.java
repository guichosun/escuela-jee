package mx.qr.sace.persistencia.entidades;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

import mx.qr.sace.dominio.EntidadBase;

/**
 */
@Entity
@Table(name = "datos_hist_academica", catalog = "SACE_DB")
@AttributeOverrides({
	@AttributeOverride(name = "usuario", 
		column = @Column(name = "usuario", nullable = false, length = 45)),
	@AttributeOverride(name = "fechaHora", 
		column = @Column(name = "fecha_hora", nullable = false))
})
public class DatoHistAcademico extends EntidadBase implements java.io.Serializable {

	private static final long serialVersionUID = -1377877299878786361L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_dato_hist_academica", unique = true, nullable = false)
	private int idDatoHistAcademica;
	
	@Column(name = "nom_inst_anterior", nullable = false, length = 45)
	private String nomInstAnterior;
	
	@Column(name = "promedio")
	private Float promedio;
	
	@Column(name = "generacion", length = 45)
	private String generacion;

	public DatoHistAcademico() {
	}

	public DatoHistAcademico(String nomInstAnterior) {
		this.nomInstAnterior = nomInstAnterior;
	}

	public int getIdDatoHistAcademica() {
		return this.idDatoHistAcademica;
	}

	public void setIdDatoHistAcademica(int idDatoHistAcademica) {
		this.idDatoHistAcademica = idDatoHistAcademica;
	}

	public String getNomInstAnterior() {
		return this.nomInstAnterior;
	}

	public void setNomInstAnterior(String nomInstAnterior) {
		this.nomInstAnterior = nomInstAnterior;
	}

	public Float getPromedio() {
		return this.promedio;
	}

	public void setPromedio(Float promedio) {
		this.promedio = promedio;
	}

	public String getGeneracion() {
		return this.generacion;
	}

	public void setGeneracion(String generacion) {
		this.generacion = generacion;
	}
}
