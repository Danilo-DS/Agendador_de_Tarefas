package br.com.agendador_tafera.application.modelDTO;

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
