package br.com.academy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.academy.enums.Curso;
import br.com.academy.enums.Status;

@Entity
public class Aluno {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="nome_aluno")//anotação do JPA
	@Size(min = 5, max = 35, message = "Preencher nome e sobrenome")
	@NotBlank(message = "preenchimento obrigatório")
	private String nome;
	
	@Column(name="matricula_aluno")
	@NotNull(message = "Clique no botão gerar")//CHAMA AS VALIDAÇÕES NO formAluno.html
	@Size(min = 3, message ="Clique no botão gerar")
	private String matricula;
	
	@Column(name="turno")
	@NotEmpty(message = "Name may not be empty")
	//@NotBlank(message = "preenchimento obrigatório")
	@Size(min = 4, message = "No mínimo 4 caracteres")
	private String turno;
	
	@Column(name="curso")
	@Enumerated(EnumType.STRING)
	@NotNull(message = "preenchimento obrigatório")//importar do javax.Validation
	private Curso curso;
	
	@Column(name="status")
	@Enumerated(EnumType.STRING)
	@NotNull(message = "preenchimento obrigatório")
	private Status status;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
