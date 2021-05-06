package br.com.agendador_tafera.application.dto.endereco;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnderecoDTO {
	
	private String logradouro;
	private String bairro;
	private String numero;
	private String complemento;
	private String cep;
	private String cidadeEstado;
	
}
