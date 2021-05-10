package br.com.agendador_tafera.application.service.empresa;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import br.com.agendador_tafera.application.config.ModelConvert;
import br.com.agendador_tafera.application.dto.empresa.EmpresaRequestDTO;
import br.com.agendador_tafera.application.dto.empresa.EmpresaResponseDTO;
import br.com.agendador_tafera.application.exception.empresa.EmpresaException;
import br.com.agendador_tafera.application.model.Empresa;
import br.com.agendador_tafera.application.model.Endereco;
import br.com.agendador_tafera.application.repository.EmpresaRepository;
import br.com.agendador_tafera.application.service.endereco.EnderecoService;
import br.com.agendador_tafera.application.service.usuario.UsuarioService;
import br.com.agendador_tafera.application.utils.Utilitarios;

@Service
public class EmpresaService {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private EnderecoService enderecoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Transactional(readOnly = true)
	public List<EmpresaResponseDTO> listarEmpresas() {
		return toListEmpresaDto(empresaRepository.findAll());
	}
	
	@Transactional(readOnly = true)
	public Empresa buscarEmpresaPorId(Long id) {
		return empresaRepository.findById(id).orElseThrow(() -> new EmpresaException(Utilitarios.ERROR_BUSCAR_EMPRESA, HttpStatus.NOT_FOUND));
	}
	
	@Transactional
	public EmpresaResponseDTO salvarEmpresa(EmpresaRequestDTO empresaRequest) {
		if(isExisteEmpresaByCnpj(empresaRequest.getCnpj())) {
			throw new EmpresaException(Utilitarios.ERROR_SALVAR_EMPRESA, HttpStatus.BAD_REQUEST);
		}
		
		Long idUsuario = usuarioService.salvarUsuario(empresaRequest.getUsuarioEmpresa()).getId();
		Empresa empresa = dtoToEmpresa(empresaRequest);
		empresa.setUsuario(usuarioService.buscarUsuarioPorId(idUsuario));
		empresaRepository.save(empresa);
		
		return empresaToDto(empresa);
	}
	
	@Transactional
	public EmpresaResponseDTO atualizarEmpresa(EmpresaRequestDTO empresaRequest, Long id) {
		if(!isExisteEmpresaId(id)) {
			throw new EmpresaException(Utilitarios.ERROR_ATUALIZAR_EMPRESA, HttpStatus.BAD_REQUEST);
		}
		
		Empresa empresa = atualizarDados(buscarEmpresaPorId(id), empresaRequest);
		empresaRepository.save(empresa);
		
		return empresaToDto(empresa);
	}
	
	@Transactional
	public void deletarEmpresa(Long id) {
		if(!isExisteEmpresaId(id)) {
			throw new EmpresaException(Utilitarios.ERROR_DELETAR_EMPRESA, HttpStatus.BAD_REQUEST);
		}
		
		empresaRepository.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	private Empresa buscarEmpresaCnpj(String cnpj) {
		return empresaRepository.findByCnpj(cnpj).orElseThrow(() -> new EmpresaException(Utilitarios.ERROR_BUSCAR_EMPRESA, HttpStatus.NOT_FOUND));
	}
	
	@Transactional(readOnly = true)
	public Empresa setarEmpresa(String cnpj, Long id) {
		var idCnpjEmpresa = StringUtils.hasText(cnpj) ? cnpj : id;
		return (idCnpjEmpresa instanceof String) ? buscarEmpresaCnpj((String) idCnpjEmpresa) : buscarEmpresaPorId((Long) idCnpjEmpresa);
	}
	
	@Transactional(readOnly = true)
	private Boolean isExisteEmpresaId(Long id) {
		return empresaRepository.existsById(id);
	}
	
	@Transactional(readOnly = true)
	private Boolean isExisteEmpresaByCnpj(String cnpj) {
		return empresaRepository.existsByCnpj(cnpj);
	}
	
	private Empresa dtoToEmpresa(EmpresaRequestDTO empresa) {
		return ModelConvert.mapper().map(empresa, Empresa.class);
	}
	
	private EmpresaResponseDTO empresaToDto(Empresa empresa) {
		return ModelConvert.mapper().map(empresa, EmpresaResponseDTO.class);
	}
	
	private List<EmpresaResponseDTO> toListEmpresaDto(List<Empresa> empresa) {
		return empresa.stream().map(e -> ModelConvert.mapper().map(e, EmpresaResponseDTO.class)).collect(Collectors.toList());
	}
	
	private Empresa atualizarDados(Empresa empresa, EmpresaRequestDTO empresaRequest) {
		
		empresa.setNomeFantasia(StringUtils.hasText(empresaRequest.getNomeFantasia()) ? empresaRequest.getNomeFantasia() : empresa.getNomeFantasia());
		empresa.setRazaoSocial(StringUtils.hasText(empresaRequest.getRazaoSocial()) ? empresaRequest.getRazaoSocial() : empresa.getRazaoSocial());
		empresa.setCnpj(StringUtils.hasText(empresaRequest.getCnpj()) ? empresaRequest.getCnpj() : empresa.getCnpj());
		empresa.setInscricaoEstadual(StringUtils.hasText(empresaRequest.getInscricaoEstadual()) ? empresaRequest.getInscricaoEstadual() : empresa.getInscricaoEstadual());
		empresa.setEndereco(Endereco.atualizarEndereco(enderecoService.buscarEnderecoPorId(empresa.getEndereco().getId()), empresaRequest.getEndereco()));
		empresa.setUsuario(usuarioService.buscarUsuarioPorId(usuarioService.atualizarUsuario(empresaRequest.getUsuarioEmpresa(), empresa.getUsuario().getId()).getId()));		
		return empresa;
	}
}
