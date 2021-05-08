package br.com.agendador_tafera.application.utils;

public class Utilitarios {
	
	/* Status de Prioridade	*/
	
	public static final String PRIORIDADE_ALTA = "ALTO";
	public static final String PRIORIDADE_MEDIA = "MEDIO";
	public static final String PRIORIDADE_BAIXA = "BAIXO";
	
	/* Status da Tarefa */
	
	public static final String TAREFA_FINALIZADA = "FINALIZADA";
	public static final String TAREFA_AGENDADA = "AGENDADA";
	public static final String TAREFA_CANCELADA = "CANCELADA";
	
	/* Mensagens e Exceções */
	
	/* Usuario */
	public static final String ERROR_DELETAR_USUARIO = "Erro ao deletar usuario";
	public static final String ERROR_USUARIO_ASSOCIADO_TAREFA = "Usuario está responsavel por uma tarefa";
	public static final String ERROR_ATUALIZAR_USUARIO = "Não foi possível atualizar o usuario";
	public static final String ERROR_BUSCAR_USUARIO = "Erro usuario não encontrado";
//	public static final String UsuarioGestor = "G";
//	public static final String UsuarioComum = "U";
//	public static final String UsuarioON = "userAcessando";
	
	/* Tarefa */
	public static final String ERROR_DELETAR_TAREFA = "Tarefa não Encontrada, exclusão não realizada";
	public static final String ERROR_ATUALIZAR_TAREFA = "Erro ao Atualizar Atividade, Atividade ou Responsável não encontrado";
	public static final String ERROR_BUSCAR_TAREFA = "Erro Tarefa não Encontrada";
	public static final String ERROR_SALVAR_TAREFA = "Erro ao Agendar Atividade, Responsável não encontrada";
	
	/* EMAIL da API */
	public static final String EMAIL_API = "pastel.systemcompany@gmail.com";
	public static final String EMAIL_OK = "OK";
	public static final String EMAIL_FAIL = "FAIL";
	public static final String EMAIL_ENVIADO = "E-mail enviado com sucesso";
	public static final String EMAIL_FALHOU = "Atividade Agendada !";
	public static final String EMAIL_FALHOU_ATUALIZACAO = "Ativdade Atualizada !, mas ocorreu um erro ao enviar o e-mail";
	
	/* JWT */
	
	public static final String HEADER_AUTH = "Authorization";
	public static final String VALUE_HEADER_AUTH = "Bearer ";
	
	/* Tipo Operação Email TAREFA*/
	public static final String CRIACAO = "C";
	public static final String ATUALIZACAO = "U";
	public static final String FINALIZACAO = "F";
	public static final String CANCELAMENTO = "CC";
	
}
