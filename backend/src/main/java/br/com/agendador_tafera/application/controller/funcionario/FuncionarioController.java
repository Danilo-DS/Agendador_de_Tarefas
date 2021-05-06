package br.com.agendador_tafera.application.controller.funcionario;

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

import br.com.agendador_tafera.application.dto.funcionario.FuncionarioRequestDTO;
import br.com.agendador_tafera.application.service.funcionario.FuncionarioService;

@RestController
@RequestMapping(value = "/api/v1/funcionario")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@GetMapping
	public ResponseEntity<?> listAll() {
		return ResponseEntity.ok(funcionarioService.listAllFuncionario());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findFuncionario(@PathVariable Long id) {
		return ResponseEntity.ok(funcionarioService.findFuncionarioId(id));
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public void save(@RequestBody FuncionarioRequestDTO funcionarioRequest) {
		funcionarioService.saveFuncionario(funcionarioRequest);
	}	
	
	@PutMapping
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<?> update(@RequestBody FuncionarioRequestDTO funcionarioRequest) {
		return ResponseEntity.ok(funcionarioService.updateFuncionario(funcionarioRequest));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		funcionarioService.deleteFuncionario(id);
		return ResponseEntity.noContent().build();
	}
}
