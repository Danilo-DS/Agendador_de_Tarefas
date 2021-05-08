package br.com.agendador_tafera.application.service.funcionario;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.agendador_tafera.application.config.ModelConvert;
import br.com.agendador_tafera.application.dto.funcionario.FuncionarioRequestDTO;
import br.com.agendador_tafera.application.dto.funcionario.FuncionarioResponseDTO;
import br.com.agendador_tafera.application.model.Endereco;
import br.com.agendador_tafera.application.model.Funcionario;
import br.com.agendador_tafera.application.repository.FuncionarioRepository;
import br.com.agendador_tafera.application.service.empresa.EmpresaService;
import br.com.agendador_tafera.application.service.endereco.EnderecoService;
import br.com.agendador_tafera.application.service.usuario.UsuarioService;

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
	
	public List<Funcionario> listAllFuncionario() {		
		return funcionarioRepository.findAll();
	}
	
	public List<FuncionarioResponseDTO> listAllFuncionario(String cnpjEmpresa) {		
		return toListFuncionarioDto(funcionarioRepository.findByEmpresa(empresaService.findEmpresaCnpj(cnpjEmpresa)));
	}
	
	public Funcionario findFuncionarioId(Long id) {
		return funcionarioRepository.findById(id).orElseThrow(() -> new RuntimeException());
	}
	
	public void saveFuncionario(FuncionarioRequestDTO funcionarioRequest) {
		Funcionario funcionario = dtoToFuncionario(funcionarioRequest);
		funcionario.setDataNascimento(funcionario.converteData(funcionarioRequest.getDtNascimento()));
		funcionarioRepository.save(funcionario);
	}
	
	public FuncionarioResponseDTO updateFuncionario(FuncionarioRequestDTO funcionarioRequest) {
		String cpf = funcionarioRequest.getCpf();
		
		if(!isExistingFuncionarioByCpf(cpf)) {
			throw new RuntimeException("");
		}
		
		Funcionario funcionario = atualizarDados(findFuncionarioByCpf(cpf), funcionarioRequest);
		funcionarioRepository.save(funcionario);
		return funcionarioToDto(funcionario);
	}
	
	public void deleteFuncionario(Long id) {
		if(!isExistingFuncionario(id)) {
			throw new RuntimeException();
		}
		
		funcionarioRepository.deleteById(id);
	}
	
	public Funcionario findFuncionarioByCpf(String cpf) {
		return funcionarioRepository.findByCpf(cpf).orElseThrow(() -> new RuntimeException(""));
	}
	
	private Boolean isExistingFuncionario(Long id) {
		return funcionarioRepository.existsById(id);
	}
	
	private Boolean isExistingFuncionarioByCpf(String cpf) {
		return funcionarioRepository.existsByCpf(cpf);
	}
	
	private Funcionario dtoToFuncionario(FuncionarioRequestDTO funcionarioRequest) {
		return ModelConvert.mapper().map(funcionarioRequest, Funcionario.class);
	}
	
	private FuncionarioResponseDTO funcionarioToDto(Funcionario funcionario) {
		return ModelConvert.mapper().map(funcionario, FuncionarioResponseDTO.class);
	}
	
	private List<FuncionarioResponseDTO> toListFuncionarioDto(List<Funcionario> funcionario) {
		return funcionario.stream().map(func->ModelConvert.mapper().map(func, FuncionarioResponseDTO.class)).collect(Collectors.toList());
	}
	
	private Funcionario atualizarDados(Funcionario funcionario, FuncionarioRequestDTO funcionarioRequestDTO) {
		
		funcionario.setNome(StringUtils.hasText(funcionarioRequestDTO.getNome()) ? funcionarioRequestDTO.getNome() : funcionario.getNome());
		funcionario.setCpf(StringUtils.hasText(funcionarioRequestDTO.getCpf()) ? funcionarioRequestDTO.getCpf() : funcionario.getCpf());
		funcionario.setPisPasep(StringUtils.hasText(funcionarioRequestDTO.getPisPasep()) ? funcionarioRequestDTO.getPisPasep() : funcionario.getPisPasep());
		funcionario.setDataNascimento(StringUtils.hasText(funcionarioRequestDTO.getNome()) ? funcionario.converteData(funcionarioRequestDTO.getDtNascimento()) : funcionario.getDataNascimento());
		funcionario.setCelular(StringUtils.hasText(funcionarioRequestDTO.getNome()) ? funcionarioRequestDTO.getNome() : funcionario.getNome());
		funcionario.setTelefone(StringUtils.hasText(funcionarioRequestDTO.getNome()) ? funcionarioRequestDTO.getNome() : funcionario.getNome());
		funcionario.setEmpresa(empresaService.findEmpresaCnpj(funcionarioRequestDTO.getEmpresa().getCnpj()));
		funcionario.setEndereco(Endereco.atualizarEndereco(enderecoService.findEnderecoId(funcionario.getEmpresa().getId()), funcionarioRequestDTO.getEndereco()));
		funcionario.setUsuario(usuarioService.buscarUsuarioPorId(funcionario.getUsuario().getId()));
		return funcionario;
	}
	
}
