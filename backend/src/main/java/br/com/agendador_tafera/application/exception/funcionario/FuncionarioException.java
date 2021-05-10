package br.com.agendador_tafera.application.exception.funcionario;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FuncionarioException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private HttpStatus status;
	
	public FuncionarioException(String msg, HttpStatus sts) {
		super(msg);
		status = sts;
	}
}
