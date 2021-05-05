package br.com.agendador_tafera.application.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioTarefaDTO {
	
	private Long id;
	private String email;
}
