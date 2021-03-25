package br.com.academy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.dao.UsuarioDao;
import br.com.academy.model.Usuario;
import br.com.academy.service.ServiceUsuario;

@Controller
public class UsuarioController {

	//@Autowired
	//private UsuarioDao usuarioRepositorio; //removido na aula 18
	
	@Autowired
	private ServiceUsuario serviceUsuario;
	
	@GetMapping("/")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login/login");
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
	//usuarioRepositorio.save(usuario);//após a implantação da regra de negocio de criptografia no service este não salva mais o user
	//18 Curso Spring Boot: Criptografia e Camada Service do Login
	serviceUsuario.salvarUsuario(usuario);
	mv.setViewName("redirect:/");
	
	return mv;
	}
}
