package br.com.agendador_tafera.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.agendador_tafera.application.model.AgendarTarefa;

@Repository
public interface AgendaTarefaRepository extends JpaRepository<AgendarTarefa, Long>{
	
}
