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

@Entity(name = "TB_FUNCIONARIO")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Funcionario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "NM_USUARIO", length = 60,nullable = false)
	private String nome;
	
	@Column(name = "NU_CPF", length = 11,nullable = false, unique = true)
	private String cpf;
	
	@Column(name = "NU_PIS_PASEP", length = 11)
	private String pisPasep;
	
	@Column(name = "DATA_CADASTRO", length = 11)
	private String dataCadastro;
	
	@Column(name = "DATA_NASCIMENTO", length = 15,nullable = false)
	private String dtNascimento;
	
	@Column(name = "CELULAR", length = 10, nullable = false)
	private String celular;
	
	@Column(name = "TELEFONE", length = 10)
	private String telefone;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "ENDERECO_ID", foreignKey = @ForeignKey(name = "FK_FUNC_ENDERECO"), nullable =  false)
	private Endereco endereco;
	
	@OneToOne
	@JoinColumn(name = "EMPRESA_ID", foreignKey = @ForeignKey(name = "FK_FUNC_EMPRESA"))
	private Empresa empresa;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "ID_USUARIO", foreignKey = @ForeignKey(name = "FK_FUNC_USUARIO"))
	private Usuario usuario;
}
