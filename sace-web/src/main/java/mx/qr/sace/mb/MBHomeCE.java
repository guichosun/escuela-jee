package mx.qr.sace.mb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import mx.qr.sace.ce.negocio.CatalogosCELocal;
import mx.qr.sace.persistencia.entidades.Carrera;

import org.apache.log4j.Logger;
import org.primefaces.model.chart.MeterGaugeChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Class para modelar el home en CE con el comportamiento para las graficas y de mas cosas que hay en el home de marketing.
 *  
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Octubre 2015
 * @copyright Q & R
 */
public class MBHomeCE extends MBHomeBase {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3174672640822159989L;

	private static final Logger log = Logger.getLogger(MBHomeCE.class);
	
	@EJB(mappedName="java:global/sace-ear/sace-controlescolar-ejb/catalogosControlEscolar")
	private CatalogosCELocal beanCatalogoCE;
	
	@Autowired
	@Qualifier("mbHome")
	private transient MBHome mbHome;
	
	/**
	 * Inicia valores para el acceso al home. 
	 */
	public void initHome() {
log.info("Entro al home de CE");
		List<Carrera> carrs = beanCatalogoCE.obtenCarrerasPorEscolaridadYModalidad(
				mbHome.getSistemaEstudioUsuario().getEscolaridad().getIdEscolaridad(),
				mbHome.getSistemaEstudioUsuario().getModalidad().getIdModalidad());
		
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
		medidorCupoMedidor.setGaugeLabel("Inscritos");
		medidorCupoMedidor.setGaugeLabelPosition("bottom");
//		medidorCupoMedidor.setShowTickLabels(false);
		medidorCupoMedidor.setLabelHeightAdjust(5);
		medidorCupoMedidor.setIntervalOuterRadius(80);
		
		return medidorCupoMedidor;
	}
}
