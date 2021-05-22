package br.com.agendador_tafera.application.dto.tarefa;

import java.util.List;

import br.com.agendador_tafera.application.dto.empresa.EmpresaDTO;
import br.com.agendador_tafera.application.dto.usuario.UsuarioTarefaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReuniaoRequestDTO {
	
	private String titulo;
	
	private String descricao;
	
	private String dtReuniao;
	
	private UsuarioTarefaDTO usuario;
	
	private EmpresaDTO empresa;
		
	private List<String> convidadosEmail;
	
	private List<String> convidadosTelefone;
}
