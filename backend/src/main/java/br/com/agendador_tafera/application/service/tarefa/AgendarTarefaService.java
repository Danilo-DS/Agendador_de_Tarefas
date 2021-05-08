package br.com.agendador_tafera.application.service.tarefa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import br.com.agendador_tafera.application.config.ModelConvert;
import br.com.agendador_tafera.application.dto.tarefa.TarefaRequestDTO;
import br.com.agendador_tafera.application.dto.tarefa.TarefaResponseDTO;
import br.com.agendador_tafera.application.dto.usuario.UsuarioTarefaDTO;
import br.com.agendador_tafera.application.enums.StatusTarefa;
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
	
	@Transactional(readOnly = true)
	public List<TarefaResponseDTO> buscarTarefaPorUsuario(Long id) {
		return toListTarefaDto(agendaRepository.findByUsuario(usuarioService.buscarUsuarioPorId((id))));
	}
	
	@Transactional
	public TarefaResponseDTO salvarTarefa(TarefaRequestDTO tarefaRequest) {
		
		Empresa empresa = validaEmpresaTarefa(tarefaRequest.getEmpresa().getCnpj(), tarefaRequest.getEmpresa().getId());
		
		List<Usuario> usuarios = validaUsuarioTarefa(tarefaRequest.getUsuario());		
		
		AgendarTarefa agendarTarefa = AgendarTarefa.builder(empresa, usuarios, tarefaRequest);
		
		agendaRepository.save(agendarTarefa);
		
		enviarEmailTarefa(agendarTarefa, Utilitarios.CRIACAO);
		
		return tarefaToDto(agendarTarefa);
			
	}
	
	@Transactional
	public TarefaResponseDTO atualizarTarefa(TarefaRequestDTO tarefaRequest, Long id) {
		
		if (!isExisteTarefaPorId(id)) { 
		 	throw new AgendarTarefaException(Utilitarios.ERROR_BUSCAR_TAREFA, HttpStatus.NOT_FOUND);
		}
		
		AgendarTarefa tarefaAtualizada = atualizarDados(agendaRepository.findById(id).get(), tarefaRequest);
		
		agendaRepository.save(tarefaAtualizada);
		
		enviarEmailTarefa(tarefaAtualizada, Utilitarios.ATUALIZACAO);
		
		return tarefaToDto(tarefaAtualizada);
	}
	
	public TarefaResponseDTO finalizarTarefa(Long id) {
		
		AgendarTarefa tarefaFinalizada = agendaRepository.findById(id).orElseThrow(() -> new AgendarTarefaException(Utilitarios.ERROR_BUSCAR_TAREFA, HttpStatus.NOT_FOUND));
		tarefaFinalizada.setDtFinalizacaoTarefa(AgendarTarefa.convertData());
		tarefaFinalizada.setStatusTarefa(StatusTarefa.FINALIZADA);
		
		agendaRepository.save(tarefaFinalizada);
		
		enviarEmailTarefa(tarefaFinalizada, Utilitarios.FINALIZACAO);
		
		return tarefaToDto(tarefaFinalizada);
	}

	public TarefaResponseDTO cancelarTarefa(Long id) {
		
		AgendarTarefa tarefaFinalizada = agendaRepository.findById(id).orElseThrow(() -> new AgendarTarefaException(Utilitarios.ERROR_BUSCAR_TAREFA, HttpStatus.NOT_FOUND));
		tarefaFinalizada.setDtCancelamentoTarefa(AgendarTarefa.convertData());
		tarefaFinalizada.setStatusTarefa(StatusTarefa.CANCELADA);
		
		agendaRepository.save(tarefaFinalizada);
		
		enviarEmailTarefa(tarefaFinalizada, Utilitarios.CANCELAMENTO);
		
		return tarefaToDto(tarefaFinalizada);
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
	
	private String montaCorpoEmail(AgendarTarefa tarefa, String tipoEmail) {
		StringBuilder sb = new StringBuilder();
		sb.append("Descrição da Tarefa: " + tarefa.getDescricao());
		sb.append("Nével de Prioridade: " + tarefa.getPrioridade());
		sb.append("Situação da Tarefa: " + tarefa.getStatusTarefa());
		
		if(StringUtils.hasText(tarefa.getDtCancelamentoTarefa())) {
			sb.append("Data Cancelamento: " + tarefa.getDtCancelamentoTarefa());
		}
		
		if(StringUtils.hasText(tarefa.getDtFinalizacaoTarefa())) {
			sb.append("Data Finalização: " + tarefa.getDtFinalizacaoTarefa());
		}
		
		if(tipoEmail.equals(Utilitarios.ATUALIZACAO)) {
			sb.append("Data Criação: " + tarefa.getDtCriacaoTarefa());
		}
		
		return sb.toString();
		
	}
	
	private List<Usuario> validaUsuarioTarefa(List<UsuarioTarefaDTO> usuarioTarefa) {
		List<Usuario> usuarios = new ArrayList<>();
		
		if(!usuarioService.isExisteUsuarios(usuarioTarefa)){
			throw new AgendarTarefaException(Utilitarios.ERROR_SALVAR_TAREFA, HttpStatus.NOT_FOUND);
		}
		
		usuarioTarefa.forEach(ut -> {
			if(StringUtils.hasText(ut.getEmail())) {
				usuarios.add(usuarioService.buscarUsuarioPorEmail(ut.getEmail()));
			}
			usuarios.add(usuarioService.buscarUsuarioPorId(ut.getId()));
		});
		
		return usuarios;
	}
	
	private Empresa validaEmpresaTarefa(String cnpj, Long id) {
		var idCnpjEmpresa = StringUtils.hasText(cnpj) ? cnpj : id;
		return (idCnpjEmpresa instanceof String) ? empresaService.findEmpresaCnpj((String) idCnpjEmpresa) : empresaService.findEmpresaId((Long) idCnpjEmpresa);
	}
	
	private AgendarTarefa atualizarDados(AgendarTarefa tarefa, TarefaRequestDTO tarefaRequest) {
		
		tarefa.setTitulo(StringUtils.hasText(tarefaRequest.getTitulo()) ? tarefaRequest.getTitulo() : tarefa.getTitulo());
		tarefa.setDescricao(StringUtils.hasText(tarefaRequest.getDescricao()) ? tarefaRequest.getDescricao() : tarefa.getDescricao());
		tarefa.setUsuario(validaUsuarioTarefa(tarefaRequest.getUsuario()));
		tarefa.setEmpresa(validaEmpresaTarefa(tarefaRequest.getEmpresa().getCnpj(), tarefaRequest.getEmpresa().getId()));
		tarefa.setConvidadosEmail(CollectionUtils.isEmpty(tarefaRequest.getConvidadosEmail()) ? tarefaRequest.getConvidadosEmail().toString() : tarefa.getConvidadosEmail().toString());
		tarefa.setConvidadosTelefone(CollectionUtils.isEmpty(tarefaRequest.getConvidadosTelefone()) ? tarefaRequest.getConvidadosTelefone().toString() : tarefa.getConvidadosTelefone().toString());
		tarefa.setPrioridade(StringUtils.hasText(tarefaRequest.getPrioridade()) ? tarefaRequest.getPrioridade() : tarefa.getPrioridade());
		
		return tarefa;
	}	
	
	private void enviarEmailTarefa(AgendarTarefa tarefa, String tipoOperacaoEmail) {
		List<String> emailsNaoEnviados = emailService.enviarEmail(tarefa.getTitulo(), montaCorpoEmail(tarefa, tipoOperacaoEmail), tarefa.getUsuario());
		
		if (!CollectionUtils.isEmpty(emailsNaoEnviados)) {
			new AgendarTarefaException(Utilitarios.EMAIL_FALHOU, emailsNaoEnviados, HttpStatus.FAILED_DEPENDENCY);
		}
	}
}
