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

import br.com.agendador_tafera.application.enums.StatusTarefa;
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
	@JoinTable(name = "TB_TAREFAS_USUARIOS",
		joinColumns = @JoinColumn(name = "USUARIO_ID", foreignKey = @ForeignKey(name = "FK_USER_TAREFA")),
		inverseJoinColumns = @JoinColumn(name = "ID_PERFIL", foreignKey = @ForeignKey(name = "FK_TAREFA")))
	private List<Usuario> usuario;
	
	@ManyToOne
	@JoinColumn(name = "EMPRESA_ID", foreignKey = @ForeignKey(name = "FK_TAR_EMPRESA"))
	private Empresa empresa;
	
	@Column(name = "EMAIL_CONVIDADOS", columnDefinition = "VARCHAR(5000)")
	private String convidados;
	
	@Column(name = "PRIORIDADE", length = 5, nullable = false)
	private String prioridade;
	
	@Column(name = "DT_CRIACAO_TAREFA", length = 10, nullable = false)
	private String dtCriacaoTarefa;
	
	@Column(name = "DT_FINALIZACAO_TAREFA", length = 10)
	private String dtFinalizacaoTarefa;
	
	@Column(name = "DT_CANCELAMENTO_TAREFA", length = 10)
	private String dtCancelamentoTarefa;
	
	@Column(name = "STATUS_TAREFA", length = 11, nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusTarefa statusTarefa;
	
	public String convertData() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date()); 
	}
}
