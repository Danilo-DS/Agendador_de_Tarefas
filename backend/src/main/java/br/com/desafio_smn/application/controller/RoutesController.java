package br.com.desafio_smn.application.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.desafio_smn.application.model.Usuario;
import br.com.desafio_smn.application.service.LoginService;
import br.com.desafio_smn.application.utils.Utilitarios;

@Controller
@RequestMapping(value = "/api/v1")
public class RoutesController {
	
	@Autowired
	private LoginService sevice;
	
	@GetMapping("/login")
	public String login() {
		return "login/login";
	}
	
	@PostMapping("/logout")
	public String logout(HttpSession sessao) {
		sessao.invalidate();
		return "redirect:/api/v1/login";
	}
	
	@PostMapping("/home")
	public String home(HttpSession sessao) {
		
		if(sevice.userAtivo(sessao.getAttribute(Utilitarios.UsuarioON).toString())){
			sessao.invalidate();
			return "login/login";
		}
		else if(!sevice.userPermissao(sessao.getAttribute(Utilitarios.UsuarioON).toString()).equals(null)) {
			sessao.setAttribute(Utilitarios.UsuarioON, sessao.getAttribute(Utilitarios.UsuarioON).toString());
			return "home/index";
		}
		else {
			return "login/login";
		}
	}
	
	@PostMapping("/home/agendar-tarefa")
	public String formAgendarTarefas(HttpSession sessao) {
		
		if(sevice.userAtivo(sessao.getAttribute(Utilitarios.UsuarioON).toString())){
			sessao.invalidate();
			return "login/login";
		}
		else if(sevice.userPermissao(sessao.getAttribute(Utilitarios.UsuarioON).toString()).getTipo().equals(Utilitarios.UsuarioGestor)) {
			sessao.setAttribute(Utilitarios.UsuarioON, sessao.getAttribute(Utilitarios.UsuarioON).toString());
			return "tarefa/formAgendaTarefa";
		} 
		else {
			sessao.setAttribute(Utilitarios.UsuarioON, sessao.getAttribute(Utilitarios.UsuarioON).toString());
			return "home/index";
		}
	}
	
	@PostMapping("/home/usuarios/cadastrar-usuario")
	public String formCadastrarUsuario(HttpSession sessao) {
		
		if(sevice.userAtivo(sessao.getAttribute(Utilitarios.UsuarioON).toString())){
			sessao.invalidate();
			return "login/login";
		}
		else if(sevice.userPermissao(sessao.getAttribute(Utilitarios.UsuarioON).toString()).getTipo().equals(Utilitarios.UsuarioGestor)) {
			sessao.setAttribute(Utilitarios.UsuarioON, sessao.getAttribute(Utilitarios.UsuarioON).toString());
			return "usuario/formCadastroUsuario";
		}
		else {
			sessao.setAttribute(Utilitarios.UsuarioON, sessao.getAttribute(Utilitarios.UsuarioON).toString());
			return "home/index";
		}
	}
	
	@PostMapping("/home/usuarios/listar-usuario")
	public String listarUsuario(HttpSession sessao) {
		if(sevice.userAtivo(sessao.getAttribute(Utilitarios.UsuarioON).toString())){
			sessao.invalidate();
			return "login/login";
		}
		else if(!sevice.userPermissao(sessao.getAttribute(Utilitarios.UsuarioON).toString()).equals(null)) {
			sessao.setAttribute(Utilitarios.UsuarioON, sessao.getAttribute(Utilitarios.UsuarioON).toString());
			return "usuario/listaUsuario";
		}
		else {
			return "login/login";
		}
	}
	
	@GetMapping("/home/usuarios/listar-usuario")
	public String lstarUser() {
		return "usuario/listaUsuario";
	}
	
	
	@PostMapping("/efetuarLogin")
	public String efetuarlogin(Usuario u, RedirectAttributes ra, HttpSession sessao) {
		
		if (sevice.loginCredencias(u.getEmail(), u.getSenha()) != null){
			sessao.setAttribute("userAcessando", u.getEmail());
			return "home/index";
		}
		else {
			ra.addFlashAttribute("msg", "Email ou senha inválido");
			return "redirect:/api/v1/login";
		}
		
	}
}
