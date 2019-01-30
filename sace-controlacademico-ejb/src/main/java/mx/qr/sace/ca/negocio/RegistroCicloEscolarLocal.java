package mx.qr.sace.ca.negocio;

import java.util.List;

import javax.ejb.Local;

import mx.qr.core.exception.ApplicationException;
import mx.qr.sace.persistencia.PeriodoEscolar;
import mx.qr.sace.persistencia.entidades.CicloEscolar;

/**
 * Define el negocio para hacer el registro 
 * a un ciclo escolar.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Mayo 2016
 * @copyright Q & R
 */
@Local
public interface RegistroCicloEscolarLocal {

	public void registra(CicloEscolar ciclo, List<PeriodoEscolar> periodos) 
			throws ApplicationException;
	
	public CicloEscolar consulta() throws ApplicationException;
	public void modifica() throws ApplicationException;
	public void elimina() throws ApplicationException;
}