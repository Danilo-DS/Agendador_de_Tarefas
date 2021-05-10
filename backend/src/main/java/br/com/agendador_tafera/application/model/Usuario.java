package br.com.agendador_tafera.application.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "TB_USUARIO")
@NoArgsConstructor
@AllArgsConstructor
@Data
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
	
	@Column(name = "CELULAR", length = 10, nullable = false)
	private String celular;
		
	@Column(name = "TIPO_USUARIO", length = 4, nullable = false)
	private String tipoUsuario;
	
	
	@ManyToMany(cascade = CascadeType.REMOVE)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "TB_USUARIO_PERFIL",
	   joinColumns =  @JoinColumn(name = "ID_USUARIO", foreignKey = @ForeignKey(name = "FK_USER_PERFIL")),
	   inverseJoinColumns =  @JoinColumn(name = "ID_PERFIL", foreignKey = @ForeignKey(name = "FK_PERFIL_USER"))
	)
	private List<PerfilUsuario> perfis; 
	
	public String encriptPassword(String senha) {
		senha = new BCryptPasswordEncoder().encode(senha);
		return senha;
	}
}
