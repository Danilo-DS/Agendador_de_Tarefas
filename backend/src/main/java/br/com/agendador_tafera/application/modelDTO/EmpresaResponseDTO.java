package br.com.agendador_tafera.application.modelDTO;

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
}
