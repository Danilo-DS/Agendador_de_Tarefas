package br.com.agendador_tafera.application.controller.funcionario;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.agendador_tafera.application.dto.funcionario.FuncionarioRequestDTO;
import br.com.agendador_tafera.application.dto.funcionario.FuncionarioResponseDTO;
import br.com.agendador_tafera.application.service.funcionario.FuncionarioService;

@RestController
@RequestMapping(value = "/api/v1/funcionario")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_MST')")
	public ResponseEntity<?> listAll() {
		return ResponseEntity.ok(funcionarioService.listarFuncionario());
	}
	
	@GetMapping(value = "/empresa")
	@PreAuthorize("hasRole('ROLE_MST')")
	public ResponseEntity<?> listaPorEmpresa(@RequestParam(name = "cnpjEmpresa") String cnpj) {
		return ResponseEntity.ok(funcionarioService.listaFuncionariosEmpresa(cnpj));
	}
	
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_MST')")
	public ResponseEntity<?> BuscarFuncionario(@PathVariable Long id) {
		return ResponseEntity.ok(funcionarioService.buscarFuncionarioPorId(id));
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_MST')")
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public FuncionarioResponseDTO salvar(@RequestBody FuncionarioRequestDTO funcionarioRequest) {
		return funcionarioService.salvarFuncionario(funcionarioRequest);
	}	
	
	@PutMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_MST')")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<?> atualizar(@RequestBody FuncionarioRequestDTO funcionarioRequest, @PathVariable Long id) {
		return ResponseEntity.ok(funcionarioService.atualizarFuncionario(funcionarioRequest, id));
	}
	
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_MST')")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		funcionarioService.deletarFuncionario(id);
		return ResponseEntity.noContent().build();
	}
}
