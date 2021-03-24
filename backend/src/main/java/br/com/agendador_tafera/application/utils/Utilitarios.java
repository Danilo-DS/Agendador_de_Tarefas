package br.com.agendador_tafera.application.utils;

public class Utilitarios {
	
	/* Status de Prioridade	*/
	
	public static final String PrioridadeAlta = "ALTO";
	public static final String PrioridadeMedia = "MEDIO";
	public static final String PrioridadeBaixa = "BAIXO";
	
	/* Status da Tarefa */
	
	public static final String TarefaFinalizada = "FINALIZADA";
	public static final String TarefaAgendada = "AGENDADA";
	public static final String TarefaCancelada = "CANCELADA";
	
	/* Mensagens e Exceções */
	
	/* Usuario */
	public static final String ErrorDeletarUsuario = "Erro ao deletar usuario";
	public static final String ErrorUsuarioAssociadoTarefa = "Usuario está responsavel por uma tarefa";
	public static final String ErrorAtualizarUsuario = "Não foi possível atualizar o usuario";
	public static final String ErrorBuscarUsuario = "Erro usuario não encontrado";
	public static final String UsuarioGestor = "G";
	public static final String UsuarioComum = "U";
	public static final String UsuarioON = "userAcessando";
	
	/* Tarefa */
	public static final String ErrorDeletarTarefa = "Tarefa não Encontrada, exclusão não realizada";
	public static final String ErrorAtualizarTarefa = "Erro ao Atualizar Tarefa, Tarefa ou Usuario Responsável não encontrada";
	public static final String ErrorBuscarTarefa = "Erro Tarefa não Encontrada";
	public static final String ErrorSalvarTarefa = "Erro ao Salvar Tarefa, Usuario Responsável não encontrada";
	
	/* Email da API */
	public static final String EmailAPI = "pastel.systemcompany@gmail.com";
	public static final String EmailOk = "OK";
	public static final String EmailFail = "FAIL";
	public static final String EmailEnviado = "Email enviado com sucesso";
	public static final String EmailFalhou = "Tarefa Agendada, mas ocorreu um erro ao enviar o e-mail";
	
	/* JWT */
	
	public static final String HeaderAuth = "Authorization";
	public static final String ValueHeaderAuth = "Bearer ";
}
