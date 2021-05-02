package br.com.agendador_tafera.application.config.security.payload;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import br.com.agendador_tafera.application.enums.PerfilUS;
import br.com.agendador_tafera.application.model.PerfilUsuario;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLogin implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String email;
	private String senha;
	private String tipoUsuario;
	private List<PerfilUsuario> perfis;
	
	private Collection<? extends GrantedAuthority> authorities;

	public UserLogin (Long id, String email, String senha, String tipoUsuario, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.tipoUsuario = tipoUsuario;
		this.authorities = authorities;
	}
	
	public static UserLogin builder(UserLogin user) {
		
		List<GrantedAuthority> role = new ArrayList<>(); 
		
		if(!CollectionUtils.isEmpty(user.getPerfis())) {
			user.getPerfis().forEach(p -> {
				role.add(new SimpleGrantedAuthority("ROLE_"+ user.getPermissao(p.getId())));
			});
		}
		
		return new UserLogin(
					user.getId(),
					user.getUsername(),
					user.getPassword(),
					user.getTipoUsuario(),
					role
			   );
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	@Override
	public String getPassword() {
		return this.senha;
	}
	@Override
	public String getUsername() {
		return this.email;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	private String getPermissao(Long idPerfil) {
		
		if(PerfilUS.ADMINISTRADOR.getIdPerfil().longValue() == idPerfil) {
			return PerfilUS.ADMINISTRADOR.getPerfil();
		}
		
		if(PerfilUS.EMPRESA.getIdPerfil().longValue() == idPerfil) {
			return PerfilUS.EMPRESA.getPerfil();
		}
		
		if(PerfilUS.FUNCIONARIO.getIdPerfil().longValue() == idPerfil) {
			return PerfilUS.FUNCIONARIO.getPerfil();
		}
		
		if(PerfilUS.GESTOR.getIdPerfil().longValue() == idPerfil) {
			return PerfilUS.GESTOR.getPerfil();
		}
		
		if(PerfilUS.MASTER.getIdPerfil().longValue() == idPerfil) {
			return PerfilUS.MASTER.getPerfil();
		}
		
		return PerfilUS.USUARIO_COMUM.getPerfil();
		
	}
}
