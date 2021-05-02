package br.com.agendador_tafera.application.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.agendador_tafera.application.config.ModelConvert;
import br.com.agendador_tafera.application.config.security.payload.UserLogin;
import br.com.agendador_tafera.application.model.Usuario;
import br.com.agendador_tafera.application.repository.UsuarioRepository;

@Service
public class LoginService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		String em = email;
		Usuario user = userRepository.findByEmail(em).orElseThrow(() -> new UsernameNotFoundException("Error email ou senha invalido"));
		
		return UserLogin.builder(toUserLogin(user));
	}
	
	/* Converte um Obj do tipo Usuario em um obj de UsuarioLoginDTO */ 
	private UserLogin toUserLogin(Usuario user) {
		return ModelConvert.mapper().map(user, UserLogin.class);
	}
	
}
