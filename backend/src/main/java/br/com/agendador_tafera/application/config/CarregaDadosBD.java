package br.com.agendador_tafera.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.agendador_tafera.application.enums.PerfilUS;
import br.com.agendador_tafera.application.model.PerfilUsuario;
import br.com.agendador_tafera.application.model.Usuario;
import br.com.agendador_tafera.application.repository.PerfilRepository;
import br.com.agendador_tafera.application.repository.UsuarioRepository;

@Component
public class CarregaDadosBD implements ApplicationRunner {
	
	@Autowired
	private UsuarioRepository userRepository;
	
	@Autowired
	private PerfilRepository perfilRepository;
	
    public CarregaDadosBD() {    }
	 
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		if(perfilRepository.findAll().isEmpty()) {
			
			perfilRepository.save(new PerfilUsuario(null, PerfilUS.ADMINISTRADOR));
			perfilRepository.save(new PerfilUsuario(null, PerfilUS.EMPRESA));
			perfilRepository.save(new PerfilUsuario(null, PerfilUS.GESTOR));
			perfilRepository.save(new PerfilUsuario(null, PerfilUS.FUNCIONARIO));
			perfilRepository.save(new PerfilUsuario(null, PerfilUS.USUARIO_COMUM));
			perfilRepository.save(new PerfilUsuario(null, PerfilUS.MASTER));
			
		}
		
		if(userRepository.findAll().isEmpty()){

			userRepository.save(new Usuario(null,
								"Schedule System",
								"systemschedule@gmail.com.br",
								new BCryptPasswordEncoder().encode("123"),
								"1634099514",PerfilUS.MASTER.perfil, perfilRepository.findAll()));
			
		}
	}

}
