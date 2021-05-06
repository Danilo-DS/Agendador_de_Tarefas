package br.com.agendador_tafera.application.dto.funcionario;

import br.com.agendador_tafera.application.dto.empresa.EmpresaRequestDTO;
import br.com.agendador_tafera.application.dto.endereco.EnderecoDTO;
import br.com.agendador_tafera.application.dto.usuario.UsuarioRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FuncionarioRequestDTO {

	private String nome;
	
	private String cpf;
	
	private String pisPasep;
	
	private String dtNascimento;
	
	private String celular;
	
	private String telefone;
	
	private EnderecoDTO endereco;
	
	private EmpresaRequestDTO empresa;
	
	private UsuarioRequestDTO usuario;
}
