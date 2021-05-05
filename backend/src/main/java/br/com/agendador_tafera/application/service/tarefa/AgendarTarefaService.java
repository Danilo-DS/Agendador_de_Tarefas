package br.com.agendador_tafera.application.service.tarefa;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.agendador_tafera.application.config.ModelConvert;
import br.com.agendador_tafera.application.enums.StatusTarefa;
import br.com.agendador_tafera.application.exception.agendarTarefa.AgendarTarefaException;
import br.com.agendador_tafera.application.model.AgendarTarefa;
import br.com.agendador_tafera.application.model.Usuario;
import br.com.agendador_tafera.application.modelDTO.TarefaResponseDTO;
import br.com.agendador_tafera.application.repository.AgendaTarefaRepository;
import br.com.agendador_tafera.application.service.email.SendEmailService;
import br.com.agendador_tafera.application.service.usuario.UsuarioService;
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
	public List<TarefaResponseDTO> listAllTask(){
		return toDTO(agendaRepository.findAll());
	}
	
	@Transactional(readOnly = true)
	public AgendarTarefa findTaskId(Long id) {
		return agendaRepository.findById(id).orElseThrow(() -> new AgendarTarefaException(Utilitarios.ERROR_BUSCAR_TAREFA, HttpStatus.NOT_FOUND));
	}
	
	/* Lista a tarefa de cada usuario */
	public List<TarefaResponseDTO> findTaskToUsuario(Long id) {
		agendaRepository.findByUsuario(userService.findUserId(id));
		return toDTO(agendaRepository.findByUsuario(userService.findUserId(id)));
	}
	
	@Transactional
	public void saveTask(AgendarTarefa at) {
		if(userService.isExisteUsuarioPorId(at.getUsuario().get(0).getId())){
			user = userService.findUserId(at.getUsuario().get(0).getId());
			at.setDtCriacaoTarefa(at.convertData());
			at.setUsuario(Arrays.asList(user));
			at.setStatusTarefa(StatusTarefa.AGENDADA);
			agendaRepository.save(at);
			
			String respEmail = emailService.sendMail(
						at.getTitulo(),
						"Descrição da Tarefa: " + at.getDescricao() + "\n"
						+ "Nével de Prioridade: " + at.getPrioridade() + "\n"
						+ "Situação da Tarefa: " + at.getStatusTarefa() + "\n",
						at.getUsuario().get(0).getEmail()						
					);
			
			if (respEmail.equals(Utilitarios.EMAIL_FAIL)) {
				new AgendarTarefaException(Utilitarios.EMAIL_FALHOU, HttpStatus.FAILED_DEPENDENCY);
			}
		}
		else {
			throw new AgendarTarefaException(Utilitarios.ERROR_SALVAR_TAREFA, HttpStatus.NOT_FOUND);
		}
	}
	
	@Transactional
	public void updateTask(Long id, AgendarTarefa at) {
		
		String datasTarefas = ""; //recebe qual tipo de data vai ser passado no email.
		
		if (verifyTask(id) && at.getStatusTarefa().compareTo(StatusTarefa.AGENDADA) == 1 ) { 
		 																					
			agendar = agendaRepository.findById(id).get();
			agendar.setStatusTarefa(at.getStatusTarefa());
			agendar.setDtFinalizacaoTarefa(agendar.convertData());
			
			agendaRepository.save(agendar);
			
			datasTarefas = "Data Finalização: " + agendar.getDtFinalizacaoTarefa();
			
		}
		else if(verifyTask(id) && at.getStatusTarefa().compareTo(StatusTarefa.CANCELADA) == 1) {
																							  
			agendar = agendaRepository.findById(id).get();
			agendar.setStatusTarefa(at.getStatusTarefa());
			agendar.setDtCancelamentoTarefa(agendar.convertData());
			
			agendaRepository.save(agendar);
			
			datasTarefas = "Data Cancelamento: " + agendar.getDtCancelamentoTarefa();
			
		}
		else if(verifyTask(id) && userService.isExisteUsuarioPorId(at.getUsuario().get(0).getId())) { 
		 																			
			
			user = userService.findUserId(at.getUsuario().get(0).getId());
			at.setUsuario(Arrays.asList(user));
			agendar = at;
			
			agendaRepository.save(at);
			datasTarefas = "Data Criação: " + at.getDtCriacaoTarefa();
		}
		else {
			throw new AgendarTarefaException(Utilitarios.ERROR_ATUALIZAR_TAREFA, HttpStatus.NOT_FOUND); 
		}																							  
		
		
		String respEmail = emailService.sendMail(
				agendar.getTitulo(),
				"Descrição da Tarefa: " + agendar.getDescricao() + "\n"
				+ "Nével de Prioridade: " + agendar.getPrioridade() + "\n"
				+ "Situação da Tarefa: " + agendar.getStatusTarefa() + "\n"
				+ datasTarefas,
				agendar.getUsuario().get(0).getEmail());
		
		if (respEmail.equals(Utilitarios.EMAIL_FAIL)) {
			throw new AgendarTarefaException(Utilitarios.EMAIL_FALHOU_ATUALIZACAO, HttpStatus.FAILED_DEPENDENCY);
		}
		
	}

	@Transactional
	public void deleteTask(Long id) {

		if(verifyTask(id)) {
			agendaRepository.deleteById(id);
		}
		else {
			throw new AgendarTarefaException(Utilitarios.ERROR_DELETAR_TAREFA, HttpStatus.NOT_FOUND);
		}
		
	}
	
	@Transactional(readOnly = true)
	private boolean verifyTask(Long id) {
		if(agendaRepository.existsById(id)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private List<TarefaResponseDTO> toDTO(List<AgendarTarefa> tarefa) {
		return tarefa.stream().map(t -> ModelConvert.mapper().map(t, TarefaResponseDTO.class)).collect(Collectors.toList());
	}
	
}
