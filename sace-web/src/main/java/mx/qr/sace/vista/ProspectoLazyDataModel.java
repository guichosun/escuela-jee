package mx.qr.sace.vista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import mx.qr.sace.AlertaVisualProspecto;
import mx.qr.sace.marketing.negocio.ComentariosProspectoLocal;
import mx.qr.sace.marketing.negocio.ProspectosPaginadoLocal;
import mx.qr.sace.persistencia.entidades.Alumno;
import mx.qr.sace.persistencia.entidades.Escolaridad;
import mx.qr.sace.persistencia.entidades.Modalidad;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * Class concreta para cargar losprecandidatos "on-demand"
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">
 * @since Octubre 2015
 * @copyright Direccion de sistemas - IFE
 */
public class ProspectoLazyDataModel extends
		LazyDataModel<AlertaVisualProspecto> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7252104123837615632L;

	private ProspectosPaginadoLocal paginadoProspecto;
	
	private ComentariosProspectoLocal comentarioProspecto;
	
	/* La lista con los datos */
	private List<AlertaVisualProspecto> datos;
	
	private Escolaridad escolaridad;
	
	private Modalidad modalidad;

	/** Constructor con lo necesario para el LazyDataModel */
	public ProspectoLazyDataModel() {
		/*
		 * Como una alternativa de la inyeccion de la dependencia EJB que no
		 * esta soportada por esta version de jsf 2.1.x sino hasta la 2.3
		 */
		try {
			InitialContext ic = new InitialContext();
			paginadoProspecto = (ProspectosPaginadoLocal) ic.lookup("java:global/sace-ear/sace-marketing-ejb/ProspectosPaginadoEJB");
			comentarioProspecto = (ComentariosProspectoLocal) ic.lookup("java:global/sace-ear/sace-marketing-ejb/ComentariosProspectoEJB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Construye el paginador para la escolaridad y modalidad especifica.
	 *  
	 * @param escolaridad
	 * @param modalidad
	 */
	public ProspectoLazyDataModel(Escolaridad escolaridad, Modalidad modalidad) {
		this();
		this.escolaridad = escolaridad;
		this.modalidad = modalidad;
	}



	@Override
	public List<AlertaVisualProspecto> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		
		int tipoOrden = 1;
		if(sortOrder == SortOrder.DESCENDING) {
			tipoOrden = 2;
		}
		
		// Traer la pagina
		List<Alumno> alumnos = paginadoProspecto.recuperaProspectosPorPaginado(first, pageSize, sortField, 
				tipoOrden, filters, escolaridad, modalidad);
		
		List<AlertaVisualProspecto> alertas = new ArrayList<AlertaVisualProspecto>();
		AlertaVisualProspecto alerta = null;
		// Por cada uno ir por su comentario y formar la Alerta visual
		for(Alumno alumno : alumnos) {
			alerta = new AlertaVisualProspecto();
			alerta.setProspecto(alumno);
			
			// DONE Llamar al bean para traer al comentario del prospecto
			alerta.setComentario(comentarioProspecto.recuperaDeUnProspecto(alumno).getComentario());
			
			alertas.add(alerta);
		}
		
		return alertas;
	}
	
	public int getRowCount() {

		return paginadoProspecto.recuperaTotalDeRegistros(escolaridad, modalidad);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.primefaces.model.LazyDataModel#getRowData(java.lang.String)
	 */
	@Override
    public AlertaVisualProspecto getRowData(String rowKey) {
		
		for(AlertaVisualProspecto registro: this.datos) {
			if(registro.getProspecto().getIdAlumno() == Integer.parseInt(rowKey)) {
				return registro;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.primefaces.model.LazyDataModel#getRowKey(java.lang.Object)
	 */
	@Override
    public String getRowKey(AlertaVisualProspecto dtoPaginador) {
		return String.valueOf(dtoPaginador.getProspecto().getIdAlumno());
	}

	/*
	 * (non-Javadoc)
	 * @see org.primefaces.model.LazyDataModel#setRowIndex(int)
	 */
	@Override
	public void setRowIndex(int rowIndex) {

        if (rowIndex == -1 || getPageSize() == 0) {
        	
            super.setRowIndex(-1);
           
        } else {
  
            super.setRowIndex(rowIndex % getPageSize());
        }      
    }

	public List<AlertaVisualProspecto> getDatos() {
		return datos;
	}
	
}