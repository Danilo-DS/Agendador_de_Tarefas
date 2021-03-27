package br.com.agendador_tafera.application.config;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.agendador_tafera.application.model.Usuario;
import br.com.agendador_tafera.application.repository.UsuarioRepository;

@Component
public class CarregaDadosBD implements ApplicationRunner {
	
	@Autowired
	private UsuarioRepository userRespository;
    
    public CarregaDadosBD() {    }
	 
	@Override
	public void run(ApplicationArguments args) throws Exception {
		if(userRespository.findAll().isEmpty()){
			userRespository.save(new Usuario(null,"Schedule System","systemschedule@gmail.com.br",new BCryptPasswordEncoder().encode("123"), new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
								"1634099514", "1634099514", "Rua dos Pracinhas, 1720 - Núcleo Agrícola Alpha, Franca - SP", "G"));
		}
	}

}