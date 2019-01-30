package mx.qr.sace.mb;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import mx.qr.core.exception.ApplicationException;
import mx.qr.core.exception.RegistroNoEncontradoException;
import mx.qr.core.persistencia.EstatusAlumno;
import mx.qr.core.vista.MBDefecto;
import mx.qr.core.vista.UtileriasMessageSource;
import mx.qr.sace.ce.negocio.CatalogosCELocal;
import mx.qr.sace.ce.negocio.ManagerDeFichaAcademicaLocal;
import mx.qr.sace.core.ContenedorBytesPdf;
import mx.qr.sace.formulario.FormularioAlumno;
import mx.qr.sace.formulario.FormularioProspecto;
import mx.qr.sace.marketing.negocio.CatalogosMarketingLocal;
import mx.qr.sace.marketing.negocio.ComentariosProspectoLocal;
import mx.qr.sace.marketing.negocio.PreguntaEstadisticaMercadoLocal;
import mx.qr.sace.marketing.negocio.PropuestaDeMercadoLocal;
import mx.qr.sace.marketing.negocio.RegistroProspectoLocal;
import mx.qr.sace.persistencia.entidades.Alumno;
import mx.qr.sace.persistencia.entidades.Comentario;
import mx.qr.sace.persistencia.entidades.DatoHistAcademico;
import mx.qr.sace.persistencia.entidades.DatoLaboral;
import mx.qr.sace.persistencia.entidades.DatosPersona;
import mx.qr.sace.persistencia.entidades.Empresa;
import mx.qr.sace.persistencia.entidades.EmpresaConvenio;
import mx.qr.sace.persistencia.entidades.FichaAcademica;
import mx.qr.sace.persistencia.entidades.Pregunta;
import mx.qr.sace.persistencia.entidades.ResponsablePago;
import mx.qr.sace.persistencia.entidades.RespuestaAPregunta;
import mx.qr.sace.util.UtileriasFaces;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Comportamiento del flujo y de las interacciones con la vista del
 * prospecto.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Julio 2015
 * @copyright Q & R
 */
public class MBProspecto extends MBAutoCompleta {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8096941728656161534L;
	
	private static final Logger log = Logger.getLogger(MBProspecto.class);
	
	@EJB(mappedName="java:global/sace-ear/sace-marketing-ejb/RegistroProspectoEJB")
	private RegistroProspectoLocal beanProspecto;
	
	@EJB(mappedName="java:global/sace-ear/sace-marketing-ejb/PropuestaDeMercadoEJB")
	private PropuestaDeMercadoLocal beanMercado;
	
	@EJB(mappedName="java:global/sace-ear/sace-marketing-ejb/ComentariosProspectoEJB")
	private ComentariosProspectoLocal beanComentario;
	
	@EJB(mappedName="java:global/sace-ear/sace-marketing-ejb/CatalogosMarketingEJB")
	private CatalogosMarketingLocal beanCatalogosMKT;
	
	@EJB(mappedName="java:global/sace-ear/sace-marketing-ejb/PreguntaEstadisticaMercadoEJB")
	private PreguntaEstadisticaMercadoLocal beanEstadistico;
	
	@EJB(mappedName="java:global/sace-ear/sace-controlescolar-ejb/catalogosControlEscolar")
	private CatalogosCELocal beanCatalogoCE;
	
	@EJB(mappedName="java:global/sace-ear/sace-controlescolar-ejb/ManagerDeFichaAcademicaEJB")
	private ManagerDeFichaAcademicaLocal managerFA;
	
	@Autowired
	@Qualifier("mbHome")
	private transient MBHome mbHome;
	
	private FormularioProspecto form;
	
	public void inicializa() {
		form = new FormularioProspecto();
//		FabricaEntidades fabrica = FabricaEntidades.getInstancia();
		
	}
	
