
package mx.qr.sace.vista;

import java.io.Serializable;

public class NivelEstudioProfeDataModel implements Serializable {

	private static final long serialVersionUID = -697566230691775935L;
	
	private String cedula;
	private String nivel;
	private String carrera;
	
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	public String getCarrera() {
		return carrera;
	}
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	
}
