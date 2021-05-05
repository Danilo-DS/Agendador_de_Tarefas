package br.com.agendador_tafera.application.modelDTO;

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
	
	private String dtNascimento;
	
	private String celular;
	
	private String telefone;
	
	private EnderecoDTO endereco;
	
	private EmpresaResponseDTO empresa;
	
	private UsuarioResponseDTO usuario;
}
