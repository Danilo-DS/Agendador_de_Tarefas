package br.com.agendador_tafera.application.exception.agendarTarefa;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AgendarTarefaException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	private HttpStatus status;
	
	private String emailsUsuarios;
	
	public AgendarTarefaException(String msg, List<String> email, HttpStatus sts) {
		super(msg);
		status = sts;
		formatarEmails(email);
	}
	
	public AgendarTarefaException(String msg, HttpStatus sts) {
		super(msg);
		status = sts;
	}
	
	private void formatarEmails(List<String> email) {
		
		email.forEach(e -> {
			emailsUsuarios += (emailsUsuarios + ", ");
		});
		
	}
}
