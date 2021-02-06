package br.com.agendador_tafera.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.agendador_tafera.application.ModelConvert;
import br.com.agendador_tafera.application.exception.agendarTarefa.AgendarTarefaException;
import br.com.agendador_tafera.application.model.AgendarTarefa;
import br.com.agendador_tafera.application.model.Usuario;
import br.com.agendador_tafera.application.modelDTO.TarefaDTO;
import br.com.agendador_tafera.application.repository.AgendaTarefaRepository;
import br.com.agendador_tafera.application.utils.Utilitarios;

@Service
public class AgendarTarefaService {
	
	@Autowired
	private AgendaTarefaRepository agendaRepository;
	
	@Autowired
	private UsuarioService userService;
	
	@Autowired
	private SendEmailService emailService;
	
	private Usuario user;
	
	private AgendarTarefa agendar;
	
	@Transactional(readOnly = true)
	public List<TarefaDTO> listAllTask(){
		return toDTO(agendaRepository.findAll());
	}
	
	@Transactional(readOnly = true)
	public AgendarTarefa findTaskId(Long id) {
		return agendaRepository.findById(id).orElseThrow(() -> new AgendarTarefaException(Utilitarios.ErrorBuscarTarefa, HttpStatus.NOT_FOUND));
	}
	
	/*Salva uma tarefa e utiliza o serviço de email para enviar
	 * um email ao usuario designado tarefa
	 * @param Recebe um obj AgendarTarefa
	 */
	@Transactional
	public void saveTask(AgendarTarefa at) {
		if(userService.verifyUser(at.getUsuario().getId())){
			user = userService.findUserId(at.getUsuario().getId());
			at.setDtCriacaoTarefa(at.convertData());
			at.setUsuario(user);
			at.setStatusTarefa(Utilitarios.TarefaAgendada);
			agendaRepository.save(at);
			
			String respEmail = emailService.sendMail(
						at.getTitulo(),
						"Descrição da Tarefa: " + at.getDescricao() + "\n"
						+ "Nével de Prioridade: " + at.getPrioridade() + "\n"
						+ "Situação da Tarefa: " + at.getStatusTarefa() + "\n",
						at.getUsuario().getEmail()						
					);
			
			if (respEmail.equals(Utilitarios.EmailFail)) {
				new AgendarTarefaException(Utilitarios.EmailFail, HttpStatus.FAILED_DEPENDENCY);
			}
		}
		else {
			throw new AgendarTarefaException(Utilitarios.ErrorSalvarTarefa, HttpStatus.NOT_FOUND);
		}
	}
	
	/* Atualiza Tarefa já cadastrada na base
	 * @param recebe o id da tarefa
	 * @param recebe um obj tarefa*/
	@Transactional
	public void updateTask(Long id, AgendarTarefa at) {
		
		String datasTarefas = ""; //recebe qual tipo de data vai ser passado no email.
		
		if (verifyTask(id) && at.getStatusTarefa().equals(Utilitarios.TarefaFinalizada)) { /*Condição para finalização tarefa, verifica se a tarefa existe e verifica o atributo
		 																					* statusTarefa do obj recebido na request */
			agendar = agendaRepository.findById(id).get();
			agendar.setStatusTarefa(at.getStatusTarefa());
			agendar.setDtFinalizacaoTarefa(agendar.convertData());
			
			agendaRepository.save(agendar);
			
			datasTarefas = "Data Finalização: " + agendar.getDtFinalizacaoTarefa();
			
		}
		else if(verifyTask(id) && at.getStatusTarefa().equals(Utilitarios.TarefaCancelada)) {/*Condição para cancelamento tarefa, verifica se a tarefa existe e verifica o atributo
																							  *statusTarefa do obj recebido na request é iqual a CANCELADA */
			agendar = agendaRepository.findById(id).get();
			agendar.setStatusTarefa(at.getStatusTarefa());
			agendar.setDtCancelamentoTarefa(agendar.convertData());
			
			agendaRepository.save(agendar);
			
			datasTarefas = "Data Cancelamento: " + agendar.getDtCancelamentoTarefa();
			
		}
		else if(verifyTask(id) && userService.verifyUser(at.getUsuario().getId())) {/*Condição para atualizar informações da tarefa, verifica se a tarefa existe 
		 																			 *e se o usuario resonsavel por ela também existe. */
			
			user = userService.findUserId(at.getUsuario().getId());
			at.setUsuario(user);
			agendar = at;
			
			agendaRepository.save(at);
			datasTarefas = "Data Criação: " + at.getDtCriacaoTarefa();
		}
		else {
			throw new AgendarTarefaException(Utilitarios.ErrorAtualizarTarefa, HttpStatus.NOT_FOUND); //Se nenhum da condições acima não for atendidas será lançada uma exceção
		}																							   //Informando um problema ao atualizar a tarefa*/
		
		
		/* Se uma das condições acima for aceita e não lançar exceção
		 * é chamado o serviço de email*/
		String respEmail = emailService.sendMail(
				agendar.getTitulo(),
				"Descrição da Tarefa: " + agendar.getDescricao() + "\n"
				+ "Nével de Prioridade: " + agendar.getPrioridade() + "\n"
				+ "Situação da Tarefa: " + agendar.getStatusTarefa() + "\n"
				+ datasTarefas,
				agendar.getUsuario().getEmail());
		
		if (respEmail.equals(Utilitarios.EmailFail)) { //Verifica se o email foi enviado, caso contrario lança exceção
			throw new AgendarTarefaException(Utilitarios.EmailFail, HttpStatus.FAILED_DEPENDENCY);
		}
		
	}

	@Transactional
	public void deleteTask(Long id) {

		if(verifyTask(id)) {
			agendaRepository.deleteById(id);
		}
		else {
			throw new AgendarTarefaException(Utilitarios.ErrorDeletarTarefa, HttpStatus.NOT_FOUND);
		}
		
	}
	
	/* Verifica se a tarefa existe na base
	 * @param id da tarefa
	 * @return true para OK e false para FAIL*/
	@Transactional(readOnly = true)
	private boolean verifyTask(Long id) {
		if(agendaRepository.existsById(id)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/* Converte uma lista Objs do tipo AgendarTarefa em uma list de TarefaDTO */ 
	private List<TarefaDTO> toDTO(List<AgendarTarefa> tarefa) {
		return tarefa.stream().map(t -> ModelConvert.mapper().map(t, TarefaDTO.class)).collect(Collectors.toList());
	}
	
}
