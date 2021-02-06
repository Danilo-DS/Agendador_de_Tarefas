package br.com.agendador_tafera.application.exception.usuario;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsuarioException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	private HttpStatus status;
	
	public UsuarioException(String msg, HttpStatus sts) {
		super(msg);
		status = sts;
	}
	
}
