package br.com.agendador_tafera.application.service.usuario;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.agendador_tafera.application.config.ModelConvert;
import br.com.agendador_tafera.application.config.security.payload.UserLogin;
import br.com.agendador_tafera.application.model.Empresa;
import br.com.agendador_tafera.application.model.Usuario;
import br.com.agendador_tafera.application.repository.EmpresaRepository;
import br.com.agendador_tafera.application.repository.UsuarioRepository;

@Service
public class LoginService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository userRepository;
	
	@Autowired
	private EmpresaRepository empRespository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		String em = email;
		Usuario user = userRepository.findByEmail(em).orElseThrow(() -> new UsernameNotFoundException("Error email ou senha invalido"));
		
		Optional<Empresa> empresa = empRespository.findByUsuarioId(user.getId());
		Long idEmpresa = empresa.isPresent() ? empresa.get().getId() : null;;
		
		return UserLogin.builder(toUserLogin(user), idEmpresa);
	}
	
	private UserLogin toUserLogin(Usuario user) {
		return ModelConvert.mapper().map(user, UserLogin.class);
	}
	
}
