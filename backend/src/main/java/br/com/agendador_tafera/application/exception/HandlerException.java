package br.com.agendador_tafera.application.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.agendador_tafera.application.exception.empresa.EmpresaException;
import br.com.agendador_tafera.application.exception.funcionario.FuncionarioException;
import br.com.agendador_tafera.application.exception.perfilusuario.PerfilException;
import br.com.agendador_tafera.application.exception.tarefa.AgendarTarefaException;
import br.com.agendador_tafera.application.exception.usuario.UsuarioException;

@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(UsuarioException.class)
	public ResponseEntity<Object> exceptionUsuario(UsuarioException userExp, WebRequest request ){

		return super.handleExceptionInternal(userExp, userExp.getMessage(), new HttpHeaders(), userExp.getStatus(), request);
	}
	
	@ExceptionHandler(PerfilException.class)
	public ResponseEntity<Object> exceptionPerfil(PerfilException perfExp, WebRequest request ){

		return super.handleExceptionInternal(perfExp, perfExp.getMessage(), new HttpHeaders(), perfExp.getStatus(), request);
	}
	
	@ExceptionHandler(AgendarTarefaException.class)
	public ResponseEntity<Object> exceptionTarefa(AgendarTarefaException atExp, WebRequest request ){
		
		return super.handleExceptionInternal(atExp, atExp.getMessage(), new HttpHeaders(), atExp.getStatus(), request);
	}
	
	@ExceptionHandler(EmpresaException.class)
	public ResponseEntity<Object> exceptionEmpresa(EmpresaException empExp, WebRequest request ){
		
		return super.handleExceptionInternal(empExp, empExp.getMessage(), new HttpHeaders(), empExp.getStatus(), request);
	}
	
	@ExceptionHandler(FuncionarioException.class)
	public ResponseEntity<Object> exceptionFuncionario(FuncionarioException funcExp, WebRequest request ){
		
		return super.handleExceptionInternal(funcExp, funcExp.getMessage(), new HttpHeaders(), funcExp.getStatus(), request);
	}
}
