package br.com.agendador_tafera.application.config.security.payload.reponse;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.agendador_tafera.application.utils.Utilitarios;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class JwtResponse {
	
	private String token;
	private String type;
	private Long id;
	private String email;
	private Long empresa;
	private List<String> permissao;
	
	public JwtResponse(String token, Long id, String email, List<String> permissao, Long idEmpresa) {
		this.token = token;
		this.type = Utilitarios.VALUE_HEADER_AUTH;
		this.id = id;
		this.email = email;
		this.empresa = idEmpresa;
		this.permissao = permissao;
	}
}
