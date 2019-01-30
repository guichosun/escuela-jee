package mx.qr.sace.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "c_tipos_nivel", catalog = "SACE_DB")
public class TipoNivel implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8182453595818717594L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_tipo_nivel", unique = true, nullable = false)
	private int idTipoNivel;
	
	@Column(name = "nombre", nullable = false, length = 20)
	private String nombre;
	
	@Column(name = "etiqueta", nullable = false, length = 10)
	private String etiqueta;

	public int getIdTipoNivel() {
		return this.idTipoNivel;
	}

	public void setIdTipoNivel(int idTipoEmpleado) {
		this.idTipoNivel = idTipoEmpleado;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String descripcion) {
		this.nombre = descripcion;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

}
