package br.com.agendador_tafera.application.controller.empresa;

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

import br.com.agendador_tafera.application.dto.empresa.EmpresaRequestDTO;
import br.com.agendador_tafera.application.dto.empresa.EmpresaResponseDTO;
import br.com.agendador_tafera.application.service.empresa.EmpresaService;

@RestController
@RequestMapping(value = "/api/v1/empresa")
public class EmpresaController {
	
	@Autowired
	private EmpresaService empresaService;
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_MST')")
	public ResponseEntity<?> listaEmpresa(){
		return ResponseEntity.ok(empresaService.listarEmpresas());
	}
	
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_MST') or hasRole('ROLE_EMP')")
	public ResponseEntity<?> BuscarEmpresa(@PathVariable Long id){
		return ResponseEntity.ok(empresaService.buscarEmpresaPorId(id));
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public EmpresaResponseDTO salvar(@RequestBody EmpresaRequestDTO empresaResquest){
		return empresaService.salvarEmpresa(empresaResquest);
	}	
	
	@PutMapping(value = "/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	@PreAuthorize("hasRole('ROLE_MST') or hasRole('ROLE_EMP')")
	public ResponseEntity<?> atualizar(@RequestBody EmpresaRequestDTO empresaResquest, @PathVariable Long id){
		return ResponseEntity.ok(empresaService.atualizarEmpresa(empresaResquest, id));
	}
	
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_MST') or hasRole('ROLE_EMP')")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		empresaService.deletarEmpresa(id);
		return ResponseEntity.noContent().build();
	}
}
