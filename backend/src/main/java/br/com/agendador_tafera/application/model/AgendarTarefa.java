package br.com.agendador_tafera.application.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import br.com.agendador_tafera.application.dto.tarefa.ReuniaoRequestDTO;
import br.com.agendador_tafera.application.dto.tarefa.TarefaRequestDTO;
import br.com.agendador_tafera.application.enums.StatusTarefa;
import br.com.agendador_tafera.application.enums.TipoAgendamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "TB_TAREFAS")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AgendarTarefa implements Serializable{
	
	private static final long serialVersionUID = 1l;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "TITULO_TAREFA", length = 65, nullable = false)
	private String titulo;
	
	@Column(name = "DESCRICAO", length = 255, nullable = false)
	private String descricao;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "TB_TAREFAS_USUARIOS",
		joinColumns = @JoinColumn(name = "ID_TAREFA", foreignKey = @ForeignKey(name = "FK_TAREFA")),
		inverseJoinColumns = @JoinColumn(name = "ID_USURIO", foreignKey = @ForeignKey(name = "FK_USUARIO_TAREFA")))
	private List<Usuario> usuario;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "EMPRESA_ID", foreignKey = @ForeignKey(name = "FK_TAR_EMPRESA"))
	private Empresa empresa;
	
	@Column(name = "EMAIL_CONVIDADOS", columnDefinition = "VARCHAR(5000)")
	private String convidadosEmail;
	
	@Column(name = "TELEFONES_CONVIDADOS", columnDefinition = "VARCHAR(5000)")
	private String convidadosTelefone;
	
	@Column(name = "PRIORIDADE", length = 5, nullable = true)
	private String prioridade;
	
	@Column(name = "DT_CRIACAO_TAREFA", length = 10, nullable = false)
	private String dtCriacaoTarefa;
	
	@Column(name = "DT_FINALIZACAO_TAREFA", length = 10)
	private String dtFinalizacaoTarefa;
	
	@Column(name = "DT_CANCELAMENTO_TAREFA", length = 10)
	private String dtCancelamentoTarefa;
	
	@Column(name = "DT_REUNIAO", length = 10)
	private String dtReuniao;
	
	@Column(name = "TIPO_AGENDAMENTO", length = 1)
	private String tipoAgendamento;
	
	@Column(name = "STATUS_TAREFA", length = 11, nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusTarefa statusTarefa;
	
	public static String convertData() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
	}
	
	public static AgendarTarefa builder(Empresa empresa, List<Usuario> usuario, TarefaRequestDTO tarefaRequest, TipoAgendamento agendamento) {
		return new AgendarTarefa(null, tarefaRequest.getTitulo(), tarefaRequest.getDescricao(), usuario, empresa, null, 
								null, tarefaRequest.getPrioridade(), convertData(), null, null, null, agendamento.tipo, StatusTarefa.AGENDADA);
	}
	
	public static AgendarTarefa builder(Empresa empresa,  List<Usuario> usuario, ReuniaoRequestDTO reuniaoRequest, TipoAgendamento agendamento) {
		return new AgendarTarefa(null, reuniaoRequest.getTitulo(), reuniaoRequest.getDescricao(), usuario , empresa, reuniaoRequest.getConvidadosEmail().toString().replace("[", "").replace("]", ""), 
								reuniaoRequest.getConvidadosTelefone().toString().replace("[", "").replace("]", ""), null, convertData(), null, null, reuniaoRequest.getDtReuniao(), agendamento.tipo, StatusTarefa.AGENDADA);
	}
}
