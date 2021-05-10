package br.com.agendador_tafera.application.exception.empresa;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmpresaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private HttpStatus status;
	public EmpresaException(String msg, HttpStatus sts) {
		super(msg);
		status = sts;
	}
}
