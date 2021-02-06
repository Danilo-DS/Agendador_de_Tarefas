package br.com.desafio_smn.application.modelDTO;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter 
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UsuarioLoginDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String tipo;
}
