package mx.qr.sace.formulario;

import java.util.ArrayList;

import mx.qr.core.vista.FormularioBase;
import mx.qr.core.vista.UtileriasMessageSource;

/**
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del
 *         Campo</a>
 * @since Agosto 2016
 * @copyright Q & R
 */
public class FormularioRegistroEmpleado extends FormularioBase {

	private static final long serialVersionUID = -4089357141253825444L;

	/**
	 * 
	 */
	public FormularioRegistroEmpleado() {
		super();
		migajas = new ArrayList<String>();
		migajas.add(UtileriasMessageSource
				.mensajeProperties("registro_empleados_migaja0"));
		migajas.add(UtileriasMessageSource
				.mensajeProperties("registro_empleados_migaja1"));
		migajas.add(UtileriasMessageSource
				.mensajeProperties("registro_empleados_migaja2"));

	}

	/**
	 * @param usuarioEnFormulario
	 */
	public FormularioRegistroEmpleado(String usuarioEnFormulario) {
		this();
		this.usuarioEnFormulario = usuarioEnFormulario;
	}


}