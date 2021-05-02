package br.com.agendador_tafera.application.config.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.agendador_tafera.application.service.usuario.LoginService;
import br.com.agendador_tafera.application.utils.Utilitarios;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthTokenFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private AuthEntryPointJwt authEntryPointJwt;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String tokeJwt = parseJwt(request); 
			
			if(StringUtils.hasText(tokeJwt) && jwtUtils.validarToken(tokeJwt)) {
				
				String email = jwtUtils.getEmailFromTokenJwt(tokeJwt);
				
				UserDetails userDetails = loginService.loadUserByUsername(email);
				
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
			filterChain.doFilter(request, response);	
		}
		catch (AuthenticationException e) {
			authEntryPointJwt.commence(request, response, e);
		}	
		catch (Exception e) {
			log.error("Error: Autenticação Falhou: " + e.getMessage());
		}
		
	}

	private String parseJwt(HttpServletRequest req) {
		String headerRequest = req.getHeader(Utilitarios.HEADER_AUTH);
		if(StringUtils.hasText(headerRequest) && headerRequest.startsWith(Utilitarios.VALUE_HEADER_AUTH)) {
			return headerRequest.substring(7, headerRequest.length());
		}
		return null;
	}
	
	
}
