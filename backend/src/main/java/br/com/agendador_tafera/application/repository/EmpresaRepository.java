package br.com.agendador_tafera.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.agendador_tafera.application.model.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long>{

}
