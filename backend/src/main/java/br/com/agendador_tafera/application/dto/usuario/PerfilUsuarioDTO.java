package br.com.agendador_tafera.application.dto.usuario;

import br.com.agendador_tafera.application.enums.PerfilUS;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class PerfilUsuarioDTO {
	
	private PerfilUS perfil;
	
	public String getPerfil() {
		return perfil.getPerfil();
	}
}
