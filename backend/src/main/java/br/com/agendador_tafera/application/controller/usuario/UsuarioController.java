package br.com.agendador_tafera.application.controller.usuario;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.agendador_tafera.application.dto.usuario.UsuarioRequestDTO;
import br.com.agendador_tafera.application.dto.usuario.UsuarioResponseDTO;
import br.com.agendador_tafera.application.service.usuario.UsuarioService;

@RestController
@RequestMapping(value = "/api/v1/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	/* EndPoint Listar todos os Usuarios*/
	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADM')")
	public ResponseEntity<?> listAll(){
		return ResponseEntity.ok(service.listarUsuarios());
	}
	
	/* EndPoint Buscar Usuarios por id*/
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_G')")
	public ResponseEntity<?> findUser(@PathVariable Long id){
		return ResponseEntity.ok(service.buscarUsuarioPorId(id)); 
	}
	
	/* EndPoint Salvar Usuarios*/
	@PostMapping
	@PreAuthorize("hasRole('ROLE_G')")
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public UsuarioResponseDTO save(@RequestBody UsuarioRequestDTO usuarioRequest){
		return service.salvarUsuario(usuarioRequest);
	}
	
	/* EndPoint Atualizar dados do Usuario*/
	@PutMapping
	@PreAuthorize("hasRole('ROLE_G') or hasRole('ROLE_U')")
	public ResponseEntity<?> update(@RequestBody UsuarioRequestDTO usuarioRequest){
		return ResponseEntity.ok(service.atualizarUsuario(usuarioRequest));
	}
	
	/* EndPoint Exclusão do usuario*/
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_G')")
	public ResponseEntity<?> delete(@PathVariable Long id){
		service.deletarUsuario(id);
		return ResponseEntity.noContent().build(); 
	}
			
}
