package br.com.academy.enums;

public enum Curso {

	ADMINISTRACAO("Administração"),
	INFORMATICA("Informática"),
	CONTABILIDADE("Contabilidade"),
	PEDAGOGIA("Pedagogia"),
	ENFERMAGEM("Enfermagem");
	
	private String curso;///
	
//construtor do Enum - para ser chamado no model Classe Aluno
	private Curso(String curso){
		this.curso = curso;
	}
	
}
