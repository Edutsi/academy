package br.com.academy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.dao.AlunoDao;
import br.com.academy.model.Aluno;

@Controller
public class AlunoController {

	@Autowired
	private AlunoDao alunorepositorio;

	@GetMapping("/inserirAlunos")
	// passa a variavel aluno e cria o OBJ ALUNO
	public ModelAndView InsertAlunos(Aluno aluno) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Aluno/formAluno");// caminho da pagina forAluno
		mv.addObject("aluno", new Aluno());// add o obj aluno para a view
		return mv;

	}

	@PostMapping("insertAlunos")
	public ModelAndView inserirAluno(Aluno aluno) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/alunos-adicionados");
		alunorepositorio.save(aluno);
		return mv;
	}

	@GetMapping("alunos-adicionados")
	public ModelAndView listagemAlunos() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Aluno/listAlunos");// caminho da pagina listAlunos
		mv.addObject("alunosList", alunorepositorio.findAll());// findAll chama todos os dados do banco
		return mv;

	}
	
	@GetMapping("/alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Integer id){
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Aluno/alterar");
		Aluno aluno = alunorepositorio.getOne(id);
		mv.addObject("aluno",aluno);
		return mv;
		
	}
	
	@PostMapping("/alterar")
	public ModelAndView alterar(Aluno aluno){
		ModelAndView mv = new ModelAndView();
		alunorepositorio.save(aluno);
		mv.setViewName("redirect:/alunos-adicionados");
		return mv;
	}
	@GetMapping("/excluir/{id}")
	public String excluirAluno(@PathVariable("id") Integer id){
		alunorepositorio.deleteById(id);
		return "redirect:/alunos-adicionados";
		
	}
	@GetMapping("filtro-alunos")
	public ModelAndView filtroAlunos(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Aluno/filtroAlunos");
		return mv;
	}
	@GetMapping("alunos-ativos")
	public ModelAndView listaAlunosAtivos() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Aluno/alunos-ativos");// caminho da pagina alunos-ativos
		mv.addObject("alunosAtivos", alunorepositorio.findByStatusAtivos());// findAll chama todos os dados do banco
		return mv;

	}
	@GetMapping("alunos-inativos")
	public ModelAndView listaAlunosInativos() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Aluno/alunos-inativos");
		mv.addObject("alunosInativos", alunorepositorio.findByStatusInativos());// chama no for each da view alunos-ativos
		return mv;

	}
	@GetMapping("alunos-trancados")
	public ModelAndView listaAlunosTrancados() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Aluno/alunos-trancados");
		mv.addObject("alunosInativos", alunorepositorio.findByStatusTrancados());// chama no for each da view alunos-ativos
		return mv;

	}
	@GetMapping("alunos-cancelados")
	public ModelAndView listaAlunosCancelados() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Aluno/alunos-cancelados");
		mv.addObject("alunosInativos", alunorepositorio.findByStatusCancelados());// chama no for each da view alunos-ativos
		return mv;

	}
}