package br.com.agendador_tafera.application.config.security.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import br.com.agendador_tafera.application.config.security.payload.UserLogin;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Data
public class JwtUtils {
	
	@Value("${jwt.secret}")
	private String jwtSecret;
	
	@Value("${jwt.expiration}")
	private long jwtExpiration;
	
	private Date timeToken = null;
	
	public String gerarTokenJwt(Authentication auth) {
		
		UserLogin userLogin = (UserLogin) auth.getPrincipal();
		timeToken = new Date();
		
		return Jwts.builder().setSubject(userLogin.getEmail())
							 .setIssuedAt(timeToken)
							 .setExpiration(new Date(timeToken.getTime() + jwtExpiration))
							 .signWith(SignatureAlgorithm.HS512, jwtSecret)
							 .compact();
	}
	
	public String getEmailFromTokenJwt(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean validarToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		}
		catch (Exception e) {
			log.error("Error ao Validar Token: " + e.getMessage());
		}
		
		return false;
	}
	
}
