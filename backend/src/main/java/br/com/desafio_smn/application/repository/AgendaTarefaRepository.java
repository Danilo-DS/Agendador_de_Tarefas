package br.com.desafio_smn.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.desafio_smn.application.model.AgendarTarefa;

@Repository
public interface AgendaTarefaRepository extends JpaRepository<AgendarTarefa, Long>{
	
}
