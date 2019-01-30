package mx.qr.sace.ce.bo;

import java.util.Date;

import javax.persistence.EntityManager;

import mx.qr.core.persistencia.EstatusAlumno;
import mx.qr.core.persistencia.JPADAO;
import mx.qr.sace.persistencia.dao.DocumentoEntregadoDAO;
import mx.qr.sace.persistencia.dao.impl.DocumentoEntregadoDAOImpl;
import mx.qr.sace.persistencia.entidades.DatosPersona;
import mx.qr.sace.persistencia.entidades.DocumentoEntregado;
import mx.qr.sace.persistencia.entidades.Requisito;

/**
 * Class encargada de administrar la logica del proceso de inscripcion.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del
 *         Campo</a>
 * @since Febrero 2016
 * @copyright Q & R
 */
public class ManagerInscripcion {

	/** Los identificadores de los requisitos */
	public final static int[] IDS_REQUISITOS = {5, 8, 3, 4, 7, 9};
	
	/** El estatus del del alumno de estudiante*/
	public final static EstatusAlumno ALUMNO_ESTUDIANTE = EstatusAlumno.ESTUDIANTE;

	@JPADAO
	private DocumentoEntregadoDAO docsEntregadoDAO;
	
	public ManagerInscripcion(EntityManager em) {
		docsEntregadoDAO = new DocumentoEntregadoDAOImpl(em);
	}
	
	/**
	 * Guarda los documentos entregados
	 * 
	 * @param docsEntregados
	 */
	public void guardaDocumentosEntregan(DatosPersona datoPer, boolean[] docsEntregados) {
		Requisito r = null;
		for(int x = 0; x < docsEntregados.length; x++) {
			if(docsEntregados[x]) {
				r = new Requisito(IDS_REQUISITOS[x]);
				DocumentoEntregado doc = new DocumentoEntregado();
	    		doc.setDatoPersonal(datoPer);
	    		doc.setDocumento(r);
	    		doc.setFechaRecepcion(new Date());
	    		docsEntregadoDAO.guarda(doc);
			}
    		
    	}
	}
}
