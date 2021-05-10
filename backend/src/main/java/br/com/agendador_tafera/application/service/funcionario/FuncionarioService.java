package br.com.agendador_tafera.application.service.funcionario;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.agendador_tafera.application.config.ModelConvert;
import br.com.agendador_tafera.application.dto.funcionario.FuncionarioRequestDTO;
import br.com.agendador_tafera.application.dto.funcionario.FuncionarioResponseDTO;
import br.com.agendador_tafera.application.exception.funcionario.FuncionarioException;
import br.com.agendador_tafera.application.model.Empresa;
import br.com.agendador_tafera.application.model.Endereco;
import br.com.agendador_tafera.application.model.Funcionario;
import br.com.agendador_tafera.application.model.Usuario;
import br.com.agendador_tafera.application.repository.FuncionarioRepository;
import br.com.agendador_tafera.application.service.empresa.EmpresaService;
import br.com.agendador_tafera.application.service.endereco.EnderecoService;
import br.com.agendador_tafera.application.service.usuario.UsuarioService;
import br.com.agendador_tafera.application.utils.Utilitarios;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private EnderecoService enderecoService;
	
	@Transactional(readOnly = true)
	public List<FuncionarioResponseDTO> listarFuncionario() {		
		return toListFuncionarioDto(funcionarioRepository.findAll());
	}
	
	@Transactional(readOnly = true)
	public List<FuncionarioResponseDTO> listaFuncionariosEmpresa(String cnpjEmpresa) {		
		return toListFuncionarioDto(funcionarioRepository.findByEmpresaCnpj(cnpjEmpresa));
	}
	
	@Transactional(readOnly = true)
	public Funcionario buscarFuncionarioPorId(Long id) {
		return funcionarioRepository.findById(id).orElseThrow(() -> new FuncionarioException(Utilitarios.ERROR_BUSCAR_FUNCIONARIO, HttpStatus.NOT_FOUND));
	}
	
	@Transactional
	public FuncionarioResponseDTO salvarFuncionario(FuncionarioRequestDTO funcionarioRequest) {
		if(isExisteFuncionarioPorCpf(funcionarioRequest.getCpf())) {
			throw new FuncionarioException(Utilitarios.ERROR_SALVAR_FUNCIONARIO, HttpStatus.BAD_REQUEST);
		}
		
		Empresa empresa = empresaService.setarEmpresa(funcionarioRequest.getEmpresa().getCnpj(), funcionarioRequest.getEmpresa().getId());
				
		Long idUsuario = usuarioService.salvarUsuario(funcionarioRequest.getUsuarioFuncioario()).getId();
		Funcionario funcionario = dtoToFuncionario(funcionarioRequest);
		funcionario.setDataNascimento(funcionario.formatarData(funcionarioRequest.getDtNascimento()));
		funcionario.setEmpresa(empresa);
		funcionario.setUsuario(usuarioService.buscarUsuarioPorId(idUsuario));
		
		funcionarioRepository.save(funcionario);
		return funcionarioToDto(funcionario);
	}
	
	@Transactional
	public FuncionarioResponseDTO atualizarFuncionario(FuncionarioRequestDTO funcionarioRequest, Long id) {
		if(!isExisteFuncionarioPorId(id)) {
			throw new FuncionarioException(Utilitarios.ERROR_ATUALIZAR_FUNCIONARIO, HttpStatus.BAD_REQUEST);
		}
		
		Funcionario funcionario = atualizarDados(buscarFuncionarioPorId(id), funcionarioRequest);
		funcionarioRepository.save(funcionario);
		return funcionarioToDto(funcionario);
	}
	
	@Transactional
	public void deletarFuncionario(Long id) {
		if(!isExisteFuncionarioPorId(id)) {
			throw new FuncionarioException(Utilitarios.ERROR_DELETAR_FUNCIONARIO, HttpStatus.NOT_FOUND);
		}
		
		funcionarioRepository.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public Funcionario buscarFuncionarioPorCpf(String cpf) {
		return funcionarioRepository.findByCpf(cpf).orElseThrow(() -> new FuncionarioException(Utilitarios.ERROR_BUSCAR_FUNCIONARIO, HttpStatus.NOT_FOUND));
	}
	
	@Transactional(readOnly = true)
	public void validaFuncionarioEmpresa(List<Usuario> usuarios, Long idEmpresa) {
		
		usuarios.forEach(u -> {
			if(!funcionarioRepository.existsByUsuarioAndEmpresaId(u, idEmpresa)) {
				throw new FuncionarioException("Usuario/Funcionario Não Está Associado a Empresa\n"
						+ "ID: " + u.getId() + " Email: " + u.getEmail() , HttpStatus.BAD_REQUEST);
			}
		});
	}
	
	@Transactional(readOnly = true)
	private Boolean isExisteFuncionarioPorId(Long id) {
		return funcionarioRepository.existsById(id);
	}
	
	@Transactional(readOnly = true)
	private Boolean isExisteFuncionarioPorCpf(String cpf) {
		return funcionarioRepository.existsByCpf(cpf);
	}
	
	@Transactional(readOnly = true)
	private Funcionario dtoToFuncionario(FuncionarioRequestDTO funcionarioRequest) {
		return ModelConvert.mapper().map(funcionarioRequest, Funcionario.class);
	}
	
	private FuncionarioResponseDTO funcionarioToDto(Funcionario funcionario) {
		return ModelConvert.mapper().map(funcionario, FuncionarioResponseDTO.class);
	}
	
	private List<FuncionarioResponseDTO> toListFuncionarioDto(List<Funcionario> funcionario) {
		return funcionario.stream().map(func->ModelConvert.mapper().map(func, FuncionarioResponseDTO.class)).collect(Collectors.toList());
	}
	
	private Funcionario atualizarDados(Funcionario funcionario, FuncionarioRequestDTO funcionarioRequest) {
		
		funcionario.setNome(StringUtils.hasText(funcionarioRequest.getNome()) ? funcionarioRequest.getNome() : funcionario.getNome());
		funcionario.setCpf(StringUtils.hasText(funcionarioRequest.getCpf()) ? funcionarioRequest.getCpf() : funcionario.getCpf());
		funcionario.setPisPasep(StringUtils.hasText(funcionarioRequest.getPisPasep()) ? funcionarioRequest.getPisPasep() : funcionario.getPisPasep());
		funcionario.setDataNascimento(StringUtils.hasText(funcionarioRequest.getDtNascimento()) ? funcionario.formatarData(funcionarioRequest.getDtNascimento()) : funcionario.getDataNascimento());
		funcionario.setCelular(StringUtils.hasText(funcionarioRequest.getCelular()) ? funcionarioRequest.getCelular() : funcionario.getCelular());
		funcionario.setTelefone(StringUtils.hasText(funcionarioRequest.getTelefone()) ? funcionarioRequest.getTelefone() : funcionario.getTelefone());
		funcionario.setEmpresa(empresaService.setarEmpresa(funcionarioRequest.getEmpresa().getCnpj(),funcionarioRequest.getEmpresa().getId()));
		funcionario.setEndereco(Endereco.atualizarEndereco(enderecoService.buscarEnderecoPorId(funcionario.getEndereco().getId()), funcionarioRequest.getEndereco()));
		funcionario.setUsuario(usuarioService.buscarUsuarioPorId(usuarioService.atualizarUsuario(funcionarioRequest.getUsuarioFuncioario(), funcionario.getUsuario().getId()).getId()));
		
		return funcionario;
	}
	
}
