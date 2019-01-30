package mx.qr.sace.persistencia.entidades;

/**
 * 
 * @author guichosun
 *
 */
public class AreaAdscrita {

	public int id;
	
	public String nombre;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "AreaAdscrita [id=" + id + ", nombre=" + nombre + "]";
	}
}
