package br.com.agendador_tafera.application.service.empresa;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.agendador_tafera.application.config.ModelConvert;
import br.com.agendador_tafera.application.model.Empresa;
import br.com.agendador_tafera.application.model.Endereco;
import br.com.agendador_tafera.application.modelDTO.EmpresaRequestDTO;
import br.com.agendador_tafera.application.modelDTO.EmpresaResponseDTO;
import br.com.agendador_tafera.application.repository.EmpresaRepository;
import br.com.agendador_tafera.application.repository.EnderecoRepository;
import br.com.agendador_tafera.application.service.usuario.UsuarioService;

@Service
public class EmpresaService {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private EnderecoRepository enderecoRespository; 
	
	private UsuarioService usuarioService;
	
	public List<EmpresaResponseDTO> listAllEmpresa() {
		return toListEmpresaDTO(empresaRepository.findAll());
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
		if(isExisteEmpresaByCnpj(cnpj)) {
			
			Empresa empresa = atualizarDadosEmpresa(empresaRepository.findByCnpj(cnpj).get(), empresaRequest);
			empresaRepository.save(empresa);
			
			return empresaToDto(empresa);
		}
		else {
			throw new RuntimeException("");
		}
	}
	
	public void deleteEmpresa(Long id) {
		if(!isExisteEmpresa(id)) {
			throw new RuntimeException();
		}
		
		empresaRepository.deleteById(id);
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
	
	private List<EmpresaResponseDTO> toListEmpresaDTO(List<Empresa> empresa) {
		return empresa.stream().map(e -> ModelConvert.mapper().map(e, EmpresaResponseDTO.class)).collect(Collectors.toList());
	}
	
	private Empresa atualizarDadosEmpresa(Empresa empresa, EmpresaRequestDTO empresaRequest) {
		
		empresa.setNomeFantasia(StringUtils.hasText(empresaRequest.getNomeFantasia()) ? empresaRequest.getNomeFantasia() : empresa.getNomeFantasia());
		empresa.setRazaoSocial(StringUtils.hasText(empresaRequest.getRazaoSocial()) ? empresaRequest.getRazaoSocial() : empresa.getRazaoSocial());
		empresa.setCnpj(StringUtils.hasText(empresaRequest.getCnpj()) ? empresaRequest.getCnpj() : empresa.getCnpj());
		empresa.setInscricaoEstadual(StringUtils.hasText(empresaRequest.getInscricaoEstadual()) ? empresaRequest.getInscricaoEstadual() : empresa.getInscricaoEstadual());
		empresa.setEndereco(Endereco.atualizarEndereco(enderecoRespository.getOne(empresa.getEndereco().getId()), empresaRequest.getEndereco()));
		empresa.setUsuario(usuarioService.findUserId(usuarioService.updateUser(empresaRequest.getUsuario()).getId()));		
		return empresa;
	}
}
