package br.com.desafio_smn.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio_smn.application.ModelConvert;
import br.com.desafio_smn.application.model.Usuario;
import br.com.desafio_smn.application.modelDTO.UsuarioLoginDTO;
import br.com.desafio_smn.application.repository.UsuarioRepository;

@Service
public class LoginService {
	
	@Autowired
	UsuarioRepository userRepository;
	
	Usuario usuario;
	
	/* Valida se o email e a senha pertence a um usuario
	 * @return obj do tipo Usuario*/
	public Usuario loginCredencias(String email, String senha) {
		return userRepository.findByEmailAndSenha(email, senha);
	}
	
	/*Valida se o email do usuario na sessão
	 * está ativo e pega a permissão dele
	 * @param email do usuario*/
	 
	public UsuarioLoginDTO userPermissao(String email) {
		usuario = userRepository.findByEmail(email);
		return toDTOLogin(usuario);
	}
	
	/*Valida se o email do usuario na sessão
	 * existe.
	 * @param email do usuario
	 * @return true caso sim e false caso não*/
	public boolean userAtivo(String email) {
		if(userRepository.findByEmail(email) == null) {
			return true;
		}
		return false;
	}
	
	/* Converte um Obj do tipo Usuario em um obj de UsuarioLoginDTO */ 
	private UsuarioLoginDTO toDTOLogin(Usuario user) {
		return ModelConvert.mapper().map(usuario, UsuarioLoginDTO.class);
	}
	
}
