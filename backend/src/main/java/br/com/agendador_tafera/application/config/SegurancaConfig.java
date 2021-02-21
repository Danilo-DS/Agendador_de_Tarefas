package br.com.agendador_tafera.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.agendador_tafera.application.service.LoginService;

@Configuration
@EnableWebSecurity
public class SegurancaConfig extends WebSecurityConfigurerAdapter{

//	@Bean
//	@Override
//	protected AuthenticationManager authenticationManager() throws Exception {
//		return super.authenticationManager();
//	}
	
	@Autowired
	private LoginService login;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.GET, "/api/v1/usuarios/t/*").permitAll()
		.anyRequest().authenticated().and()
		.formLogin().permitAll().and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/api/v1/"));
		//super.configure(http);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(login).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	/*
	 * @Override public void configure(WebSecurity web) throws Exception {
	 * 
	 * super.configure(web); }
	 */
	
	
	
}
