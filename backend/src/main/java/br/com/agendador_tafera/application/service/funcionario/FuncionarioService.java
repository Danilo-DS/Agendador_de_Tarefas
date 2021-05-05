package br.com.agendador_tafera.application.service.funcionario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agendador_tafera.application.model.Funcionario;
import br.com.agendador_tafera.application.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public List<Funcionario> listAllFuncionario() {
		
		return funcionarioRepository.findAll();
	}
	
	public Funcionario findFuncionarioId(Long id) {
		return funcionarioRepository.findById(id).orElseThrow(() -> new RuntimeException());
	}
	
	public void saveFuncionario(Funcionario funcionario) {
		Funcionario func = funcionario;
		funcionarioRepository.save(func);
	}
	
	public Funcionario updateFuncionario() {
		
		return null;
	}
	
	public void deleteFuncionario(Long id) {
		if(!isExistingFuncionario(id)) {
			throw new RuntimeException();
		}
		
		funcionarioRepository.deleteById(id);
	}
	
	private Boolean isExistingFuncionario(Long id) {
		return funcionarioRepository.existsById(id);
	}
	
}
