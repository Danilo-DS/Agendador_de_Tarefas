package br.com.desafio_smn.application.controller.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio_smn.application.model.Usuario;
import br.com.desafio_smn.application.service.UsuarioService;

@RestController
@RequestMapping(value = "/api/v1/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
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
	public void save(Usuario user){
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

}
