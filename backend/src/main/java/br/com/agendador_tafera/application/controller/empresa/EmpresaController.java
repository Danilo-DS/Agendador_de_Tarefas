package br.com.agendador_tafera.application.controller.empresa;

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

import br.com.agendador_tafera.application.modelDTO.EmpresaRequestDTO;
import br.com.agendador_tafera.application.service.empresa.EmpresaService;

@RestController
@RequestMapping(value = "/api/v1/empresa")
public class EmpresaController {
	
	@Autowired
	private EmpresaService empresaService;
	
	@GetMapping
	public ResponseEntity<?> listAll(){
		return ResponseEntity.ok(empresaService.listAllEmpresa());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findEmpresa(@PathVariable Long id){
		return ResponseEntity.ok(empresaService.findEmpresaId(id));
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public void save(@RequestBody EmpresaRequestDTO empresaResquest){
		empresaService.saveEmpresa(empresaResquest);
	}	
	
	@PutMapping
	@ResponseStatus(value = HttpStatus.OK)
	public void update(EmpresaRequestDTO empresaResquest){
		empresaService.updateEmpresa(empresaResquest);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		empresaService.deleteEmpresa(id);
		return ResponseEntity.noContent().build();
	}
}
