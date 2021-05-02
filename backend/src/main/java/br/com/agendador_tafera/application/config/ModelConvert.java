package br.com.agendador_tafera.application.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelConvert {
	
	@Bean
	public static ModelMapper  mapper() {
		return new ModelMapper();
	}
}
