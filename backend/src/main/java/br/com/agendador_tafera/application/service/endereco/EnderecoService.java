package br.com.agendador_tafera.application.service.endereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agendador_tafera.application.model.Endereco;
import br.com.agendador_tafera.application.repository.EnderecoRepository;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Endereco buscarEnderecoPorId(Long id) {
		return enderecoRepository.findById(id).orElseThrow(() -> new RuntimeException("Error ao localizar endereço do Funcionario"));
	}
}
