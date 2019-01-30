package mx.qr.sace.formulario;

import java.util.ArrayList;

import mx.qr.core.vista.FormularioBase;
import mx.qr.core.vista.UtileriasMessageSource;
import mx.qr.sace.persistencia.entidades.Grupo;

/**
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del
 *         Campo</a>
 * @since Abril 2016
 * @copyright Q & R
 */
public class FormularioRegistroGrupo extends FormularioBase {

	private static final long serialVersionUID = -4089357141253825214L;

	/* El dominio de este formulario */
	private Grupo dominio;

	/**
	 * 
	 */
	public FormularioRegistroGrupo() {
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
	public FormularioRegistroGrupo(String usuarioEnFormulario) {
		this();
		this.usuarioEnFormulario = usuarioEnFormulario;
	}


	@Override
	public void configuraEnConsulta() {
		super.configuraEnConsulta();
	}

	@Override
	public void inicializa() {
		super.inicializa();
	}

	@Override
	public void codifica() {
		
	}

	@Override
	public void decodifica() {
		
	}

	@Override
	public void limpia() {
		
	}
}