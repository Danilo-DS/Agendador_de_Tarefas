package br.com.agendador_tafera.application.service.perfilUsuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.agendador_tafera.application.exception.perfilusuario.PerfilException;
import br.com.agendador_tafera.application.model.PerfilUsuario;
import br.com.agendador_tafera.application.repository.PerfilRepository;
import br.com.agendador_tafera.application.utils.Utilitarios;

@Service
public class PerfilService {
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	@Transactional(readOnly = true)
	public List<PerfilUsuario> ListarPerfis() {
		return perfilRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public PerfilUsuario buscarPerfilPorId(Long id) {
		return perfilRepository.findById(id).orElseThrow(() -> new PerfilException(Utilitarios.ERROR_BUSCAR_PERFIL_USUARIO, HttpStatus.NOT_FOUND));
	}
}
