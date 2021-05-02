package br.com.agendador_tafera.application.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.agendador_tafera.application.exception.agendarTarefa.AgendarTarefaException;
import br.com.agendador_tafera.application.exception.usuario.UsuarioException;

@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler{
	
	/* Captura e lança exceções personalizadas de Usuario*/
	@ExceptionHandler(UsuarioException.class)
	public ResponseEntity<Object> ExceptionUsuario(UsuarioException userExp, WebRequest request ){

		return super.handleExceptionInternal(userExp, userExp.getMessage(), new HttpHeaders(), userExp.getStatus(), request);
	}
	
	/* Captura e lança exceções personalizadas de Tarefas*/ 
	@ExceptionHandler(AgendarTarefaException.class)
	public ResponseEntity<Object> ExceptionTarefa(AgendarTarefaException atExp, WebRequest request ){
		
		return super.handleExceptionInternal(atExp, atExp.getMessage(), new HttpHeaders(), atExp.getStatus(), request);
	}
}
