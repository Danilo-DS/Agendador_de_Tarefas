package br.com.desafio_smn.application.controller.agendarTarefa;

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

import br.com.desafio_smn.application.model.AgendarTarefa;
import br.com.desafio_smn.application.service.AgendarTarefaService;

@RestController
@RequestMapping(value = "/api/v1/agendar-tarefa")
public class AgendarTarefaController {
	
	@Autowired
	private AgendarTarefaService service;
	
	/* EndPoint listar todas as Tarefa */
	@GetMapping
	public ResponseEntity<?> listAll(){
		return ResponseEntity.ok(service.listAllTask());
	}
	
	/* EndPoint Buscar tarefa por id*/
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findTask(@PathVariable Long id){
		return ResponseEntity.ok(service.findTaskId(id));
	}
	
	/* EndPoint Salvar Tarefa */
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public void save(AgendarTarefa at){
		service.saveTask(at);
	}
	
	/* EndPoint Atualizar Tarefa */
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody AgendarTarefa at){
		System.out.println("DEGUBD" + at.toString());
		service.updateTask(id, at);
		return ResponseEntity.ok().build();
	}
	
	/* EndPoint Excluir Tarefa */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		service.deleteTask(id);
		return ResponseEntity.noContent().build();
	}
	
}	
