package br.com.agendador_tafera.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import br.com.agendador_tafera.application.repository.UsuarioRepository;
import br.com.agendador_tafera.application.service.UsuarioService;
//@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "Authorization")
@RestController
@RequestMapping(value = "/api/v1/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private UsuarioRepository r;
	
	/* EndPoint Listar todos os Usuarios*/
	@GetMapping
	public ResponseEntity<?> listAll(){
		return ResponseEntity.ok(service.listAllUsers());
	}
	
	/* EndPoint Buscar Usuarios por id*/
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findUser(@PathVariable Long id){
		return ResponseEntity.ok(service.findUserId(id)); 
	}
	
	/* EndPoint Salvar Usuarios*/
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public void save(@RequestBody Usuario user){
		service.saveUser(user);
	}
	
	/* EndPoint Atualizar dados do Usuario*/
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Usuario user){
		service.updateUser(user, id);
		return ResponseEntity.ok().build();
	}
	
	/* EndPoint Exclusão do usuario*/
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		service.deleteUser(id);
		return ResponseEntity.noContent().build(); 
	}
	
	
	@GetMapping(value = "/t/{email}")
	public ResponseEntity<?> findemail(@PathVariable String email){
		Usuario u = r.findByEmail(email);
		System.out.println(u.toString());
		return ResponseEntity.ok(u); 
	}
	
}
