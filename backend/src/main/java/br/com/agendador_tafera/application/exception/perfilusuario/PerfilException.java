package br.com.agendador_tafera.application.exception.perfilusuario;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PerfilException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private HttpStatus status;
	
	public PerfilException (String msg, HttpStatus sts) {
		super(msg);
		status = sts;
	}
}
