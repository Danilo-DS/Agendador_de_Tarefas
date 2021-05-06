package br.com.agendador_tafera.application.dto.empresa;

import br.com.agendador_tafera.application.dto.endereco.EnderecoDTO;
import br.com.agendador_tafera.application.dto.usuario.UsuarioResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmpresaResponseDTO {
	
	private Long id;
	private String razaoSocial;
	private String cnpj;
	private EnderecoDTO endereco;
	private UsuarioResponseDTO usuario;
}
