package br.com.academy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.academy.model.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {
	//METODOS QUE SERÃO CHAMADOS NO SERVICE
	@Query("select i from Usuario i where i.email = :email")//usando jpql aula 18
	public Usuario findByEmail(String email);// por conta do paramentro string email - tem q colocar o :email na query JPQL
	
	@Query("select s from Usuario s where s.user = :user and s.senha= :senha")
	public Usuario buscarLogin(String user, String senha);
}
