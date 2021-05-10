package br.com.agendador_tafera.application.controller.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agendador_tafera.application.service.perfilUsuario.PerfilService;

@RestController
@RequestMapping(value = "/api/v1/perfis-usuario")
public class PerfilUsuarioController {
	
	@Autowired
	private PerfilService perfilService;
	
	@GetMapping
	public ResponseEntity<?> listarPerfis(){
		return ResponseEntity.ok(perfilService.ListarPerfis());
	}
}
