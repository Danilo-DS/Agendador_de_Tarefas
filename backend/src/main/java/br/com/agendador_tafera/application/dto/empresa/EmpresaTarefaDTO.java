package br.com.agendador_tafera.application.dto.empresa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmpresaTarefaDTO {
	
	private Long id;
	private String cnpj;
}
