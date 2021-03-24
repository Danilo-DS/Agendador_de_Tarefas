package br.com.agendador_tafera.application.config.security.payload;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLogin implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String email;
	private String senha;
	private String tipo;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public static UserLogin builder(UserLogin user) {
		
		List<GrantedAuthority> role = new ArrayList<>();
		role.add(new SimpleGrantedAuthority(user.getTipo()));
		
		return new UserLogin(
					user.getId(),
					user.getUsername(),
					user.getPassword(),
					user.getTipo(),
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
	
	
}
