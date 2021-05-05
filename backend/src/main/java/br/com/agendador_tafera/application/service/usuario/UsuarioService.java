package br.com.agendador_tafera.application.service.usuario;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.agendador_tafera.application.config.ModelConvert;
import br.com.agendador_tafera.application.exception.usuario.UsuarioException;
import br.com.agendador_tafera.application.model.Usuario;
import br.com.agendador_tafera.application.modelDTO.UsuarioRequestDTO;
import br.com.agendador_tafera.application.modelDTO.UsuarioResponseDTO;
import br.com.agendador_tafera.application.repository.UsuarioRepository;
import br.com.agendador_tafera.application.utils.Utilitarios;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository userRepository;
	
	@Transactional(readOnly = true)
	public List<UsuarioResponseDTO> listAllUsers(){
		return toListUsuarioDto(userRepository.findAll());
	}
	
	@Transactional(readOnly = true)
	public Usuario findUserId(Long id) {
		Optional<Usuario> user = userRepository.findById(id);
		return user.orElseThrow(() -> new UsuarioException(Utilitarios.ERROR_BUSCAR_USUARIO, HttpStatus.NOT_FOUND));
	}
	
	@Transactional
	public UsuarioResponseDTO saveUser(UsuarioRequestDTO  usuarioRequest) {
		Usuario usuario = dtoToUsuario(usuarioRequest);
		usuario.setSenha(usuario.encriptPassword(usuario.getSenha()));
		userRepository.save(usuario);
		return usuarioToDto(usuario);
	}
	
	@Transactional
	public UsuarioResponseDTO updateUser(UsuarioRequestDTO usuarioRequest) {
		
		String email = usuarioRequest.getEmail();
		
		if(isExisteUsuarioPorEmail(email)) {
			Usuario usuario = atualizaDadosUsuario(userRepository.findByEmail(email).get(), usuarioRequest);
			userRepository.save(usuario);
			
			return usuarioToDto(usuario);
		}
		else {
			throw new UsuarioException(Utilitarios.ERROR_ATUALIZAR_USUARIO, HttpStatus.NOT_FOUND);
		}
	}
	
	@Transactional
	public void deleteUser(Long id) {
		if(isExisteUsuarioPorId(id)) {
			userRepository.deleteById(id);
		}
		else {
			throw new UsuarioException(Utilitarios.ERROR_DELETAR_USUARIO, HttpStatus.NOT_FOUND);
		}
	}
	
	@Transactional(readOnly = true)
	public boolean isExisteUsuarioPorId(Long id) {
		return userRepository.existsById(id);
		
	}
	
	@Transactional(readOnly = true)
	public boolean isExisteUsuarioPorEmail(String email) {
		return userRepository.existsByEmail(email);
		
	}
	
	private Usuario dtoToUsuario(UsuarioRequestDTO usuarioRequest) {
		return ModelConvert.mapper().map(usuarioRequest, Usuario.class);
	}
	
	private List<UsuarioResponseDTO> toListUsuarioDto(List<Usuario> user) {
		return user.stream().map(u -> ModelConvert.mapper().map(u, UsuarioResponseDTO.class)).collect(Collectors.toList());
		
	}
	
	private UsuarioResponseDTO usuarioToDto(Usuario usuario) {
		UsuarioResponseDTO usuarioResponse = ModelConvert.mapper().map(usuario, UsuarioResponseDTO.class);
		usuario.getPerfis().forEach(p -> {
			usuarioResponse.getPermissao().add(p.getPerfil().getPerfil());
		});
		return usuarioResponse;
	}
	
	private Usuario atualizaDadosUsuario(Usuario usuario, UsuarioRequestDTO usuarioRequest) {
		usuario.setNome(StringUtils.hasText(usuarioRequest.getNome()) ? usuarioRequest.getNome() : usuario.getNome());
		usuario.setEmail(StringUtils.hasText(usuarioRequest.getEmail()) ? usuarioRequest.getEmail() : usuario.getEmail());
		usuario.setSenha(usuario.encriptPassword(StringUtils.hasText(usuarioRequest.getSenha()) ? usuarioRequest.getSenha() : usuario.getSenha()));
		usuario.setTipoUsuario(StringUtils.hasText(usuarioRequest.getTipoUsuario()) ? usuarioRequest.getTipoUsuario() : usuario.getTipoUsuario());
		usuario.setPerfis(usuarioRequest.getPerfis());
		return usuario;
	}
	
	
}
