package br.com.academy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.academy.model.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {

	@Query("select i from Usuario i where i.email = :email")//usando jpql aula 18
	public Usuario findByEmail(String email);// por conta do paramentro string email - tem q colocar o :email na query JPQL
	
	
}
