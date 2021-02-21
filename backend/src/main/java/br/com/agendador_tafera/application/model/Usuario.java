package br.com.agendador_tafera.application.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "usuario")
@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NOME", length = 55, nullable = false)
	private String nome;
	
	@Column(name = "EMAIL", length = 60, unique = true, nullable = false)
	private String email;
	
	@Column(name = "SENHA", length = 65, nullable = false)
	private String senha;
	
	@Column(name = "DATA_NASCIMENTO", length = 15,nullable = false)
	private String dtNascimento;
	
	@Column(name = "CELULAR", length = 10, nullable = false)
	private String celular;
	
	@Column(name = "TELEFONE", length = 10)
	private String telefone;
	
	@Column(name = "ENDERECO", length = 255, nullable = false)
	private String endereco;
	
	@Column(name = "TIPO_USUARIO", length = 1, nullable = false)
	private String tipoUsuario;
}
