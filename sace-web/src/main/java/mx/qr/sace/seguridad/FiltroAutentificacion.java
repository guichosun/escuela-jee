package mx.qr.sace.seguridad;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Class usada para el acceso al sistema por username, password y un captcha.
 * La validación del username y del password se hace en la DB 
 * @author 
 */
public class FiltroAutentificacion extends UsernamePasswordAuthenticationFilter {

	private static final Logger logger = Logger
			.getLogger(FiltroAutentificacion.class);

	private String succesUrl = "";
	private String errorUrl = "";
	private SimpleUrlAuthenticationSuccessHandler simpleUrlSucces = new SimpleUrlAuthenticationSuccessHandler();
	private SimpleUrlAuthenticationFailureHandler simpleUrlFailure = new SimpleUrlAuthenticationFailureHandler();
	
	public FiltroAutentificacion() {
		super();
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {

		logger.debug("El usuario: " + this.obtainUsername(request)
				+ " trata de entrar al sistema.");

		Authentication auth = null;
		
		// Validando usuario vacio
		this.obtainUsername(request);

		// Validando contraseña vacia
		this.obtainPassword(request);
		
		auth = autenticador (request,response);
		
		return auth;
	}
	
	public Authentication autenticador (HttpServletRequest request,HttpServletResponse response){
    	Authentication auth = null;
    	auth = super.attemptAuthentication(request, response);              
//  	  	if (true) {
//  	  		logger.error("Credenciales invalidas");
//  	  		throw new BadCredentialsException("Credenciales invalidas.");
//  	  	}
  	  	return auth;
    }
	
	@Override
	protected String obtainPassword(HttpServletRequest request) {
		String password = super.obtainPassword(request);
		if (password == null || password.isEmpty()) {
			throw new BadCredentialsException(
					"El campo contraseña es obligatorio, favor de verificarlo");
		}
		return password;
	}

	@Override
	protected String obtainUsername(HttpServletRequest request) {
		String userName = super.obtainUsername(request);
		if (userName == null || userName.isEmpty()) {
			throw new BadCredentialsException(
					"El campo Nombre de usuario es obligatorio, favor de verificarlo");
		}
		return userName;
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		logger.info("La autentificacion fue exitosa ");
		for(GrantedAuthority aut : authResult.getAuthorities()) {
			logger.debug(aut.getAuthority());
		}
		simpleUrlSucces.setDefaultTargetUrl(succesUrl);
		setAuthenticationSuccessHandler(simpleUrlSucces);
		logger.debug("EL SUCCESURL: "+succesUrl + " : " + simpleUrlSucces);
		super.successfulAuthentication(request, response, chain, authResult);
		
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException failed)
			throws IOException, ServletException {

		logger.info("La autentificacion fue erronea ");
		simpleUrlFailure.setDefaultFailureUrl(errorUrl);
		setAuthenticationFailureHandler(simpleUrlFailure);
		super.unsuccessfulAuthentication(request, response, failed);

	}

	public String getSuccesUrl() {
		return succesUrl;
	}

	public void setSuccesUrl(String succesUrl) {
		this.succesUrl = succesUrl;
	}

	public String getErrorUrl() {
		return errorUrl;
	}

	public void setErrorUrl(String errorUrl) {
		this.errorUrl = errorUrl;
	}
}
