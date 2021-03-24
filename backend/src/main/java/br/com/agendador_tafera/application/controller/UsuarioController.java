package br.com.agendador_tafera.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.agendador_tafera.application.model.Usuario;
import br.com.agendador_tafera.application.service.UsuarioService;

@RestController
@RequestMapping(value = "/api/v1/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	/* EndPoint Listar todos os Usuarios*/
	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_G')")
	public ResponseEntity<?> listAll(){
		return ResponseEntity.ok(service.listAllUsers());
	}
	
	/* EndPoint Buscar Usuarios por id*/
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasRole('G')")
	public ResponseEntity<?> findUser(@PathVariable Long id){
		return ResponseEntity.ok(service.findUserId(id)); 
	}
	
	/* EndPoint Salvar Usuarios*/
	@PostMapping
	@PreAuthorize("hasRole('G')")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void save(@RequestBody Usuario user){
		service.saveUser(user);
	}
	
	/* EndPoint Atualizar dados do Usuario*/
	@PutMapping(value = "/{id}")
	@PreAuthorize("hasRole('G') or hasRole('U')")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Usuario user){
		service.updateUser(user, id);
		return ResponseEntity.ok().build();
	}
	
	/* EndPoint Exclusão do usuario*/
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasRole('G')")
	public ResponseEntity<?> delete(@PathVariable Long id){
		service.deleteUser(id);
		return ResponseEntity.noContent().build(); 
	}
	
	@PreAuthorize("hasRole('G') or hasRole('U')")
	@GetMapping(value = "/t/test")
	public ResponseEntity<?> findall(){
		return ResponseEntity.ok(service.listAllUsers()); 
	}
	
}
