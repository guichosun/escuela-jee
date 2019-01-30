package mx.qr.sace.formulario;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;

import mx.qr.core.vista.MBDefecto;
import mx.qr.core.vista.MBDefecto.Accion;
import mx.qr.core.vista.UtileriasMessageSource;
import mx.qr.sace.persistencia.entidades.PagoDiverso;
import mx.qr.sace.persistencia.entidades.Tramite;

/**
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del
 *         Campo</a>
 * @since Diciembre 2016
 * @copyright Q & R
 */
public class FormularioPagoConcepto extends FormularioAlumno {

	private static final long serialVersionUID = -4089357146663825214L;
	
	private PagoDiverso registroPago;
	
	private Integer idTramite;
	
	/**
	 * 
	 */
	public FormularioPagoConcepto() {
		super();
		migajas = new ArrayList<String>();
		migajas.add(UtileriasMessageSource
				.mensajeProperties("caja_otros_migaja0"));
		migajas.add(UtileriasMessageSource
				.mensajeProperties("caja_otros_migaja1"));
		migajas.add(UtileriasMessageSource
				.mensajeProperties("caja_otros_migaja2"));
		
	}
	
	/**
	 * @param usuarioEnFormulario
	 */
	public FormularioPagoConcepto(String usuarioEnFormulario) {
		this();
		this.usuarioEnFormulario = usuarioEnFormulario;
	}

	@Override
	public void codifica() {
	}

	@Override
	public void decodifica() {

	}
	
	/**
	 * Listener para limpiar el formulario al seleccionar 
	 * la escolaridad
	 */
	public void limpiaSeleccionEscolaridad() {
		getSistemaEstudioFormulario().getModalidad().setIdModalidad(0);
		setCarreraSeleccionada(null);
		setMuestraResultado(false);
		// Limpiar el dato laboral
//		limpiaDatoLaboral();
//		limpiaPresupuesto();
		
		if(!getAccionModulo().equals(Accion.CAPTURA)) {
			// Eliminar el dominio consultado
			setDominio(null);
		}
	}
	
	/**
	 * Listener para limpiar el formulario al seleccionar 
	 * la modalidad
	 */
	public void limpiaSeleccionModalidad() {
		setCarreraSeleccionada(null);
		setMuestraResultado(false);
	}
	
	/**
	 * call-Back al momento de seleccionar la carrera del combo de carreras.
	 */
	public void muestraCarrera() {
		
		// Se muestra la seccion de la busqueda por matricula
		setMuestraResultado(true);
		
		// Solo en consulta. Limpiar el combo de busqueda
		if(!getAccionModulo().equals(MBDefecto.Accion.CAPTURA)) {
			setDominio(null);
		}
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
		registroPago = new PagoDiverso(getUsuarioEnFormulario());
		registroPago.setTramite(new Tramite());
		setIdTramite(null);
	}
	
	public void cambiaValorConcepto(ValueChangeEvent ce) {
		if(ce.getNewValue() != null) {
		}
		
	}
	
	@Override
	public List<String> getMigajas() {
		return migajas;
	}

	public PagoDiverso getRegistroPago() {
		return registroPago;
	}

	public void setRegistroPago(PagoDiverso registroPago) {
		this.registroPago = registroPago;
	}

	public Integer getIdTramite() {
		return idTramite;
	}

	public void setIdTramite(Integer idTramite) {
		this.idTramite = idTramite;
	}
}