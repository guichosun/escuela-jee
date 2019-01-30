package mx.qr.sace.mb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import mx.qr.sace.ce.negocio.CatalogosCELocal;
import mx.qr.sace.dominio.SistemaEstudio;
import mx.qr.sace.marketing.negocio.RegistroProspectoLocal;
import mx.qr.sace.persistencia.entidades.Alumno;
import mx.qr.sace.persistencia.entidades.Carrera;
import mx.qr.sace.vista.ProspectoLazyDataModel;

import org.apache.log4j.Logger;
import org.primefaces.model.chart.MeterGaugeChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Class con el comportamiento para las graficas y de mas cosas que hay en el home de marketing.
 *  
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Octubre 2015
 * @copyright Q & R
 */
public class MBHomeMarketing extends MBHomeBase {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3174672640822159989L;

	private static final Logger log = Logger.getLogger(MBHomeMarketing.class);
	
	/* Es el bean para obtener los alumnos con estatus de prospecto */
	@EJB(beanInterface=RegistroProspectoLocal.class,
			mappedName="java:global/sace-ear/sace-marketing-ejb/RegistroProspectoEJB")
	private RegistroProspectoLocal beanProspecto;
	
	@EJB(mappedName="java:global/sace-ear/sace-controlescolar-ejb/catalogosControlEscolar")
	private CatalogosCELocal beanCatalogoCE;
	
	/* 
	 * Los medidores para las carrecas. 
	 * Por el momento como minimo hay 1 y como max hay hasta tres carreras por modalidad y nivel.
	 * Sin embargo esto puede crecer.
	 */
	private MeterGaugeChartModel medidorCupoMedidor;
	private MeterGaugeChartModel medidorCupoMedidor2;
	private MeterGaugeChartModel medidorCupoMedidor3;
	
	@Autowired
	@Qualifier("mbHome")
	private transient MBHome mbHome;
	
	/* El prospecto seleccionado de la tabla de notificaciones */
	private Alumno prospectoSeleccionado;
	
	/** 
	 * El sistema de estudios que se utilizará en los
	 * formularios de los modulos 
	 */
	private SistemaEstudio sistemaEstudioFormulario;
	
	private ProspectoLazyDataModel alertaVisualProspectosDataModel;
	
	/**
	 * Inicia valores para el acceso al home. 
	 */
	public void initHome() {

		List<Carrera> carrs = beanCatalogoCE.obtenCarrerasPorEscolaridadYModalidad(
				mbHome.getSistemaEstudioUsuario().getEscolaridad().getIdEscolaridad(),
				mbHome.getSistemaEstudioUsuario().getModalidad().getIdModalidad());
		
		medidorCupoMedidor = initMedidor(carrs.get(0).getNombre());
		if(carrs.size() > 1) {
			medidorCupoMedidor2 = initMedidor(carrs.get(1).getNombre());
			medidorCupoMedidor3 = initMedidor(carrs.get(2).getNombre());
		}
		
//		refrescaGraficas();
		
		/*
		 * Al entrar al home de MKT, obtener:
		 * 
		 * - TODO Los alumnos con estatus de prospecto.
		 * - 
		 */
		alertaVisualProspectosDataModel = new ProspectoLazyDataModel(mbHome.getSistemaEstudioUsuario().getEscolaridad(), 
				mbHome.getSistemaEstudioUsuario().getModalidad());
		
	}
	
	/**
	 * Para inicializar el sistema de estudios.
	 * 
	 * Al entrar a un modulo.
	 * De primera vez, el sistema sera tomado del usuario que entro en el sistema y
	 * para despues de la accion de guardar serà tomado de 
	 * la seleccion del componente en el modulo
	 * @param sistema
	 */
	public void inicializaSistemaEstudios() {
			// El sistema de estudios que se obtiene al entrar al sistema
			setSistemaEstudioFormulario(mbHome.getSistemaEstudioUsuario());
		
	}
	
	public void limpia() {
		// Reset Valor de la mdalidad
		getSistemaEstudioFormulario().setModalidad(MODALIDAD_DEFAULT);
	}
	
	public void refrescaEscolaridad() {
		getSistemaEstudioFormulario().getModalidad().setIdModalidad(0);
	}
	public void refrescaModalidad() {
	}
	
	private MeterGaugeChartModel initMedidor(String nomCarrera) {
		List<Number> intervals = new ArrayList<Number>();
		intervals.add(2);
		intervals.add(4);
		intervals.add(7);
		intervals.add(10);
        
        MeterGaugeChartModel medidorCupoMedidor = new MeterGaugeChartModel();
        medidorCupoMedidor.setValue(0);
		medidorCupoMedidor.setIntervals(intervals);
		medidorCupoMedidor.setTitle(nomCarrera);
		medidorCupoMedidor.setSeriesColors("cc6666, E7E658, 93b75f, 66cc66");
		medidorCupoMedidor.setGaugeLabel("Prospectos");
		medidorCupoMedidor.setGaugeLabelPosition("bottom");
		medidorCupoMedidor.setLabelHeightAdjust(5);
		medidorCupoMedidor.setIntervalOuterRadius(80);
		
		return medidorCupoMedidor;
	}

	public void refrescaGraficas() {
//		mbHome.getEscolaridadSeleccionada().getIdEscolaridad();
//		mbHome.getModalidadSeleccionada().getIdModalidad();
		//TODO Traer el conteo de todos los alumnos inscritos por carrera
		medidorCupoMedidor.setValue(1);
		
		if(medidorCupoMedidor2 != null) {
			medidorCupoMedidor.setValue(1);
		}
		if(medidorCupoMedidor3 != null) {
			medidorCupoMedidor.setValue(1);
		}
	}

	public Alumno getProspectoSeleccionado() {
		return prospectoSeleccionado;
	}


	public void setProspectoSeleccionado(Alumno prospectoSeleccionado) {
		this.prospectoSeleccionado = prospectoSeleccionado;
	}


	public ProspectoLazyDataModel getAlertaVisualProspectosDataModel() {
		return alertaVisualProspectosDataModel;
	}


	public void setAlertaVisualProspectosDataModel(ProspectoLazyDataModel prospectosDataModel) {
		this.alertaVisualProspectosDataModel = prospectosDataModel;
	}


	public MeterGaugeChartModel getMedidorCupoMedidor() {
		return medidorCupoMedidor;
	}


	public void setMedidorCupoMedidor(MeterGaugeChartModel medidorCupoMedidor) {
		this.medidorCupoMedidor = medidorCupoMedidor;
	}

	public MeterGaugeChartModel getMedidorCupoMedidor2() {
		return medidorCupoMedidor2;
	}

	public void setMedidorCupoMedidor2(MeterGaugeChartModel medidorCupoMedidor2) {
		this.medidorCupoMedidor2 = medidorCupoMedidor2;
	}

	public MeterGaugeChartModel getMedidorCupoMedidor3() {
		return medidorCupoMedidor3;
	}

	public void setMedidorCupoMedidor3(MeterGaugeChartModel medidorCupoMedidor3) {
		this.medidorCupoMedidor3 = medidorCupoMedidor3;
	}

	public SistemaEstudio getSistemaEstudioFormulario() {
		return sistemaEstudioFormulario;
	}

	public void setSistemaEstudioFormulario(SistemaEstudio sistemaEstudioFormulario) {
		this.sistemaEstudioFormulario = sistemaEstudioFormulario;
	}
}
