package mx.qr.sace.mb;

import java.io.Serializable;

import javax.ejb.EJB;

import mx.qr.core.exception.ApplicationException;
import mx.qr.core.exception.RegistroAsociadoException;
import mx.qr.core.exception.RegistroNoEncontradoException;
import mx.qr.core.persistencia.EstatusRegistro;
import mx.qr.core.vista.FormularioBase;
import mx.qr.core.vista.MBDefecto;
import mx.qr.core.vista.UtileriasMessageSource;
import mx.qr.sace.formulario.FormularioRegistroBeca;
import mx.qr.sace.marketing.negocio.AdministracionMarketingLocal;
import mx.qr.sace.marketing.negocio.CatalogosMarketingLocal;
import mx.qr.sace.marketing.negocio.PreguntaEstadisticaMercadoLocal;
import mx.qr.sace.persistencia.entidades.Descuento;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Comportamiento para cancelar a un Prospecto.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Noviembre 2015
 * @copyright Q & R
 */
public class MBRegistroBeca extends MBDefectoBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8096941728656161519L;
	
	private static final Logger log = Logger.getLogger(MBRegistroBeca.class);
	
	@EJB(mappedName="java:global/sace-ear/sace-marketing-ejb/PreguntaEstadisticaMercadoEJB")
	private PreguntaEstadisticaMercadoLocal beanEstadistico;
	
	@EJB(mappedName="java:global/sace-ear/sace-marketing-ejb/AdministracionMarketingEJB")
	private AdministracionMarketingLocal beanAdmon;
	
	@EJB(mappedName="java:global/sace-ear/sace-marketing-ejb/CatalogosMarketingEJB")
	private CatalogosMarketingLocal beanCatalogosMKT;;
	
	@Autowired
	@Qualifier("mbHome")
	private transient MBHome mbHome;
	
	private FormularioRegistroBeca form;
	
	public void inicializa() {
		form = new FormularioRegistroBeca(obtenUsuario().getUsername());
	}
	
	/**
	 * Inicializa los elementos necesarios para el formulario, dependiendo si es captura o modifica
	 */
	public void inicializaForm(Integer como) {

		if(como == 1) {
			
			// DONE CREAR LOS OBJETOS DE DOMINIO
			form.inicializa();
			
			form.setAccionModulo(MBDefecto.Accion.CAPTURA);
		} else if(como == 3) {
			
			//TODO Revisar si hay becas capturados.
			try {
				form.setBecas(beanCatalogosMKT.recuperaBecas(EstatusRegistro.ACTIVO));
				form.getBecas().addAll(beanCatalogosMKT.recuperaBecas(EstatusRegistro.INACTIVO));

				if(!form.getBecas().isEmpty()) {
					// La vista sabe que hay registros
					form.setHayRegistros(true);
				}
				
			} catch (ApplicationException e) {
				form.setHayRegistros(false);
				log.error(e.getMessage(), e.getCause());
			}
			
			// Los campos se ponen en modo lectura
			form.setModoLectura(true);
			
			form.setAccionModulo(MBDefecto.Accion.CONSULTA);
		}
	}

	@Override
	public String guardaHandler() {
		
		try {
			beanAdmon.registraBeca(form.getDominio(), form.getDescuentos());
			
			agregaMensaje(TipoMensaje.INFO_MENSAJE,
					UtileriasMessageSource.mensajeProperties("msg_confirmacion_guarda"));
			
			form.limpia();
		} catch (ApplicationException e) {
			agregaMensaje(TipoMensaje.ERROR_MENSAJE.getTipo(), "mensajesError", e.getMessage());
			return "";
		}
		return OUTCOME_GUARDA;
	}

	@Override
	public String consultaHandler() {

		// Recupera la beca
		try {
			form.setDominio(
					beanCatalogosMKT.recuperaBeca(form.getDominio().getIdBeca()));
			
			// Traer los descuentos.
			muestraDescuentos();
		} catch (RegistroNoEncontradoException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String modificaHandler() {
		
		form.codifica();
		
		try {
			beanAdmon.alteraBeca(1, form.getDominio(), form.getDescuentos());
			
			agregaMensaje(TipoMensaje.INFO_MENSAJE, 
					UtileriasMessageSource.mensajeProperties("msg_confirmacion_modifica"));

			// Consultar el registro completo
			consultaHandler();
			
			// Inicializa el formulario en consulta
			inicializaForm(3);
			
			// Regresa el formulario a consulta
			form.configuraEnConsulta();
			
		} catch (RegistroAsociadoException e) {
			agregaMensaje(TipoMensaje.ERROR_MENSAJE.getTipo(), "mensajesError", e.getMessage());
		} catch (RegistroNoEncontradoException e) {
			agregaMensaje(TipoMensaje.ADVERTENCIA_MENSAJE.getTipo(), "mensajesAdvertencia", 
					UtileriasMessageSource.mensajeProperties("msg_info_registro_a_modificar_no_existe"));
		} 
		
//		catch (Exception e) {
//			// Exceptions que la aplicacion no sabe manejar
//			agregaMensaje(TipoMensaje.ERROR_MENSAJE.getTipo(), "mensajesError", "");
//		}
		
		// Se queda en la misma pantalla
		return null;
	}

	@Override
	public String eliminaHandler() {
		// sii tiene que cambiar de estatus el formulario si hubiera registros.
		if(getForm().isHayRegistros()) {
			
			try {
				beanAdmon.eliminaBeca(form.getDominio());
				
				agregaMensaje(TipoMensaje.INFO_MENSAJE, 
						UtileriasMessageSource.mensajeProperties("msg_confirmacion_elimina"));
				
				// Limpio las referencias del dominio en el formulario
				form.setDominio(null);
				form.setDescuentos(null);
				
			} catch (RegistroAsociadoException e) {
				agregaMensaje(TipoMensaje.ADVERTENCIA_MENSAJE.getTipo(), "mensajesAdvertencia", e.getMessage());
			} catch (RegistroNoEncontradoException e) {
				agregaMensaje(TipoMensaje.ADVERTENCIA_MENSAJE.getTipo(), "mensajesAdvertencia", 
						UtileriasMessageSource.mensajeProperties("msg_info_registro_a_eliminar_no_existe"));
			}
			
			// Ir al inicio del flujo.
			return "elimina";
		}
		
		return null;
	}
	
	public void muestraDescuentos() {
		// TODO Recuperar los descuentos de una Beca
		Descuento[] desc = null;
		try {
			desc = beanCatalogosMKT.recuperaDescuentosDeUnaBeca(form.getDominio().getIdBeca());
		} catch (ApplicationException e) {
			log.error(e.getMessage(), e.getCause());
		}
		form.setDescuentos(desc);
	}
	
	@Override
	public FormularioBase getForm() {
		return form;
	}
}
