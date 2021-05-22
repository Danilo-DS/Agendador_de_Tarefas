package br.com.agendador_tafera.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.agendador_tafera.application.model.AgendarTarefa;
import br.com.agendador_tafera.application.model.Empresa;
import br.com.agendador_tafera.application.model.Usuario;

@Repository
public interface AgendaTarefaRepository extends JpaRepository<AgendarTarefa, Long>{
	
	List<AgendarTarefa> findByUsuario(Usuario usuario);
	
	List<AgendarTarefa> findByEmpresa(Empresa empresa);
	
	@Query( value = "SELECT * FROM TB_TAREFAS T"
			+ " INNER JOIN TB_TAREFAS_USUARIOS TU ON T.ID = TU.ID_TAREFA"
			+ " WHERE TU.ID_USUARIO = ?1 ORDER BY 1 ASC LIMIT(1)", nativeQuery = true)
	AgendarTarefa buscarProximaTarefa(Long idUsuario);
	
}