	/**
	 * Inicializa los elementos necesarios para el formulario
	 */
	public void inicializaForm(Integer como) {

		try {
			// DONE RECUPERAR LAS BECAS DEBERIA ESTAR EN EL METODO DE por contactar y no aqui
			form.setBecas(beanCatalogosMKT.recuperaBecas());
			
			form.setEmpresas(beanCatalogosMKT.recuperaEmpresas());
			// Agregar la opcion Otro al final. Es un truco
			form.getEmpresas().add(new Empresa(666, "Otro..."));
			
			// DONE Recuperar las respuestas
			form.setRespuestasMkt(beanEstadistico.recuperaRespuestasAPregunta(
					new Pregunta(FormularioProspecto.PREGUNTA_MEDIO_DE_CONTACTO)));

			form.setCarreras(beanCatalogoCE.obtenCarrerasPorEscolaridadYModalidad(
					mbHome.getSistemaEstudioUsuario().getEscolaridad().getIdEscolaridad(),
					mbHome.getSistemaEstudioUsuario().getModalidad().getIdModalidad()));
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

		if(como == 1) {
			form.setProspectosRegistrados(true);
			
			// DONE Inicializar los objetos necesarios
			
			Alumno p = new Alumno();
			form.setDominio(p);
			form.getDominio().setUsuario(obtenUsuario().getUsername());

			form.setFichaAcademica(new FichaAcademica(obtenUsuario().getUsername()));
			
			DatosPersona dp = new DatosPersona();
			dp.setUsuario(obtenUsuario().getUsername());
			form.getDominio().setDatoPersona(dp);
			
			DatoHistAcademico dha = new DatoHistAcademico();
			dha.setUsuario(obtenUsuario().getUsername());
			form.getFichaAcademica().setDatoHistAcademico(dha);
			
			Comentario comentario = new Comentario();
			form.setComentario(comentario);
			
			ResponsablePago tutor = new ResponsablePago();
			tutor.setUsuario(obtenUsuario().getUsername());
			form.setTutor(tutor);

			form.setDatoLaboral(new DatoLaboral(obtenUsuario().getUsername()));
			
			// Se crea la RespuestaAPregunta, con la Pregunta conocida
			RespuestaAPregunta rap = new RespuestaAPregunta(
					FormularioProspecto.PREGUNTA_MEDIO_DE_CONTACTO);
			rap.setUsuario(obtenUsuario().getUsername());
			form.setRespuestaAP(rap);
			
			form.setAccionModulo(MBDefecto.Accion.CAPTURA);
		} else if(como == 3) {
			form.setHayRegistros(true);
			// Los campos se ponen en modo lectura
			form.setModoLectura(true);
			
			form.setAccionModulo(MBDefecto.Accion.CONSULTA);
		}
		
		log.debug("Entra a "+form.getAccionModulo().getName() +" del Registro de Prospecto");
	}
	
	@Override
	public List<Alumno> completa(String query) {
		// En la busqueda de este modulo los estatus que puede buscar son PROSPECRO y CONTACTARE
		// DONE Sacar la lista de los PROSPECTOS luego agregar la lista de CONTACTAR
		form.setEstatusAlumnoEnUso(EstatusAlumno.PROSPECTO);
		List<Alumno> coincidencias = super.completa(query);
		
		form.setEstatusAlumnoEnUso(EstatusAlumno.CANDIDATO);
		coincidencias.addAll(super.completa(query));
		
		return coincidencias;
	}
	
	/**
	 * Verifica el promedio para ver en que rango entra y asignar una beca
	 */
	public void verificaBecaPor(AjaxBehaviorEvent event) {
		UIComponentBase ui = (UIComponentBase)event.getSource();

		if(ui.getId().equalsIgnoreCase("ocupacionTut")) {

			Empresa es = form.getDatoLaboral().getEmpresa();
			if(es.getId() != 666) {
				EmpresaConvenio ec = beanCatalogosMKT.recuperaConvenioDeEmpresa(es.getId());
				if(ec != null) {
					form.verificaEmpresa(ec);
				}
				
			} else {
				form.setSoloLecturaOtraEmpresa(false);
			}
			
			
		} else {
			//TODO Consultar que beca le corresponde al promedio
			
			String mensaje = "";
			
			FacesMessage message = new FacesMessage();
			message.setSeverity(FacesMessage.SEVERITY_INFO);
			message.setSummary("Sugerencia de beca: ");
			
			if(form.getFichaAcademica().getDatoHistAcademico().getPromedio() >= 8 &&
					form.getFichaAcademica().getDatoHistAcademico().getPromedio() < 9) {
				mensaje = "Beca en Inscripcion 10% - Mensualidad 5%";
				message.setDetail(mensaje);
				FacesContext.getCurrentInstance().addMessage("infoBecaPorPromedio", message);
			} else if(form.getFichaAcademica().getDatoHistAcademico().getPromedio() >= 9 &&
					form.getFichaAcademica().getDatoHistAcademico().getPromedio() < 10) {
				mensaje = "Beca en Inscripcion 16% - Mensualidad 15%";
				message.setDetail(mensaje);
				FacesContext.getCurrentInstance().addMessage("infoBecaPorPromedio", message);
			}
		}
	}
	
	@Override
	public String consultaHandler() {
		
		form.decodifica();
		
		try {
			// DONE Traer la ficha academica
			form.setFichaAcademica(
					managerFA.recuperaFichaDelAlumno(
							form.getDominio(), form.getCarreraSeleccionada()));
			
			
			// DONE TREAER EL COMENTARIO
			form.setComentario(
					beanComentario.recuperaDeUnProspecto(
							form.getDominio()));
			
			// DONE Traer la respuesta a la pregunta
			form.setRespuestaAP(beanEstadistico.recuperaRespuestaAPreguntaDelAlumno(
					form.getDominio(),
					new Pregunta(FormularioProspecto.PREGUNTA_MEDIO_DE_CONTACTO)));
			
			// TODO Traer el presupuesto del alumno: 
			// Traer la beca seleccionada
			if(form.getFichaAcademica().getIdBeca() != null) {
				form.setBecaSeleccionada(
						beanCatalogosMKT.recuperaBeca(
								form.getFichaAcademica().getIdBeca()));
				
				form.configuraBeca();
			} else {
				form.setBecaSeleccionada(null);
			}
			
			form.setMuestraResultado(true);
			
		} catch (RegistroNoEncontradoException e) {
			form.setMuestraResultado(false);
		}
		return null;
	}

	/**
	 * Manejador para la acciÃ³n de guardar
	 * @return
	 */
	@Override
	public String guardaHandler() {
		
		form.codifica();
		
		try {
			if(!form.isPorRevalidar()) { // No revalida
				// Si es por contactar
				if(form.isPorContactar()) {
					beanProspecto.registro(form.getDominio(),
							form.getFichaAcademica(), form.getTutor(), 
							form.getComentario(), form.getRespuestaAP());
					
					// Se crea la ficha de pago
	//				ContenedorBytesPdf cbp = beanProspecto.elaboraFichaPagoPresupuesto(form.getDominio(), form.getFichaAcademica());
	//				try {
	//					obtienePDFConTotales(cbp);
	////				} catch (JRException e) {
	////					e.printStackTrace();
	//				} catch (IOException e) {
	//					e.printStackTrace();
	//				}
				} else {
					// o no
					beanProspecto.registro(form.getDominio(), form.getFichaAcademica(),
							form.getTutor(),
							form.getBecaSeleccionada(),
							form.getMontoTotalInscripcion(), form.getMontoTotalMensualidad(),
							form.getComentario(), form.getTramitesAPagar(),
							form.getRespuestaAP());
					
				}
			} else { // Si es para revalidar
//				se guarda:
//				el dominio con estatus REVALIDA
//				la ficha academica
//				el tutor (de tener)
//				el comentario
//				la respuesta a la pregunta estadistica
			}
			
			agregaMensaje(TipoMensaje.INFO_MENSAJE,
					UtileriasMessageSource.mensajeProperties("msg_confirmacion_guarda"));
			form.limpia();
			
			log.debug("Prospecto guardado!");
		} catch(ApplicationException e) {
			agregaMensaje(TipoMensaje.ADVERTENCIA_MENSAJE.getTipo(), "mensajesError", e.getMessage());
			form.limpia();
			return null;
		}
		
		return OUTCOME_GUARDA;
	}
	
	/**
	 * La accion de modificar
	 * @return
	 */
	public String modificaHandler() {
		form.codifica();
		
		try {
			
			// Un Si es por contactar
			if(form.isPorContactar()) {
				
				beanProspecto.modifica(form.getDominio(),
						form.getFichaAcademica(), form.getTutor(), 
						form.getComentario(), form.getRespuestaAP());
				
			} else {
				// o no
				beanProspecto.modifica(form.getDominio(), form.getFichaAcademica(),
						form.getTutor(),
						form.getBecaSeleccionada(),
						form.getMontoTotalInscripcion(), form.getMontoTotalMensualidad(),
						form.getComentario(), form.getTramitesAPagar(),
						form.getRespuestaAP());
				
			}
			
			agregaMensaje(TipoMensaje.INFO_MENSAJE,
					UtileriasMessageSource.mensajeProperties("msg_confirmacion_guarda"));
			
			// Consultar el registro completo
			consultaHandler();

			// Inicializa el formulario en consulta
			inicializaForm(3);
			
			// Regresa el formulario a consulta
			form.configuraEnConsulta();
			
			log.debug("Prospecto modificado. Regresa a la consulta!");
		} catch(ApplicationException e) {
			agregaMensaje(TipoMensaje.ADVERTENCIA_MENSAJE.getTipo(), "mensajesError", e.getMessage());
			form.limpia();
		}
		
		// Se queda en la misma pantalla
		return null;
	}
	
	@Override
	public String eliminaHandler() {
		return null;
	}

	@Override
	public FormularioAlumno getForm() {
		return form;
	}

	public void obtienePDFConTotales(ContenedorBytesPdf pdf) throws IOException {

		    HttpServletResponse response=(HttpServletResponse)UtileriasFaces.obtenContextoExterno().getResponse();
		    response.addHeader("Content-disposition", "attachment; filename="+pdf.getNombre()+".pdf");  
		    ServletOutputStream servletOStream=response.getOutputStream();
		    
		    servletOStream.write(pdf.getBytesPdf());
//		    Asi le digo que aqui no ha terminado la respuesta
		    UtileriasFaces.obtenInstanciaFacesActual().responseComplete();   
		    
	}

	@Override
	public String activaParaModificar() {
		String ret = super.activaParaModificar();
		
		// Para activar la captura en el campo cual empresa cuando no tenga empresa
		if(form.getDatoLaboral().getEmpresa().getId() == 666) {
			form.setSoloLecturaOtraEmpresa(true);
		}
		
		// Como la liga genera un submit a la forma, el convertidor de la carrera entra en accion
		// y ocaciona que se vuelvan a mostrar todos los tramites de la carrera
		form.ponerCuotasEnTramites();
		return ret;
		
		
	}
	

	
}