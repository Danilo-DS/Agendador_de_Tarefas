package br.com.agendador_tafera.application.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.util.StringUtils;

import br.com.agendador_tafera.application.modelDTO.EnderecoDTO;
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
	
	public static Endereco atualizarEndereco(Endereco endereco, EnderecoDTO enderecoDTO) {
			endereco.setLogradouro(StringUtils.hasText(enderecoDTO.getLogradouro()) ? enderecoDTO.getLogradouro() : endereco.getLogradouro());
			endereco.setBairro(StringUtils.hasText(enderecoDTO.getBairro()) ? enderecoDTO.getBairro() : endereco.getBairro());
			endereco.setNumero(StringUtils.hasText(enderecoDTO.getNumero()) ? enderecoDTO.getNumero() : endereco.getNumero());
			endereco.setComplemento(StringUtils.hasText(enderecoDTO.getComplemento()) ? enderecoDTO.getComplemento() : endereco.getComplemento());
			endereco.setCep(StringUtils.hasText(enderecoDTO.getCep()) ? enderecoDTO.getCep() : endereco.getCep());
			endereco.setCidadeEstado(StringUtils.hasText(enderecoDTO.getCidadeEstado()) ? enderecoDTO.getCidadeEstado() : endereco.getCidadeEstado());
		return endereco;
	}
}
