package br.com.agendador_tafera.application.modelDTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioResponseDTO {
	
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private String celular;
	private String tipoUsuario;
	private List<String> permissao;
	
	
}
