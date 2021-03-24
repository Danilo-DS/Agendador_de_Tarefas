package br.com.agendador_tafera.application.config.security.payload.reponse;

import java.util.List;

import br.com.agendador_tafera.application.utils.Utilitarios;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtResponse {
	
	private String token;
	private String type;
	private Long id;
	private String email;
	private List<String> permissao;
	
	public JwtResponse(String token, Long id, String email, List<String> permissao) {
		this.token = token;
		this.type = Utilitarios.ValueHeaderAuth;
		this.id = id;
		this.email = email;
		this.permissao = permissao;
	}
}
