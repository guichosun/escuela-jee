package mx.qr.sace.mb;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Class con el comportamiento para las graficas y de mas cosas que hay en el home de marketing.
 *  
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Octubre 2015
 * @copyright Q & R
 */
public class MBHomeFinanzas extends MBHomeBase {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3174672640822159989L;

	private static final Logger log = Logger.getLogger(MBHomeFinanzas.class);
	
	@Autowired
	@Qualifier("mbHome")
	private transient MBHome mbHome;
	
	/**
	 * Inicia valores para el acceso al home. 
	 */
	public void initHome() {
		log.debug("Entra al home de finanzas");
		
	}
	
}
