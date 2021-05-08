package br.com.agendador_tafera.application.service.email;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.agendador_tafera.application.model.Usuario;
import br.com.agendador_tafera.application.utils.Utilitarios;

@Service
public class SendEmailService{
	
	@Autowired
	private JavaMailSender enviarEmail;
	
	private List<String> emailNaoEnviado;
	
	private SimpleMailMessage msg;
	/*Envia email para usuario responsavel pela tarefa
	 * @param Titulo da tarefa
	 * @param Corpo da mensagem exp: Descrição, prioridade da tarefa, etc.
	 * @param Email do usuario responsável
	 * @return OK para enviado e FAIL se ocorrer algum problema*/
	public List<String> enviarEmail(String Titulo, String corpoMensage, List<Usuario> emailsDesnitario){
		
		emailNaoEnviado = new ArrayList<>();; 

		emailsDesnitario.forEach(dest -> {
			try {
				msg = new SimpleMailMessage();
				
				msg.setSubject(Titulo);
				msg.setText(corpoMensage);
				msg.setTo(dest.getEmail());
				msg.setFrom(Utilitarios.EMAIL_API);
				enviarEmail.send(msg);
			}
			catch (Exception e) {
				emailNaoEnviado.add(dest.getEmail());
			}
			
		});
		
		return emailNaoEnviado; 
	}
	
}
