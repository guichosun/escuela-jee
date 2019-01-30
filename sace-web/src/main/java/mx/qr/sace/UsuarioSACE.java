package mx.qr.sace;

import java.io.Serializable;
import java.util.Collection;

import mx.qr.sace.persistencia.entidades.AreaAdscrita;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * Usuario del sistema SACE que contiene la informaciòn referente a caracteristicas del usuario
 * en el sistema.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Enero 2016
 * @copyright Q & R
 */
public class UsuarioSACE extends User implements Serializable {
	
	private static final long serialVersionUID = 785486395429416026L;
	
	protected AreaAdscrita areaAdscrita;
	
    public UsuarioSACE(String username, String password,
            Collection<? extends GrantedAuthority> authorities) {

        super(username, password, authorities);

    }

	/**
	 * @param username
	 * @param password
	 * @param enabled
	 * @param accountNonExpired
	 * @param credentialsNonExpired
	 * @param accountNonLocked
	 * @param authorities
	 */
	public UsuarioSACE(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
	}

	public AreaAdscrita getAreaAdscrita() {
		return areaAdscrita;
	}

	public void setAreaAdscrita(AreaAdscrita areaAdscrita) {
		this.areaAdscrita = areaAdscrita;
	}
    
    
}
