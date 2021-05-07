package br.com.agendador_tafera.application.service.tarefa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.agendador_tafera.application.config.ModelConvert;
import br.com.agendador_tafera.application.dto.tarefa.TarefaRequestDTO;
import br.com.agendador_tafera.application.dto.tarefa.TarefaResponseDTO;
import br.com.agendador_tafera.application.dto.usuario.UsuarioTarefaDTO;
import br.com.agendador_tafera.application.exception.agendarTarefa.AgendarTarefaException;
import br.com.agendador_tafera.application.model.AgendarTarefa;
import br.com.agendador_tafera.application.model.Empresa;
import br.com.agendador_tafera.application.model.Usuario;
import br.com.agendador_tafera.application.repository.AgendaTarefaRepository;
import br.com.agendador_tafera.application.service.email.SendEmailService;
import br.com.agendador_tafera.application.service.empresa.EmpresaService;
import br.com.agendador_tafera.application.service.usuario.UsuarioService;
import br.com.agendador_tafera.application.utils.Utilitarios;

@Service
public class AgendarTarefaService {
	
	@Autowired
	private AgendaTarefaRepository agendaRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private SendEmailService emailService;
	
	@Autowired
	private EmpresaService empresaService;
	
//	private FuncionarioService funcionarioService;
	
	
	@Transactional(readOnly = true)
	public List<TarefaResponseDTO> listarTarefas(){
		return toListTarefaDto(agendaRepository.findAll());
	}
	
	@Transactional(readOnly = true)
	public AgendarTarefa buscarTarefaPorId(Long id) {
		return agendaRepository.findById(id).orElseThrow(() -> new AgendarTarefaException(Utilitarios.ERROR_BUSCAR_TAREFA, HttpStatus.NOT_FOUND));
	}
	
	@Transactional(readOnly = true)
	public List<TarefaResponseDTO> buscarTarefaPorEmpresa(Long id) {
		return toListTarefaDto(agendaRepository.findByEmpresa(empresaService.findEmpresaId(id)));
	}
	
	public List<TarefaResponseDTO> buscarTarefaPorUsuario(Long id) {
		return toListTarefaDto(agendaRepository.findByUsuario(usuarioService.buscarUsuarioPorId((id))));
	}
	
	@Transactional
	public TarefaResponseDTO salvarTarefa(TarefaRequestDTO tarefaRequest) {
		if(!usuarioService.isExisteUsuarios(tarefaRequest.getUsuario())){
			throw new AgendarTarefaException(Utilitarios.ERROR_SALVAR_TAREFA, HttpStatus.NOT_FOUND);
		}
		
			var idCnpjEmpresa = StringUtils.hasText(tarefaRequest.getEmpresa().getCnpj()) ? tarefaRequest.getEmpresa().getCnpj() : tarefaRequest.getEmpresa().getId(); 
			
			Empresa empresa = (idCnpjEmpresa instanceof String) ? empresaService.findEmpresaCnpj((String) idCnpjEmpresa) : empresaService.findEmpresaId((Long) idCnpjEmpresa);
			//List<FuncionarioResponseDTO> funcioanrio = funcionarioService.listAllFuncionario(empresa.getCnpj());
			List<Usuario> usuarios = validaUsuarioTarefa(tarefaRequest.getUsuario()) ;
			
			AgendarTarefa agendarTarefa = AgendarTarefa.builder(empresa, usuarios, tarefaRequest);
			
			agendaRepository.save(agendarTarefa);
			
			
			//criar metodo chamado monta email
//			String respEmail = emailService.sendMail(
//					agendarTarefa.getTitulo(),
//					"Descrição da Tarefa: " + agendarTarefa.getDescricao() + "\n"
//					+ "Nével de Prioridade: " + agendarTarefa.getPrioridade() + "\n"
//					+ "Situação da Tarefa: " + agendarTarefa.getStatusTarefa() + "\n",
//					agendarTarefa.getUsuario()						
//				);
		
//		if (respEmail.equals(Utilitarios.EMAIL_FAIL)) {
//			new AgendarTarefaException(Utilitarios.EMAIL_FALHOU, HttpStatus.FAILED_DEPENDENCY);
//		}
		
		return tarefaToDto(agendarTarefa);
			
	}
	
