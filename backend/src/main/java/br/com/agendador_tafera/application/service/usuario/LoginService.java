package br.com.agendador_tafera.application.service.usuario;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.agendador_tafera.application.config.ModelConvert;
import br.com.agendador_tafera.application.config.security.payload.UserLogin;
import br.com.agendador_tafera.application.enums.PerfilUS;
import br.com.agendador_tafera.application.model.Empresa;
import br.com.agendador_tafera.application.model.Funcionario;
import br.com.agendador_tafera.application.model.Usuario;
import br.com.agendador_tafera.application.repository.EmpresaRepository;
import br.com.agendador_tafera.application.repository.FuncionarioRepository;
import br.com.agendador_tafera.application.repository.UsuarioRepository;

@Service
public class LoginService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository userRepository;
	
	@Autowired
	private EmpresaRepository empRespository;
	
	@Autowired
	private FuncionarioRepository funcRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		String em = email;
		Usuario user = userRepository.findByEmail(em).orElseThrow(() -> new UsernameNotFoundException("Error email ou senha invalido"));
		
		
		Optional<Funcionario> funcionario = Optional.ofNullable(null);
		Optional<Empresa> empresa = Optional.ofNullable(null);
		
		if(user.getTipoUsuario().equals(PerfilUS.EMPRESA.perfil)) {
			empresa = empRespository.findByUsuarioId(user.getId());
		}
		else {
			funcionario = funcRepository.findByUsuarioId(user.getId());		
		}
		
		Long idEmpresa = empresa.isPresent() ? empresa.get().getId() : funcionario.isPresent() ? funcionario.get().getEmpresa().getId() : null;
		
		return UserLogin.builder(toUserLogin(user), idEmpresa);
	}
	
	private UserLogin toUserLogin(Usuario user) {
		return ModelConvert.mapper().map(user, UserLogin.class);
	}
	
}
