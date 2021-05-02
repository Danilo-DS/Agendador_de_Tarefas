package br.com.agendador_tafera.application.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.agendador_tafera.application.utils.Utilitarios;

@Service
public class SendEmailService{
	
	@Autowired
	private JavaMailSender sendMail;
	
	/*Envia email para usuario responsavel pela tarefa
	 * @param Titulo da tarefa
	 * @param Corpo da mensagem exp: Descrição, prioridade da tarefa, etc.
	 * @param Email do usuario responsável
	 * @return OK para enviado e FAIL se ocorrer algum problema*/
	public String sendMail(String Titulo, String corpoMensage, String emailDesnitario){
		
		SimpleMailMessage msg = new SimpleMailMessage();
		
		  msg.setSubject(Titulo);
		  msg.setText(corpoMensage);
		  msg.setTo(emailDesnitario);
		  msg.setFrom(Utilitarios.EMAIL_API);
		 
		try {
			sendMail.send(msg);
			return Utilitarios.EMAIL_OK;
		}
		catch (Exception e) {
			return Utilitarios.EMAIL_FAIL;
		}
		
	}
	
}
