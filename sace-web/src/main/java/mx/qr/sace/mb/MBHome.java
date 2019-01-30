package mx.qr.sace.mb;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import mx.qr.core.ui.BigMenu;
import mx.qr.core.ui.Etapa;
import mx.qr.core.ui.ModeloBigMenu;
import mx.qr.core.ui.Modulo;
import mx.qr.core.ui.Pestanha;
import mx.qr.sace.UsuarioSACE;
import mx.qr.sace.dominio.SistemaEstudio;

import org.apache.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Julio 2015
 * @copyright Q & R
 */
@Component("mbHome")
@Scope("session")
public class MBHome extends MBHomeBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3174672640822159989L;

	private static final Logger log = Logger.getLogger(MBHome.class);
	
	/* El modelo ara el big menu del sistema */
	private ModeloBigMenu bigMenuModelo;
	
	/**
	 * Inicia valores para el acceso al home. 
	 */
	public String homeInteligente() {
		log.debug("Entra al home!");
		
		setSistemaEstudioUsuario(new SistemaEstudio(ESCOLARIDAD_DEFAULT, MODALIDAD_DEFAULT));
		
		handleBuildMenu();
		
		return getTransicion();
	}
	
	/*
	 * Obtiene la trancision al home que le corresponde al usuario recien registrado en el sistema.
	 */
	private String getTransicion() {
		UsuarioSACE dtoUsuarioSesion = obtieneUsuario();
		
		log.info("Entro: "+dtoUsuarioSesion.getAreaAdscrita());
		
		/*
		 * TODO De acuerdo al usuario seleccionar la escolaridad y la modalidad,
		 * de lo contrario usa valores por defaut
		 */
		switch (dtoUsuarioSesion.getAreaAdscrita().getId()) {
			case 1:
				return "homeROOT";
			case 2:
				return "sucMKT";
			case 3:
				return "sucFNZ";
			case 4:
				return "sucCE";
			case 5:
				return "sucCA";
			default:
				return "";
		}
		
	}
	
	/**
	 * Maneja la accion del cambio en los combos de estados y distritos, 
	 * adicional de pintar el menu al seleccionar el estado  
	 */
	public void handleBuildMenu() {
		
//		menuPrincipal.clear();
		
//		ParametrosMenu ops = new ParametrosMenuBase(74, 0, 0, "od.admin.oc");
//		UsuarioSupycap  usuario = obtieneUsuario();
		
//		ParametrosMenu ops = new ParametrosMenuBase(usuario.getIdSistema(),
//				getUbicacionSistema().getIdEdo(),
//				getUbicacionSistema().getIdDto(),
//				usuario.getGrupo());
		
//		try {
//			menuPrincipal = asAdminServi.obtenMenu(ops, false);
//			bigMenuModelo = asAdminServi.obtenBigMenu(ops);
			bigMenuModelo = menuFijo();
//		} catch (ApplicationException e) {
//			e.printStackTrace();
//		}
	}
	
	private ModeloBigMenu menuFijo() {
		BigMenu bigMenuModelo = new BigMenu();
		
		List<Pestanha> pestanhas = new ArrayList<Pestanha>();
		Pestanha pestanhaAdm = new Pestanha("Administración");
		Pestanha pestanha = null;
		Etapa etapa = null;
		Modulo modulo = null;

		if(hasRole("ROLE_RECEPCION") || hasRole("ROLE_ROOT")) {
			pestanha = new Pestanha("Recepción");
			pestanhas.add(pestanha);		
			etapa = new Etapa("Entrada");
			pestanha.getEtapas().add(etapa);
			etapa = new Etapa("Salida");
			pestanha.getEtapas().add(etapa);
			
			bigMenuModelo.setPestanhas(pestanhas);
		}

		if(hasRole("ROLE_MKT_DIR") || hasRole("ROLE_MKT") || hasRole("ROLE_ROOT")) {
			pestanha = new Pestanha("Ventas");
			pestanhas.add(pestanha);
			
			etapa = new Etapa("Prospecto");
			pestanha.getEtapas().add(etapa);
			modulo = new Modulo("Registro","/app/marketing/ventas/prospecto/registro/captura");
			etapa.getModulos().add(modulo);
			modulo = new Modulo("Cancela","/app/marketing/ventas/prospecto/cancela/captura");
			etapa.getModulos().add(modulo);
			
			bigMenuModelo.setPestanhas(pestanhas);
		}
			
		if(hasRole("ROLE_FNZ_DIR") || hasRole("ROLE_FNZ") || hasRole("ROLE_ROOT")) {
			pestanha = new Pestanha("Caja");
			pestanhas.add(pestanha);
			
			etapa = new Etapa("Pagos");
			pestanha.getEtapas().add(etapa);
			modulo = new Modulo("Fichas Pago","/app/finanzas/caja/pagos/fichas/captura");
			etapa.getModulos().add(modulo);
			modulo = new Modulo("Conceptos","/app/finanzas/caja/pagos/conceptos/captura");
			etapa.getModulos().add(modulo);
			
			bigMenuModelo.setPestanhas(pestanhas);
		}
		
		if(hasRole("ROLE_CE") || hasRole("ROLE_ROOT")) {
			pestanha = new Pestanha("Alumno");
			pestanhas.add(pestanha);
			etapa = new Etapa("Tramites");
			pestanha.getEtapas().add(etapa);
			modulo = new Modulo("Inscripcion","/app/ctrl_escolar/alumno/tramites/inscripcion/captura");
			etapa.getModulos().add(modulo);
			
			etapa = new Etapa("Revalidar");
			pestanha.getEtapas().add(etapa);
			modulo = new Modulo("Equivalencia","#");
			etapa.getModulos().add(modulo);
			
			pestanha = new Pestanha("Grupo");
			pestanhas.add(pestanha);
			etapa = new Etapa("Calificaciones");
			pestanha.getEtapas().add(etapa);
			modulo = new Modulo("Asentar","#");
			etapa.getModulos().add(modulo);

			bigMenuModelo.setPestanhas(pestanhas);	
		}
		
		if(hasRole("ROLE_CA") || hasRole("ROLE_CA_DIR") || hasRole("ROLE_ROOT")) {
			pestanha = new Pestanha("CA");
			pestanhas.add(pestanha);
			etapa = new Etapa("Registro");
			pestanha.getEtapas().add(etapa);
			modulo = new Modulo("Grupo","/app/ctrl_academico/CA/registro/grupo/captura");
			etapa.getModulos().add(modulo);
			
			bigMenuModelo.setPestanhas(pestanhas);
			
		}
			
		if(hasRole("ROLE_MKT_DIR") || hasRole("ROLE_ROOT")) {
			if(!pestanhas.contains(pestanhaAdm)) {
				pestanhas.add(pestanhaAdm);
			} else {
				int id = pestanhas.indexOf(pestanhaAdm);
				pestanhaAdm = pestanhas.get(id); 
			}
			
			etapa = new Etapa("Catalogos");
			pestanhaAdm.getEtapas().add(etapa);
			modulo = new Modulo("Beca","/app/marketing/administracion/catalogos/beca/captura");
			etapa.getModulos().add(modulo);
			modulo = new Modulo("Empresa Convenio",null);
//				modulo = new Modulo("Empresa Convenio","/app/marketing/administracion/catalogos/emp_convenio/captura");
			etapa.getModulos().add(modulo);
			
		}
		
		if(hasRole("ROLE_FNZ_DIR") || hasRole("ROLE_ROOT")) {
			if(!pestanhas.contains(pestanhaAdm)) {
				pestanhas.add(pestanhaAdm);
			} else {
				int id = pestanhas.indexOf(pestanhaAdm);
				pestanhaAdm = pestanhas.get(id); 
			}
			
			etapa = new Etapa("Catalogos");
			pestanhaAdm.getEtapas().add(etapa);
			modulo = new Modulo("Empleado","/app/finanzas/administracion/catalogos/empleado/captura");
			etapa.getModulos().add(modulo);
		}

		if(hasRole("ROLE_CA_DIR") || hasRole("ROLE_ROOT")) {
			
			if(!pestanhas.contains(pestanhaAdm)) {
				pestanhas.add(pestanhaAdm);
			} else {
				int id = pestanhas.indexOf(pestanhaAdm);
				pestanhaAdm = pestanhas.get(id); 
			}
			
			etapa = new Etapa("Catalogos");
			pestanhaAdm.getEtapas().add(etapa);
			modulo = new Modulo("Profesores","/app/ctrl_academico/administracion/catalogo/profesores/captura");
			etapa.getModulos().add(modulo);
			modulo = new Modulo("Plan de Estudio","/app/marketing/administracion/catalogos/emp_convenio/captura");
			etapa.getModulos().add(modulo);
			
			etapa = new Etapa("Planeación");
			pestanhaAdm.getEtapas().add(etapa);
//				modulo = new Modulo("Generación","/app/marketing/administracion/catalogos/beca/captura");
//				etapa.getModulos().add(modulo);
			modulo = new Modulo("Ciclo Escolar","/app/ctrl_academico/administracion/planeacion/ciclo_esc/captura");
			etapa.getModulos().add(modulo);
		}
		return bigMenuModelo;
		
	}
	
	
	protected boolean hasRole(String role) {
        // get security context from thread local
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null)
            return false;

        Authentication authentication = context.getAuthentication();
        if (authentication == null)
            return false;

        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if (role.equals(auth.getAuthority()))
                return true;
        }

        return false;
    }

	public ModeloBigMenu getBigMenuModelo() {
		return bigMenuModelo;
	}

	public void setBigMenuModelo(ModeloBigMenu bigMenuModelo) {
		this.bigMenuModelo = bigMenuModelo;
	}
	
	private StreamedContent imgEstatusGrupo;
	private StreamedContent imgEstatusGrupo2;
	private StreamedContent imgEstatusGrupo3;
	public StreamedContent getImgEstatusGrupo() {
		return construirImagenEstatus(Color.RED);
	}
	public StreamedContent getImgEstatusGrupo2() {
		return construirImagenEstatus(Color.ORANGE);
	}
	public StreamedContent getImgEstatusGrupo3() {
		return construirImagenEstatus(Color.GREEN);
	}

	public StreamedContent construirImagenEstatus(Color color) {
		//Graphic Text
        BufferedImage bufferedImg = new BufferedImage(25, 25, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bufferedImg.createGraphics();
        g2.setBackground(Color.GREEN);
        g2.setColor(color);
        g2.fillRect(0, 0, 25, 25);
//        g2.drawString("This is a text", 0, 10);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        
        try {
        	
			ImageIO.write(bufferedImg, "png", os);
			return new DefaultStreamedContent(new ByteArrayInputStream(os.toByteArray()), "image/png");
			
		} catch (IOException e) {
			return null;
		}
	}
}
