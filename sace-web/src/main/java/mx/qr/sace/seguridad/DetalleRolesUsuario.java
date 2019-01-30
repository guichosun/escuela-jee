package mx.qr.sace.seguridad;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import mx.qr.sace.UsuarioSACE;
import mx.qr.sace.persistencia.entidades.AreaAdscrita;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

/**
 * lass para poder crear una instancia del UsuarioSACE para asi poder determinar
 * que el area adscrita del usuario.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del
 *         Campo</a>
 * @since Enero 2016
 * @copyright Q & R
 */
public class DetalleRolesUsuario extends JdbcDaoImpl {

	private static final Logger logger = Logger
			.getLogger(DetalleRolesUsuario.class);
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		return super.loadUserByUsername(username);
	}

	/*
	 * Can be overridden to customize the creation of the final
	 * UserDetailsObject which is returned by the loadUserByUsername method.
	 * protected String
	 */
	@Override
	protected UserDetails createUserDetails(String username,
			UserDetails userFromUserQuery,
			List<GrantedAuthority> combinedAuthorities) {
		/*
		 * - Dejar que lo cargue spring una vez cargado, 
		 * - Consultar su area adscrita. 
		 * - Crear una instancia de UsuarioSACE con la area adscrita
		 */
		UsuarioSACE user = null;
logger.debug("El usuario "+username+" entra a ver los roles a la DB");
		AreaAdscrita aA = getJdbcTemplate().query(
				"select area_adscrita as aA from usuarios where nombre = ?",
				new String[] { username },
				new ResultSetExtractor<AreaAdscrita>() {
					@Override
					public AreaAdscrita extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						AreaAdscrita aa = new AreaAdscrita();
						while(rs.next()) {
							aa.setId(rs.getInt("aA"));
						}
						return aa;
					}
		});

		user = new UsuarioSACE(username, userFromUserQuery.getPassword(),
				userFromUserQuery.isEnabled(),
				userFromUserQuery.isAccountNonExpired(), 
	            userFromUserQuery.isCredentialsNonExpired(),
				userFromUserQuery.isAccountNonLocked(), combinedAuthorities);
		
		user.setAreaAdscrita(aA);
logger.debug(user);
		return user;
		// return super
		// .createUserDetails(username, userFromUserQuery, combinedAuthorities);
	}

}
