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
	public static final String ERROR_DELETAR_USUARIO = "Error ao deletar usuario.";
	public static final String ERROR_USUARIO_ASSOCIADO_TAREFA = "Usuario está responsavel por uma tarefa.";
	public static final String ERROR_ATUALIZAR_USUARIO = "Não foi possível atualizar o usuario.";
	public static final String ERROR_BUSCAR_USUARIO = "Error usuario não encontrado.";
	
	/* Perfil Usuario */
	public static final String ERROR_BUSCAR_PERFIL_USUARIO = "Error perfil não encontrado.";
		
	/* Tarefa */
	public static final String ERROR_DELETAR_TAREFA = "Tarefa não Encontrada, exclusão não realizada.";
	public static final String ERROR_ATUALIZAR_TAREFA = "Error ao Atualizar Atividade, Atividade ou Responsável não encontrado.";
	public static final String ERROR_BUSCAR_TAREFA = "Error Tarefa não Encontrada.";
	public static final String ERROR_SALVAR_TAREFA = "Error ao Agendar Atividade, Responsável não encontrada.";
	
	/* Funcionario */
	public static final String ERROR_BUSCAR_FUNCIONARIO = "Error Funcionario Não Encontrado.";
	public static final String ERROR_ATUALIZAR_FUNCIONARIO = "Não foi possível atualizar o funcionario, o mesmo não foi encontrado!";
	public static final String ERROR_SALVAR_FUNCIONARIO = "O CPF Já está sendo utilizado.";
	public static final String ERROR_DELETAR_FUNCIONARIO = "Não foi possível deletar o funcionario, o mesmo não foi foi encontrado!";
	
	/* Empresa */
	public static final String ERROR_BUSCAR_EMPRESA = "Error Empresa Não Encontrada.";
	public static final String ERROR_ATUALIZAR_EMPRESA = "Não foi possível atualizar a Empresa, o mesmo não foi encontrado!";
	public static final String ERROR_SALVAR_EMPRESA = "O CNPJ Já está sendo utilizado.";
	public static final String ERROR_DELETAR_EMPRESA = "Não foi possível deletar empresa, o mesmo não foi foi encontrado!";
	
	/* EMAIL da API */
	public static final String EMAIL_API = "pastel.systemcompany@gmail.com";
	public static final String EMAIL_OK = "OK";
	public static final String EMAIL_FAIL = "FAIL";
	public static final String EMAIL_ENVIADO = "E-mail enviado com sucesso";
	public static final String EMAIL_FALHOU = "Atividade Agendada !";
	public static final String EMAIL_FALHOU_ATUALIZACAO = "Ativdade Atualizada !, mas ocorreu um erro ao enviar o e-mail.";
	
	/* Erro Interno */
	public static final String ERROR_INTERNO = "Desculpe, Ocorreu um erro. Tente novamente mais tarde!";
	
	/* ----------------------------------------------------------------------------------------------------------------------- */
	
	/* JWT */
	
	public static final String HEADER_AUTH = "Authorization";
	public static final String VALUE_HEADER_AUTH = "Bearer ";
	
	/* Tipo Operação Email TAREFA*/
	public static final String CRIACAO = "C";
	public static final String ATUALIZACAO = "U";
	public static final String FINALIZACAO = "F";
	public static final String CANCELAMENTO = "CC";
	
}
