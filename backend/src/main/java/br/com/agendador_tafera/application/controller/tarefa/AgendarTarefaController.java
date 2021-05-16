package br.com.agendador_tafera.application.controller.tarefa;

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

import br.com.agendador_tafera.application.dto.tarefa.TarefaRequestDTO;
import br.com.agendador_tafera.application.dto.tarefa.TarefaResponseDTO;
import br.com.agendador_tafera.application.service.tarefa.AgendarTarefaService;


@RestController
@RequestMapping(value = "/api/v1/agendar-tarefa")
public class AgendarTarefaController {
	
	@Autowired
	private AgendarTarefaService service;
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_MST')")
	public ResponseEntity<?> listarTarefas(){
		return ResponseEntity.ok(service.listarTarefas());
	}
	
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_MST') or hasRole('ROLE_ADM') or hasRole('ROLE_EMP') or"
			+ " hasRole('ROLE_GST') or hasRole('ROLE_FUNC')")
	public ResponseEntity<?> buscarTarefa(@PathVariable Long id){
		return ResponseEntity.ok(service.buscarTarefaPorId(id));
	}
	
	@GetMapping(value = "/{id}/usuario")
	@PreAuthorize("hasRole('ROLE_MST') or hasRole('ROLE_ADM') or hasRole('ROLE_FUNC')")
	public ResponseEntity<?> listarTarefaUsuario(@PathVariable Long id){
		return ResponseEntity.ok(service.listarTarefaPorUsuario(id));
	}
	
	@GetMapping(value = "/{id}/empresa")
	@PreAuthorize("hasRole('ROLE_MST') or hasRole('ROLE_EMP') or hasRole('ROLE_GST')")
	public ResponseEntity<?> listarTarefaEmpresa(@PathVariable Long id){
		return ResponseEntity.ok(service.listarTarefaPorEmpresa(id));
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	@PreAuthorize("hasRole('ROLE_MST') or hasRole('ROLE_ADM') or hasRole('ROLE_EMP') or hasRole('ROLE_GST')")
	public TarefaResponseDTO salvar(@RequestBody TarefaRequestDTO tarefaRequest){
		return service.salvarTarefa(tarefaRequest);
	}
	
	@PutMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_MST') or hasRole('ROLE_ADM') or hasRole('ROLE_EMP') or hasRole('ROLE_GST')")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody TarefaRequestDTO tarefaRequest){
		return ResponseEntity.ok(service.atualizarTarefa(tarefaRequest, id));
	}
	
	@PutMapping(value = "/{id}/finalizar")
	@PreAuthorize("hasRole('ROLE_MST') or hasRole('ROLE_ADM') or hasRole('ROLE_EMP') or hasRole('ROLE_GST')")
	public ResponseEntity<?> finalizar(@PathVariable Long id){
		return ResponseEntity.ok(service.finalizarTarefa(id));
	}
	
	@PutMapping("hasRole('ROLE_MST') or hasRole('ROLE_ADM') or hasRole('ROLE_EMP') or hasRole('ROLE_GST')")
	@PreAuthorize("hasRole('ROLE_MST')")
	public ResponseEntity<?> cancelar(@PathVariable Long id){
		return ResponseEntity.ok(service.cancelarTarefa(id));
	}
	
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_MST') or hasRole('ROLE_ADM') or hasRole('ROLE_EMP') or hasRole('ROLE_GST')")
	public ResponseEntity<?> deletar(@PathVariable Long id){
		service.deletarTarefa(id);
		return ResponseEntity.noContent().build();
	}
	
}	
