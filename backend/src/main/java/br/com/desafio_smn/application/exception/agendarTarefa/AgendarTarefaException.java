package br.com.desafio_smn.application.exception.agendarTarefa;

import org.springframework.http.HttpStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AgendarTarefaException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	private HttpStatus status;
	
	public AgendarTarefaException(String msg, HttpStatus sts) {
		super(msg);
		status = sts;
	}
}
