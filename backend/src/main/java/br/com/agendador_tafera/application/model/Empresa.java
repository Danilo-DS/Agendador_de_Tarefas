package br.com.agendador_tafera.application.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "TB_EMPRESAS")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Empresa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NM_FANTASIA")
	private String nomeFantasia;
	
	@Column(name = "NM_RAZAO_SOCIAL")
	private String razaoSocial;
	
	@Column(name = "NU_CNPJ", unique = true)
	private String cnpj;
	
	@Column(name = "INSC_ESTADUAL")
	private String inscricaoEstadual;
	
	@OneToOne
	@JoinColumn(name = "ENDERECO_ID", foreignKey = @ForeignKey(name = "FK_EMP_ENDERECO"))
	private Endereco endereco;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USUARIO_ID", foreignKey = @ForeignKey(name = "FK_EMP_USUARIO"))
	private Usuario usuario;
}
