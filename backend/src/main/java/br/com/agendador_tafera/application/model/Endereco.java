package br.com.agendador_tafera.application.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "TB_ENDERECO")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "LOGRADOURO", length = 60, nullable = false)
	private String logradouro;
	
	@Column(name = "BAIRRO", length = 25, nullable = false)
	private String bairro;
	
	@Column(name = "NUMERO", length = 5, nullable = false)
	private String numero;
	
	@Column(name = "COMPLEMENTO", length = 254, nullable = false)
	private String complemento;
	
	@Column(name = "CEP", length = 9, nullable = false)
	private String cep;
	
	@Column(name = "CIDADE_ESTADO", length = 30, nullable = false)
	private String cidadeEstado;
}
