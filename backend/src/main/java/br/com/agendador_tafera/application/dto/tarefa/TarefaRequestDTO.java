package br.com.agendador_tafera.application.dto.tarefa;

import java.util.List;

import br.com.agendador_tafera.application.dto.empresa.EmpresaTarefaDTO;
import br.com.agendador_tafera.application.dto.usuario.UsuarioTarefaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TarefaRequestDTO {
	
	private String titulo;
	private String descricao;
	private List<UsuarioTarefaDTO> usuario;
	private EmpresaTarefaDTO empresa; 
	private String prioridade;
	private List<String> convidadosEmail;
	private List<String> convidadosTelefone;
}
