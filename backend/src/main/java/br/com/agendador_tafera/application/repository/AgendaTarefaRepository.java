package br.com.agendador_tafera.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.agendador_tafera.application.model.AgendarTarefa;
import br.com.agendador_tafera.application.model.Usuario;

@Repository
public interface AgendaTarefaRepository extends JpaRepository<AgendarTarefa, Long>{
	
	List<AgendarTarefa> findByUsuario(Usuario usuario);
}
