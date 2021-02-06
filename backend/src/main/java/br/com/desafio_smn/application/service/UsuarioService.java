package br.com.desafio_smn.application.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.desafio_smn.application.ModelConvert;
import br.com.desafio_smn.application.exception.usuario.UsuarioException;
import br.com.desafio_smn.application.model.Usuario;
import br.com.desafio_smn.application.modelDTO.UsuarioDTO;
import br.com.desafio_smn.application.repository.UsuarioRepository;
import br.com.desafio_smn.application.utils.Utilitarios;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository userRepository;
	
	@Transactional(readOnly = true)
	public List<UsuarioDTO> listAllUsers(){
		return toDTO(userRepository.findAll());
	}
	
	@Transactional(readOnly = true)
	public Usuario findUserId(Long id) {
		Optional<Usuario> user = userRepository.findById(id);
		return user.orElseThrow(() -> new UsuarioException(Utilitarios.ErrorBuscarUsuario, HttpStatus.NOT_FOUND));
	}
	
	@Transactional
	public void saveUser(Usuario user) {
		userRepository.save(user);
	}
	
	@Transactional
	public void updateUser(Usuario u, Long id) {
		if(verifyUser(id)) {
			userRepository.save(u);
		}
		else {
			throw new UsuarioException(Utilitarios.ErrorAtualizarUsuario, HttpStatus.NOT_FOUND);
		}
	}
	
	@Transactional
	public void deleteUser(Long id) {
		if(verifyUser(id)) {
			userRepository.deleteById(id);
		}
		else {
			throw new UsuarioException(Utilitarios.ErrorDeletarUsuario + " " + Utilitarios.ErrorBuscarUsuario, HttpStatus.NOT_FOUND);
		}
	}
	
	/* Verifica se o usuario existe na base
	 * @param id do usuario
	 * @return true para OK e false para FAIL*/
	@Transactional(readOnly = true)
	public boolean verifyUser(Long id) {
		if(userRepository.existsById(id)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/* Converte uma lista Objs do tipo Usuario em uma list UsuarioDTO */ 
	private List<UsuarioDTO> toDTO(List<Usuario> user) {
		return user.stream().map(u -> ModelConvert.mapper().map(u, UsuarioDTO.class)).collect(Collectors.toList());
		
	}
}
