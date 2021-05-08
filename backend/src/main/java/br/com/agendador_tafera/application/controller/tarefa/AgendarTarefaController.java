package br.com.agendador_tafera.application.controller.tarefa;

import java.util.List;

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

import br.com.agendador_tafera.application.dto.tarefa.TarefaRequestDTO;
import br.com.agendador_tafera.application.service.tarefa.AgendarTarefaService;


@RestController
@RequestMapping(value = "/api/v1/agendar-tarefa")
public class AgendarTarefaController {
	
	@Autowired
	private AgendarTarefaService service;
	
	/* EndPoint listar todas as Tarefa */
	@GetMapping
	@PreAuthorize("hasRole('ROLE_G')")
	public ResponseEntity<?> listarTarefas(){
		return ResponseEntity.ok(service.listarTarefas());
	}
	
	/* EndPoint Buscar tarefa por id*/
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_G') or hasRole('ROLE_U')")
	public ResponseEntity<?> buscarTarefa(@PathVariable Long id){
		return ResponseEntity.ok(service.buscarTarefaPorId(id));
	}
	
	/* EndPoint Lista Tarefas por usuario*/
	@GetMapping(value = "/{id}/usuario")
	@PreAuthorize("hasRole('ROLE_G') or hasRole('ROLE_U')")
	public ResponseEntity<List<?>> buscarTarefaUsuario(@PathVariable Long id){
		return ResponseEntity.ok(service.buscarTarefaPorUsuario(id));
	}
	
	/* EndPoint Salvar Tarefa */
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	@PreAuthorize("hasRole('ROLE_G')")
	public void salvar(@RequestBody TarefaRequestDTO tarefaRequest){
		service.salvarTarefa(tarefaRequest);
	}
	
	/* EndPoint Atualizar Tarefa */
	@PutMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_G')")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody TarefaRequestDTO tarefaRequest){
		return ResponseEntity.ok(service.atualizarTarefa(tarefaRequest, id));
	}
	
	@PutMapping(value = "/{id}/finalizar")
	@PreAuthorize("hasRole('ROLE_G')")
	public ResponseEntity<?> finalizar(@PathVariable Long id){
		return ResponseEntity.ok(service.finalizarTarefa(id));
	}
	
	@PutMapping(value = "/{id}/cancelar")
	@PreAuthorize("hasRole('ROLE_G')")
	public ResponseEntity<?> cancelar(@PathVariable Long id){
		return ResponseEntity.ok(service.cancelarTarefa(id));
	}
	
	/* EndPoint Excluir Tarefa */
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_G')")
	public ResponseEntity<?> delete(@PathVariable Long id){
		service.deletatTarefa(id);
		return ResponseEntity.noContent().build();
	}
	
}	
