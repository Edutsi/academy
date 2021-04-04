package br.com.academy.controllers;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.dao.UsuarioDao;
import br.com.academy.exceptions.ServiceExc;
import br.com.academy.model.Aluno;
import br.com.academy.model.Usuario;
import br.com.academy.service.ServiceUsuario;
import br.com.academy.util.Util;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioDao usuarioRepositorio; //removido na aula 18

	@Autowired
	private ServiceUsuario serviceUsuario;

	@GetMapping("/")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login/login");
		return mv;

	}

	@GetMapping("/index") // redireciona para a pagina principal
	public ModelAndView index() {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("home/index");
		mv.addObject("aluno", new Aluno());
		return mv;
	}

	@GetMapping("/cadastro")
	public ModelAndView cadastrar() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("usuario", new Usuario());
		mv.setViewName("login/cadastro");
		return mv;

	}

	@PostMapping("salvarUsuario")
	public ModelAndView cadastrar(Usuario usuario) throws Exception {
		ModelAndView mv = new ModelAndView();
		// usuarioRepositorio.save(usuario);//após a implantação da regra de negocio de
		// criptografia no service este não salva mais o user
		// 18 Curso Spring Boot: Criptografia e Camada Service do Login
		serviceUsuario.salvarUsuario(usuario);
		mv.setViewName("redirect:/");
https://www.youtube.com/
		return mv;
	}

	@PostMapping("/login")
	public ModelAndView login(@Valid Usuario usuario, BindingResult br, HttpSession session)
			throws NoSuchAlgorithmException, ServiceExc {
		// public ModelAndView login(@Valid Usuario usuario, BindingResult br,
					// RedirectAttributes attributes)
		// NoSuchAlgorithmException por causa do MD5
		ModelAndView mv = new ModelAndView();
		mv.addObject("usuario", new Usuario());
		if (br.hasErrors()) {
			mv.setViewName("/login");
		}

		Usuario userLogin = serviceUsuario.loginUser(usuario.getUser(), Util.md5(usuario.getSenha()));
		if (userLogin == null) {
			mv.addObject("msg", "Usuário não encontrado. Tente Novamente");
		} else {
			session.setAttribute("usuarioLogado ", userLogin);
			return index();
		}
		return mv;

	}

	@PostMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		return login();
	}

}