	@Transactional
	public void atualizarTarefa(TarefaRequestDTO tarefaRequest) {
		
//		String datasTarefas = ""; //recebe qual tipo de data vai ser passado no email.
//		
//		if (isExisteTarefaPorId(id) && at.getStatusTarefa().compareTo(StatusTarefa.AGENDADA) == 1 ) { 
//		 																					
//			agendar = agendaRepository.findById(id).get();
//			agendar.setStatusTarefa(at.getStatusTarefa());
//			agendar.setDtFinalizacaoTarefa(agendar.convertData());
//			
//			agendaRepository.save(agendar);
//			
//			datasTarefas = "Data Finalização: " + agendar.getDtFinalizacaoTarefa();
//			
//		}
//		else if(isExisteTarefaPorId(id) && at.getStatusTarefa().compareTo(StatusTarefa.CANCELADA) == 1) {
//																							  
//			agendar = agendaRepository.findById(id).get();
//			agendar.setStatusTarefa(at.getStatusTarefa());
//			agendar.setDtCancelamentoTarefa(agendar.convertData());
//			
//			agendaRepository.save(agendar);
//			
//			datasTarefas = "Data Cancelamento: " + agendar.getDtCancelamentoTarefa();
//			
//		}
//		else if(isExisteTarefaPorId(id) && usuarioService.isExisteUsuarioPorId(at.getUsuario().get(0).getId())) { 
//		 																			
//			
//			user = usuarioService.findUserId(at.getUsuario().get(0).getId());
//			at.setUsuario(Arrays.asList(user));
//			agendar = at;
//			
//			agendaRepository.save(at);
//			datasTarefas = "Data Criação: " + at.getDtCriacaoTarefa();
//		}
//		else {
//			throw new AgendarTarefaException(Utilitarios.ERROR_ATUALIZAR_TAREFA, HttpStatus.NOT_FOUND); 
//		}																							  
//		
//		
//		String respEmail = emailService.sendMail(
//				agendar.getTitulo(),
//				"Descrição da Tarefa: " + agendar.getDescricao() + "\n"
//				+ "Nével de Prioridade: " + agendar.getPrioridade() + "\n"
//				+ "Situação da Tarefa: " + agendar.getStatusTarefa() + "\n"
//				+ datasTarefas,
//				agendar.getUsuario().get(0).getEmail());
		
//		if (respEmail.equals(Utilitarios.EMAIL_FAIL)) {
//			throw new AgendarTarefaException(Utilitarios.EMAIL_FALHOU_ATUALIZACAO, HttpStatus.FAILED_DEPENDENCY);
//		}
		
	}

	@Transactional
	public void deletatTarefa(Long id) {

		if(!isExisteTarefaPorId(id)) {
			throw new AgendarTarefaException(Utilitarios.ERROR_DELETAR_TAREFA, HttpStatus.NOT_FOUND);
		}
		
		agendaRepository.deleteById(id);
		
	}
	
	@Transactional(readOnly = true)
	private boolean isExisteTarefaPorId(Long id) {
		return agendaRepository.existsById(id);
	}
	
	private List<TarefaResponseDTO> toListTarefaDto(List<AgendarTarefa> tarefa) {
		return tarefa.stream().map(t -> ModelConvert.mapper().map(t, TarefaResponseDTO.class)).collect(Collectors.toList());
	}
	
	private TarefaResponseDTO tarefaToDto(AgendarTarefa tarefa) {
		return ModelConvert.mapper().map(tarefa, TarefaResponseDTO.class);
	}
	
	private List<Usuario> validaUsuarioTarefa(List<UsuarioTarefaDTO> usuarioTarefa) {
		List<Usuario> usuarios = new ArrayList<>();
		
		usuarioTarefa.forEach(ut -> {
			if(StringUtils.hasText(ut.getEmail())) {
				usuarios.add(usuarioService.buscarUsuarioPorEmail(ut.getEmail()));
			}
			usuarios.add(usuarioService.buscarUsuarioPorId(ut.getId()));
		});
		
		return usuarios;
	}
	
}
