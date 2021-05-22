package br.com.agendador_tafera.application.service.usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.agendador_tafera.application.config.ModelConvert;
import br.com.agendador_tafera.application.dto.usuario.UsuarioRequestDTO;
import br.com.agendador_tafera.application.dto.usuario.UsuarioResponseDTO;
import br.com.agendador_tafera.application.dto.usuario.UsuarioTarefaDTO;
import br.com.agendador_tafera.application.exception.usuario.UsuarioException;
import br.com.agendador_tafera.application.model.PerfilUsuario;
import br.com.agendador_tafera.application.model.Usuario;
import br.com.agendador_tafera.application.repository.UsuarioRepository;
import br.com.agendador_tafera.application.service.empresa.EmpresaService;
import br.com.agendador_tafera.application.service.funcionario.FuncionarioService;
import br.com.agendador_tafera.application.service.perfilUsuario.PerfilService;
import br.com.agendador_tafera.application.utils.Utilitarios;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository userRepository;
	
	@Autowired
	private PerfilService perfilService;
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Transactional(readOnly = true)
	public List<UsuarioResponseDTO> listarUsuarios(){
		return toListUsuarioDto(userRepository.findAll());
	}
	
	@Transactional(readOnly = true)
	public List<UsuarioResponseDTO> listarUsuariosPorEmpresa(Long idEmpresa){	
		return  funcionarioService.listaFuncionariosEmpresa(
				empresaService.buscarEmpresaPorId(idEmpresa).getCnpj()
			).stream().map(f -> f.getUsuario()).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public Usuario buscarUsuarioPorEmail(String email){
		return userRepository.findByEmail(email).orElseThrow(() -> new UsuarioException(Utilitarios.ERROR_BUSCAR_USUARIO, HttpStatus.NOT_FOUND));
	}
	
	@Transactional(readOnly = true)
	public Usuario buscarUsuarioPorId(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new UsuarioException(Utilitarios.ERROR_BUSCAR_USUARIO, HttpStatus.NOT_FOUND));
	}
	
	@Transactional
	public UsuarioResponseDTO salvarUsuario(UsuarioRequestDTO  usuarioRequest) {
		if(isExisteUsuarioPorEmail(usuarioRequest.getEmail())) {
			throw new UsuarioException("Já existe um usuario com este E-mail", HttpStatus.BAD_REQUEST);
		}
		
		Usuario usuario = dtoToUsuario(usuarioRequest);
		usuario.setSenha(usuario.encriptPassword(usuario.getSenha()));	
		usuario.setPerfis(validaPerfilUsuario(usuario.getPerfis()));
		userRepository.save(usuario);
		
		return usuarioToDto(usuario);
	}
	
	@Transactional
	public UsuarioResponseDTO atualizarUsuario(UsuarioRequestDTO usuarioRequest, Long id) {
		
		if(!isExisteUsuarioPorId(id)) {
			throw new UsuarioException(Utilitarios.ERROR_ATUALIZAR_USUARIO, HttpStatus.NOT_FOUND);
		}
		
		Usuario usuario = atualizaDados(userRepository.findById(id).get(), usuarioRequest);
		userRepository.save(usuario);
		
		return usuarioToDto(usuario);
	}
	
	@Transactional
	public void deletarUsuario(Long id) {
		if(isExisteUsuarioPorId(id)) {
			userRepository.deleteById(id);
		}
		else {
			throw new UsuarioException(Utilitarios.ERROR_DELETAR_USUARIO, HttpStatus.NOT_FOUND);
		}
	}
	
	@Transactional(readOnly = true)
	public Boolean isExisteUsuarioPorId(Long id) {
		return userRepository.existsById(id);
		
	}
	
	@Transactional(readOnly = true)
	public Boolean isExisteUsuarioPorEmail(String email) {
		return userRepository.existsByEmail(email);
	}
	
	public List<Usuario> validarUsuariosTarefa(List<UsuarioTarefaDTO> usuarioTarefa) {

		List<Usuario> usuarios = new ArrayList<>();
		
		usuarioTarefa.forEach(u ->{
			usuarios.add(setarUsuario(u.getEmail(), u.getId()));
		});
		
		return usuarios;
	}
	
	@Transactional(readOnly = true)
	public Usuario setarUsuario(String email, Long id) {
		var idEmailUsuario = StringUtils.hasText(email) ? email : id;
		return (idEmailUsuario instanceof String) ? buscarUsuarioPorEmail((String) idEmailUsuario) : buscarUsuarioPorId((Long) idEmailUsuario);
	}
	
	private Usuario dtoToUsuario(UsuarioRequestDTO usuarioRequest) {
		return ModelConvert.mapper().map(usuarioRequest, Usuario.class);
	}
	
	private List<UsuarioResponseDTO> toListUsuarioDto(List<Usuario> user) {
		return user.stream().map(u -> ModelConvert.mapper().map(u, UsuarioResponseDTO.class)).collect(Collectors.toList());
		
	}
	
	private UsuarioResponseDTO usuarioToDto(Usuario usuario) {
		return ModelConvert.mapper().map(usuario, UsuarioResponseDTO.class);
	}
	
	private List<PerfilUsuario> validaPerfilUsuario(List<PerfilUsuario> perfil) {
		return perfil.stream().map(p -> perfilService.buscarPerfilPorId(p.getId())).collect(Collectors.toList());
	}
	
	private Usuario atualizaDados(Usuario usuario, UsuarioRequestDTO usuarioRequest) {
		usuario.setNome(StringUtils.hasText(usuarioRequest.getNome()) ? usuarioRequest.getNome() : usuario.getNome());
		usuario.setEmail(StringUtils.hasText(usuarioRequest.getEmail()) ? usuarioRequest.getEmail() : usuario.getEmail());
		usuario.setSenha(usuario.encriptPassword(StringUtils.hasText(usuarioRequest.getSenha()) ? usuarioRequest.getSenha() : usuario.getSenha()));
		usuario.setTipoUsuario(StringUtils.hasText(usuarioRequest.getTipoUsuario()) ? usuarioRequest.getTipoUsuario() : usuario.getTipoUsuario());
		usuario.setPerfis(validaPerfilUsuario(usuarioRequest.getPerfis()));
		return usuario;
	}
	
	
}
