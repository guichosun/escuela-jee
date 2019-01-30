package mx.qr.sace.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import mx.qr.core.persistencia.Entidad;
import mx.qr.sace.dominio.EntidadBase;

/**
 */
@Entity
@Table(name = "datos_salud", catalog = "SACE_DB")
@AttributeOverrides({
		@AttributeOverride(name = "usuario", column = @Column(name = "usuario", nullable = false, length = 45)),
		@AttributeOverride(name = "fechaHora", column = @Column(name = "fecha_hora", nullable = false)) })
public class DatoSalud extends EntidadBase implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -302386082948763543L;

	private int idDatoSalud;
	private boolean tieneOperacion;
	private String cualOperacion;
	private boolean tomaMedicamento;
	private String cualMedicamento;
	private String formaTomarlo;
	private boolean padeceEnfermedad;
	private String cualEnfermedad;
	private String tratamiento;
	private boolean padeceAlergia;
	private String cualAlergia;
	private String tratamientoAlergia;
	private boolean puedeDonar;
	private String tipoSangre;
	private String telefonoEmergencia;
	private String contactoEmergencia;
	private String parentesco;
	private boolean tieneSeguro;
	private String clinica;
	private String numeroAfiliacion;

	public DatoSalud() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_dato_salud", unique = true, nullable = false)
	public int getIdDatoSalud() {
		return this.idDatoSalud;
	}

	public void setIdDatoSalud(int idDatoSalud) {
		this.idDatoSalud = idDatoSalud;
	}

	@Column(name = "tiene_operacion", nullable = false)
	public boolean getTieneOperacion() {
		return this.tieneOperacion;
	}

	public void setTieneOperacion(boolean tieneOperacion) {
		this.tieneOperacion = tieneOperacion;
	}

	@Column(name = "cual_operacion", length = 45)
	public String getCualOperacion() {
		return this.cualOperacion;
	}

	public void setCualOperacion(String cualOperacion) {
		this.cualOperacion = cualOperacion;
	}

	@Column(name = "toma_medicamento", nullable = false)
	public boolean getTomaMedicamento() {
		return this.tomaMedicamento;
	}

	public void setTomaMedicamento(boolean tomaMedicamento) {
		this.tomaMedicamento = tomaMedicamento;
	}

	@Column(name = "cual_medicamento", length = 45)
	public String getCualMedicamento() {
		return this.cualMedicamento;
	}

	public void setCualMedicamento(String cualMedicamento) {
		this.cualMedicamento = cualMedicamento;
	}

	@Column(name = "forma_tomarlo", length = 45)
	public String getFormaTomarlo() {
		return this.formaTomarlo;
	}

	public void setFormaTomarlo(String formaTomarlo) {
		this.formaTomarlo = formaTomarlo;
	}

	@Column(name = "padece_enfermedad")
	public boolean getPadeceEnfermedad() {
		return this.padeceEnfermedad;
	}

	public void setPadeceEnfermedad(boolean padeceEnfermedad) {
		this.padeceEnfermedad = padeceEnfermedad;
	}

	@Column(name = "cual_enfermedad", length = 45)
	public String getCualEnfermedad() {
		return this.cualEnfermedad;
	}

	public void setCualEnfermedad(String cualEnfermedad) {
		this.cualEnfermedad = cualEnfermedad;
	}

	@Column(name = "tratamiento", length = 45)
	public String getTratamiento() {
		return this.tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

	@Column(name = "padece_alergia", nullable = false)
	public boolean getPadeceAlergia() {
		return this.padeceAlergia;
	}

	public void setPadeceAlergia(boolean padeceAlergia) {
		this.padeceAlergia = padeceAlergia;
	}

	@Column(name = "cual_alergia", length = 45)
	public String getCualAlergia() {
		return this.cualAlergia;
	}

	public void setCualAlergia(String cualAlergia) {
		this.cualAlergia = cualAlergia;
	}

	@Column(name = "tratamiento_alergia", length = 45)
	public String getTratamientoAlergia() {
		return this.tratamientoAlergia;
	}

	public void setTratamientoAlergia(String tratamientoAlergia) {
		this.tratamientoAlergia = tratamientoAlergia;
	}

	@Column(name = "puede_donar", nullable = false)
	public boolean getPuedeDonar() {
		return this.puedeDonar;
	}

	public void setPuedeDonar(boolean puedeDonar) {
		this.puedeDonar = puedeDonar;
	}

	@Column(name = "tipo_sangre", length = 15)
	public String getTipoSangre() {
		return this.tipoSangre;
	}

	public void setTipoSangre(String tipoSangre) {
		this.tipoSangre = tipoSangre;
	}

	@Column(name = "telefono_emergencia", nullable = false, length = 20)
	public String getTelefonoEmergencia() {
		return this.telefonoEmergencia;
	}

	public void setTelefonoEmergencia(String telefonoEmergencia) {
		this.telefonoEmergencia = telefonoEmergencia;
	}

	@Column(name = "contacto_emergencia", length = 45)
	public String getContactoEmergencia() {
		return this.contactoEmergencia;
	}

	public void setContactoEmergencia(String contactoEmergencia) {
		this.contactoEmergencia = contactoEmergencia;
	}

	@Column(name = "parentesco", length = 45)
	public String getParentesco() {
		return this.parentesco;
	}

	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}

	@Column(name = "tiene_seguro", nullable = false)
	public boolean getTieneSeguro() {
		return this.tieneSeguro;
	}

	public void setTieneSeguro(boolean tieneSeguro) {
		this.tieneSeguro = tieneSeguro;
	}

	@Column(name = "clinica", length = 15)
	public String getClinica() {
		return this.clinica;
	}

	public void setClinica(String clinica) {
		this.clinica = clinica;
	}

	@Column(name = "numero_afiliacion", length = 20)
	public String getNumeroAfiliacion() {
		return this.numeroAfiliacion;
	}

	public void setNumeroAfiliacion(String numeroAfiliacion) {
		this.numeroAfiliacion = numeroAfiliacion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + idDatoSalud;
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
		DatoSalud other = (DatoSalud) obj;
		if (idDatoSalud != other.idDatoSalud)
			return false;
		return true;
	}

}
