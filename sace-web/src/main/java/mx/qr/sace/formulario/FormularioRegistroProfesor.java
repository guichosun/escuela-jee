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
public class FormularioRegistroProfesor extends FormularioBase {

	private static final long serialVersionUID = -4089357141253821114L;

	/**
	 * 
	 */
	public FormularioRegistroProfesor() {
		super();
		migajas = new ArrayList<String>();
		migajas.add(UtileriasMessageSource
				.mensajeProperties("registro_grupo_migaja0"));
		migajas.add(UtileriasMessageSource
				.mensajeProperties("registro_grupo_migaja1"));
		migajas.add(UtileriasMessageSource
				.mensajeProperties("registro_grupo_migaja2"));

	}

	/**
	 * @param usuarioEnFormulario
	 */
	public FormularioRegistroProfesor(String usuarioEnFormulario) {
		this();
		this.usuarioEnFormulario = usuarioEnFormulario;
	}
}