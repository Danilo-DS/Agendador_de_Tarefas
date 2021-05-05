package br.com.agendador_tafera.application.modelDTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TarefaResponseDTO {
	
	private Long id;
	private String titulo;
	private String descricao;
	private List<UsuarioResponseDTO> usuario;
	private EmpresaResponseDTO empresa; 
	private String prioridade;
	private String statusTarefa;
	private String convidados;
}
