package br.com.agendador_tafera.application.service.empresa;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.agendador_tafera.application.config.ModelConvert;
import br.com.agendador_tafera.application.dto.empresa.EmpresaRequestDTO;
import br.com.agendador_tafera.application.dto.empresa.EmpresaResponseDTO;
import br.com.agendador_tafera.application.model.Empresa;
import br.com.agendador_tafera.application.model.Endereco;
import br.com.agendador_tafera.application.repository.EmpresaRepository;
import br.com.agendador_tafera.application.service.endereco.EnderecoService;
import br.com.agendador_tafera.application.service.usuario.UsuarioService;

@Service
public class EmpresaService {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private EnderecoService enderecoService;
	
	private UsuarioService usuarioService;
	
	public List<EmpresaResponseDTO> listAllEmpresa() {
		return toListEmpresaDto(empresaRepository.findAll());
	}
	
	public Empresa findEmpresaId(Long id) {
		return empresaRepository.findById(id).orElseThrow(() -> new RuntimeException());
	}
	
	//Observação
	public void saveEmpresa(EmpresaRequestDTO empresa) {
		empresaRepository.save(dtoToEmpresa(empresa));
	}
	
	public EmpresaResponseDTO updateEmpresa(EmpresaRequestDTO empresaRequest) {
		String cnpj = empresaRequest.getCnpj();
		if(!isExisteEmpresaByCnpj(cnpj)) {
			throw new RuntimeException("");
			
		}
		
		Empresa empresa = atualizarDados(findEmpresaCnpj(cnpj), empresaRequest);
		empresaRepository.save(empresa);
		
		return empresaToDto(empresa);
	}
	
	public void deleteEmpresa(Long id) {
		if(!isExisteEmpresa(id)) {
			throw new RuntimeException();
		}
		
		empresaRepository.deleteById(id);
	}
	
	public Empresa findEmpresaCnpj(String cnpj) {
		return empresaRepository.findByCnpj(cnpj).orElseThrow(() -> new RuntimeException(""));
	}
	
	private Boolean isExisteEmpresa(Long id) {
		return empresaRepository.existsById(id);
	}
	
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
		empresa.setEndereco(Endereco.atualizarEndereco(enderecoService.findEnderecoId(empresa.getEndereco().getId()), empresaRequest.getEndereco()));
		empresa.setUsuario(usuarioService.buscarUsuarioPorId(usuarioService.atualizarUsuario(empresaRequest.getUsuario()).getId()));		
		return empresa;
	}
}
