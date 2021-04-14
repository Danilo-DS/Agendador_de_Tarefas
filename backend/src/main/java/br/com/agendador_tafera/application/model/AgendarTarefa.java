package br.com.agendador_tafera.application.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "agendartarefa")
@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_Usuario"))
	private Usuario usuario;
	
	@Column(name = "PRIORIDADE", length = 5, nullable = false)
	private String prioridade;
	
	@Column(name = "DT_CRIACAO_TAREFA", length = 10, nullable = false)
	private String dtCriacaoTarefa;
	
	@Column(name = "DT_FINALIZACAO_TAREFA", length = 10)
	private String dtFinalizacaoTarefa;
	
	@Column(name = "DT_CANCELAMENTO_TAREFA", length = 10)
	private String dtCancelamentoTarefa;
	
	@Column(name = "STATUS_TAREFA", length = 11, nullable = false)
	private String statusTarefa;
	
	public String convertData() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date()); 
	}
}
