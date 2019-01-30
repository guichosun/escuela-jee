package mx.qr.sace.ca.negocio;

import java.util.List;

import javax.ejb.Local;

import mx.qr.sace.persistencia.entidades.CarreraId;
import mx.qr.sace.persistencia.entidades.CicloEscolar;

/**
 * Define el negocio para un informante del clclo escolar.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Mayo 2016
 * @copyright Q & R
 */
@Local
public interface InformanteCicloEscolarLocal {

	public CicloEscolar verCicloDeCarrera(CarreraId id);
	
	public List<CicloEscolar> verCiclosDeCarrera(CarreraId id);
	
	public void pedirPeriodosdeCiclo();
}