package br.com.agendador_tafera.application.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.agendador_tafera.application.enums.PerfilUS;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "TB_PERFIL")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PerfilUsuario implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NM_PERFIL")
	@Enumerated(EnumType.STRING)
	private PerfilUS perfil;
	
	
	
}
