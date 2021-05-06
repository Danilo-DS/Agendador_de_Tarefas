package br.com.agendador_tafera.application.dto.tarefa;

import java.util.List;

import br.com.agendador_tafera.application.dto.empresa.EmpresaResponseDTO;
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
	private EmpresaResponseDTO empresa; 
	private String prioridade;
	private String statusTarefa;
	private List<String> convidadosEmail;
	private List<String> convidadosTelefone;
}
