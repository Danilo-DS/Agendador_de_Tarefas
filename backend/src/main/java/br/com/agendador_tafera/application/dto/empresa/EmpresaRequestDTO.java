package br.com.agendador_tafera.application.dto.empresa;

import br.com.agendador_tafera.application.dto.endereco.EnderecoDTO;
import br.com.agendador_tafera.application.dto.usuario.UsuarioRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmpresaRequestDTO {
	
	private String nomeFantasia;
	private String razaoSocial;
	private String cnpj;
	private String inscricaoEstadual;
	private EnderecoDTO endereco;
	private UsuarioRequestDTO usuarioEmpresa;
}
