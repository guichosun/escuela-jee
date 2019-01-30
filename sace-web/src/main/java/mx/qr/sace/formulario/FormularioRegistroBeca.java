package mx.qr.sace.formulario;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponentBase;
import javax.faces.event.AjaxBehaviorEvent;

import mx.qr.core.persistencia.EstatusRegistro;
import mx.qr.core.vista.FormularioBase;
import mx.qr.core.vista.UtileriasMessageSource;
import mx.qr.sace.persistencia.entidades.Beca;
import mx.qr.sace.persistencia.entidades.Descuento;

/**
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del
 *         Campo</a>
 * @since Diciembre 2015
 * @copyright Q & R
 */
public class FormularioRegistroBeca extends FormularioBase {

	private static final long serialVersionUID = -4089357141253825214L;
	
	/* El estatus de una beca */
	private EstatusRegistro estatusBeca = EstatusRegistro.ACTIVO;
	
	/* El dominio de este formulario */
	private Beca dominio;
	
	/* El descuento que van a aplicar el la beca */
	private Descuento[] descuentos;
	
	/* La lista de becas para mostrar */
	private List<Beca> becas;
	
	/**
	 * 
	 */
	public FormularioRegistroBeca() {
		super();
		migajas = new ArrayList<String>();
		migajas.add(UtileriasMessageSource
				.mensajeProperties("lbl_registro_beca_migaja0"));
		migajas.add(UtileriasMessageSource
				.mensajeProperties("lbl_registro_beca_migaja1"));
		migajas.add(UtileriasMessageSource
				.mensajeProperties("lbl_registro_beca_migaja2"));
		
	}
	
	/**
	 * @param usuarioEnFormulario
	 */
	public FormularioRegistroBeca(String usuarioEnFormulario) {
		this();
		this.usuarioEnFormulario = usuarioEnFormulario;
	}

	@Override
	public void codifica() {
	}

	@Override
	public void decodifica() {

	}

	@Override
	public void limpia() {
		// Al limpiar el formulario, es crear los objtos de dominio.
		inicializa();
	}
	
	@Override
	public void configuraEnConsulta() {
		super.configuraEnConsulta();
	}

	@Override
	public void inicializa() {
		super.inicializa();
		
		Beca beca = new Beca();
		beca.setEstatus(EstatusRegistro.ACTIVO);
		beca.setUsuario(usuarioEnFormulario);
		setDominio(beca);
		
		Descuento[] decs = new Descuento[2];
		decs[0] = new Descuento();
		decs[0].setUsuario(usuarioEnFormulario);
		
		decs[1] = new Descuento();
		decs[1].setUsuario(usuarioEnFormulario);
		setDescuentos(decs);
	}
	
	/**
	 * Para activar o desactivar el descuento de el tramite
	 * 
	 * @param event
	 */
	public void activaTramite(AjaxBehaviorEvent event) {
		UIComponentBase ui = (UIComponentBase)event.getSource();
		
		if("ch1-Ins".equalsIgnoreCase(ui.getId())) {
			if(!descuentos[0].isTieneDescuentoElTramite()) {
				descuentos[0].setValor(0);
			}
		} else {
			if(!descuentos[1].isTieneDescuentoElTramite()) {
				descuentos[1].setValor(0);
			}
		}
	}
	
	@Override
	public List<String> getMigajas() {
		return migajas;
	}

	public EstatusRegistro getEstatusBeca() {
		return estatusBeca;
	}

	public void setEstatusBeca(EstatusRegistro estatusBeca) {
		this.estatusBeca = estatusBeca;
	}

	public Beca getDominio() {
		return dominio;
	}

	public void setDominio(Beca dominio) {
		this.dominio = dominio;
	}

	public Descuento[] getDescuentos() {
		return descuentos;
	}

	public void setDescuentos(Descuento[] descuentos) {
		this.descuentos = descuentos;
	}

	public List<Beca> getBecas() {
		return becas;
	}

	public void setBecas(List<Beca> becas) {
		this.becas = becas;
	}
}