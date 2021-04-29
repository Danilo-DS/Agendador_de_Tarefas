package br.com.agendador_tafera.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import br.com.agendador_tafera.application.model.AgendarTarefa;
import br.com.agendador_tafera.application.service.AgendarTarefaService;


@RestController
@RequestMapping(value = "/api/v1/agendar-tarefa")
public class AgendarTarefaController {
	
	@Autowired
	private AgendarTarefaService service;
	
	/* EndPoint listar todas as Tarefa */
	@GetMapping
	@PreAuthorize("hasRole('ROLE_G')")
	public ResponseEntity<?> listAll(){
		return ResponseEntity.ok(service.listAllTask());
	}
	
	/* EndPoint Buscar tarefa por id*/
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_G') or hasRole('ROLE_U')")
	public ResponseEntity<?> findTask(@PathVariable Long id){
		return ResponseEntity.ok(service.findTaskId(id));
	}
	
	/* EndPoint Lista Tarefas por usuario*/
	@GetMapping(value = "/{id}/usuario")
	@PreAuthorize("hasRole('ROLE_G') or hasRole('ROLE_U')")
	public ResponseEntity<List<?>> findTaskUser(@PathVariable Long id){
		return ResponseEntity.ok(service.findTaskToUsuario(id));
	}
	
	/* EndPoint Salvar Tarefa */
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	@PreAuthorize("hasRole('ROLE_G')")
	public void save(@RequestBody AgendarTarefa at){
		service.saveTask(at);
	}
	
	/* EndPoint Atualizar Tarefa */
	@PutMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_G')")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody AgendarTarefa at){
		service.updateTask(id, at);
		return ResponseEntity.ok().build();
	}
	
	/* EndPoint Excluir Tarefa */
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_G')")
	public ResponseEntity<?> delete(@PathVariable Long id){
		service.deleteTask(id);
		return ResponseEntity.noContent().build();
	}
	
}	
