package br.com.agendador_tafera.application.dto.funcionario;

import br.com.agendador_tafera.application.dto.empresa.EmpresaResponseDTO;
import br.com.agendador_tafera.application.dto.endereco.EnderecoDTO;
import br.com.agendador_tafera.application.dto.usuario.UsuarioResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FuncionarioResponseDTO {
	
	private Long id;
	
	private String nome;
	
	private String cpf;
	
	private String pisPasep;
	
	private String dataNascimento;
	
	private String celular;
	
	private String telefone;
	
	private EnderecoDTO endereco;
	
	private EmpresaResponseDTO empresa;
	
	private UsuarioResponseDTO usuario;
}
