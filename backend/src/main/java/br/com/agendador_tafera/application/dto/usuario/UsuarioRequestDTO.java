package br.com.agendador_tafera.application.dto.usuario;

import java.util.List;

import br.com.agendador_tafera.application.model.PerfilUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioRequestDTO {
	
	private String nome;
	
	private String email;
	
	private String senha;
	
	private String celular;
	
	private String tipoUsuario;
	
	private List<PerfilUsuario> perfis;
}
