package br.com.agendador_tafera.application.service.email;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.agendador_tafera.application.enums.TipoAgendamento;
import br.com.agendador_tafera.application.model.Usuario;
import br.com.agendador_tafera.application.utils.Utilitarios;

@Service
@SuppressWarnings("unchecked")
public class SendEmailService{
	
	@Autowired
	private JavaMailSender enviarEmail;
	
	private List<String> emailNaoEnviado;
	
	private SimpleMailMessage msg;

	
	public List<String> enviarEmail(String Titulo, String corpoMensage, List<?> emailsDesnitario, TipoAgendamento tipo){
		
		emailNaoEnviado = new ArrayList<>(); 
		
		if(tipo.compareTo(TipoAgendamento.ATIVIDADE) == 0) {
			enviarEmailsUsuario(Titulo, corpoMensage, (List<Usuario>) emailsDesnitario);			
		}
		else {
			enviarEmailsConvidados(Titulo, corpoMensage, (List<String>) emailsDesnitario);
		}
		
		
		return emailNaoEnviado; 
	}
	
	private void enviarEmailsUsuario(String Titulo, String corpoMensage, List<Usuario> emails){
		emails.forEach(dest -> {
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
	}
	
	private void enviarEmailsConvidados(String Titulo, String corpoMensage, List<String> emails){
		emails.forEach(dest -> {
			try {
				msg = new SimpleMailMessage();
				
				msg.setSubject(Titulo);
				msg.setText(corpoMensage);
				msg.setTo(dest);
				msg.setFrom(Utilitarios.EMAIL_API);
				enviarEmail.send(msg);
			}
			catch (Exception e) {
				emailNaoEnviado.add(dest);
			}
			
		});
	}
}
