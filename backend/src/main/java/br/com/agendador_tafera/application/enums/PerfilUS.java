package br.com.agendador_tafera.application.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum PerfilUS {
	ADMINISTRADOR (1, "ADM"),
	EMPRESA (2, "EMP"),
	FUNCIONARIO (3, "FUNC"),
	GESTOR (4, "GST"),
	USUARIO_COMUM (5, "USC"),
	MASTER (6, "MST");
	
	private Integer idPerfil;
	public String perfil;
}
