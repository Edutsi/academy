package br.com.academy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.dao.AlunoDao;
import br.com.academy.model.Aluno;

@Controller
public class AlunoController {

	@Autowired
	private AlunoDao alunorepositorio;
	@GetMapping("/inserirAlunos")
	//passa a variavel aluno e cria o OBJ ALUNO
	public ModelAndView InsertAlunos(Aluno aluno) {
		ModelAndView mv= new ModelAndView();
		mv.setViewName("Aluno/formAluno");//caminho da pagina forAluno
		mv.addObject("aluno", new Aluno());//add o obj aluno para a view
		return mv;
		
	}
	@PostMapping("insertAlunos")
	public ModelAndView inserirAluno(Aluno aluno){
		 ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/alunos-adicionados");
		alunorepositorio.save(aluno);
		return mv;
	}
	@GetMapping("alunos-adicionados")
	public ModelAndView listagemAlunos() {
		ModelAndView mv= new ModelAndView();
		mv.setViewName("Aluno/listAlunos");//caminho da pagina listAlunos
		mv.addObject("alunosList", alunorepositorio.findAll());//findAll chama todos os dados do banco
		return mv;
		
	}
	
}
