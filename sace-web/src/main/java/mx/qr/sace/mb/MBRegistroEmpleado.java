package mx.qr.sace.mb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import mx.qr.core.vista.FormularioBase;
import mx.qr.sace.ce.negocio.ProcesoInscripcionLocal;
import mx.qr.sace.formulario.FormularioRegistroEmpleado;
import mx.qr.sace.vista.NivelEstudioProfeDataModel;

import org.primefaces.event.RowEditEvent;

/**
 * MB para el proceso de registrar a un empleado
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del
 *         Campo</a>
 * @since Agosto 2016
 * @copyright Q & R
 */
public class MBRegistroEmpleado extends MBDefectoBase {

	private static final long serialVersionUID = -4173310045483132990L;

	private List<NivelEstudioProfeDataModel> niveles = new ArrayList<NivelEstudioProfeDataModel>();
	
	private NivelEstudioProfeDataModel nivel;
	
	private FormularioRegistroEmpleado form;
	
	@Override
	public void inicializa() {
		form = new FormularioRegistroEmpleado();
	}

	@Override
	public void inicializaForm(Integer como) {
		// TODO Auto-generated method stub
		setNivel(new NivelEstudioProfeDataModel());
	}

	@Override
	public FormularioRegistroEmpleado getForm() {
		return form;
	}
	
	public void agregaNivel(ActionEvent actionEvent) {
		niveles.add(getNivel());
		
		setNivel(new NivelEstudioProfeDataModel());
	}
	
	public void eliminaNivel(ActionEvent actionEvent) {
//		niveles.remove(o);
	}

	public List<NivelEstudioProfeDataModel> getNiveles() {
		return niveles;
	}

	public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Car Edited", ((NivelEstudioProfeDataModel) event.getObject()).getCarrera());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((NivelEstudioProfeDataModel) event.getObject()).getCedula());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

	public NivelEstudioProfeDataModel getNivel() {
		return nivel;
	}

	public void setNivel(NivelEstudioProfeDataModel nivel) {
		this.nivel = nivel;
	}
	
}
