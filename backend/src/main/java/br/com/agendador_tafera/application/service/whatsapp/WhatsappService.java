package br.com.agendador_tafera.application.service.whatsapp;

import java.util.List;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.agendador_tafera.application.utils.Utilitarios;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WhatsappService {
	
	
	private RestTemplate restTemplate;
	
	public void enviarMsg(List<String> numeros, String corpoMsg) {
		
		restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String response = restTemplate.postForObject(Utilitarios.URL_BOT_ENVIAR, new HttpEntity<String>(new JSONObject().put("numero", numeros).put("msg", corpoMsg).toString(), headers) , String.class);
		log.info("Bot Whats: " + response);		
		
	}
}
