package br.com.agendador_tafera.application.modelDTO;

import java.util.List;

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
