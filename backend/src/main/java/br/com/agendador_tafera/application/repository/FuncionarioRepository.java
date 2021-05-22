package br.com.agendador_tafera.application.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.agendador_tafera.application.model.Funcionario;
import br.com.agendador_tafera.application.model.Usuario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
	
	Optional<Funcionario> findByCpf(String cpf);
	
	Boolean existsByCpf(String cpf);
	
	List<Funcionario> findByEmpresaCnpj(String cnpj);
	
	Boolean existsByUsuarioAndEmpresaId(Usuario usuario, Long idEmpresa);
	
	Optional<Funcionario> findByUsuarioId(Long id);
	
	
}
