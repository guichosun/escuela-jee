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
 * Class para modelar el home en CA con el comportamiento de inicio rapido
 *  
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Abril 2016
 * @copyright Q & R
 */
public class MBHomeCA extends MBHomeBase {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3174672640822159989L;

	private static final Logger log = Logger.getLogger(MBHomeCA.class);
	
	@Autowired
	@Qualifier("mbHome")
	private transient MBHome mbHome;
	
	/**
	 * Inicia valores para el acceso al home. 
	 */
	public void initHome() {
		log.info("Entra al home de CA");
		
	}
	
}
