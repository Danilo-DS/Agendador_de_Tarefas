package br.com.agendador_tafera.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
// org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.agendador_tafera.application.ModelConvert;
import br.com.agendador_tafera.application.model.Usuario;
import br.com.agendador_tafera.application.modelDTO.UsuarioLoginDTO;
import br.com.agendador_tafera.application.repository.UsuarioRepository;

@Service
public class LoginService implements UserDetailsService {
	
	@Autowired
	UsuarioRepository userRepository;
	
	Usuario usuario;
	
	UsuarioLoginDTO user;
	
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		user =  toDTOLogin(userRepository.findByEmail(username));
		
		if(user == null) {
			System.out.println("Usuario não está autenticado");
			throw new UsernameNotFoundException("Error email ou senha invalido");
		}
		return user;
	}
	
	/* Converte um Obj do tipo Usuario em um obj de UsuarioLoginDTO */ 
	private UsuarioLoginDTO toDTOLogin(Usuario user) {
		return ModelConvert.mapper().map(user, UsuarioLoginDTO.class);
	}
	
}
