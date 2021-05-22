package br.com.agendador_tafera.application.dto.tarefa;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.agendador_tafera.application.dto.empresa.EmpresaResponseDTO;
import br.com.agendador_tafera.application.dto.usuario.UsuarioResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
@Data
public class TarefaResponseDTO {
	
	private Long id;
	
	private String titulo;
	
	private String descricao;
	
	private List<UsuarioResponseDTO> usuario;
	
	private EmpresaResponseDTO empresa;
	
	private String tipoAgendamento;
	
	private String prioridade;
	
	private String dtCriacaoTarefa;
	
	private String dtReuniao;
	
	private String statusTarefa;
	
	private String convidadosEmail;
	
	private String convidadosTelefone;
}
