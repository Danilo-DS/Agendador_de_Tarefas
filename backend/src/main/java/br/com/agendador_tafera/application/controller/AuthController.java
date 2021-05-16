package br.com.agendador_tafera.application.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agendador_tafera.application.config.security.jwt.JwtUtils;
import br.com.agendador_tafera.application.config.security.payload.UserLogin;
import br.com.agendador_tafera.application.config.security.payload.UserRequest;
import br.com.agendador_tafera.application.config.security.payload.reponse.JwtResponse;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@PostMapping("/token")
	public ResponseEntity<?> authUser(@RequestBody UserRequest userRequest){
		
		Authentication authentication = authManager.authenticate(
			new UsernamePasswordAuthenticationToken(userRequest.getEmail(), userRequest.getSenha())
		);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);		
		UserLogin user = (UserLogin) authentication.getPrincipal(); 
		String token = jwtUtils.gerarTokenJwt(authentication);
		
		List<String> permissao = user.getAuthorities().stream().map(p -> p.getAuthority()).collect(Collectors.toList());
		
		return ResponseEntity.ok(new JwtResponse(token, user.getId(), user.getUsername(), permissao, user.getIdEmpresa()));
	}
}
