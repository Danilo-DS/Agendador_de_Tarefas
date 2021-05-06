package br.com.agendador_tafera.application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.agendador_tafera.application.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
	
	Optional<Funcionario> findByCpf(String cpf);
	
	Boolean existsByCfp(String cpf);
}
