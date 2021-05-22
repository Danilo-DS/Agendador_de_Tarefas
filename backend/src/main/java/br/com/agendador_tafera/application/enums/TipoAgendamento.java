package br.com.agendador_tafera.application.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum TipoAgendamento {
	
	ATIVIDADE("A"),
	REUNIAO("R");
	
	public String tipo;
}
