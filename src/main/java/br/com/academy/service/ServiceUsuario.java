package br.com.academy.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.academy.dao.UsuarioDao;
import br.com.academy.exceptions.CriptoExistException;
import br.com.academy.exceptions.EmailExistsException;
import br.com.academy.exceptions.ServiceExc;
import br.com.academy.model.Usuario;
import br.com.academy.util.Util;
//As classes do pacote SERVICE recebem/implementam as regras de negócio
@Service
public class ServiceUsuario {

	@Autowired
	private UsuarioDao repositorioUsuario;
	
	
	public void salvarUsuario(Usuario user) throws Exception{
		try {
			
			if(repositorioUsuario.findByEmail(user.getEmail()) !=null) {
				throw new EmailExistsException("Já existe um email cadastrado para: " + user.getEmail());
			}
			user.setSenha(Util.md5(user.getSenha()));//aqui pegamos o que o user SETou na view
			//NoSuchAlgorithmException - é o responsavel pelas exceções da classe de criptografia MD5
		} catch (NoSuchAlgorithmException e) {
			throw new CriptoExistException("Erro na criptografia da senha");
		}
		//salvando o usuario
		repositorioUsuario.save(user);
	}
	///pesquisa por usuario
	public Usuario loginUser(String user, String senha) throws ServiceExc{
		Usuario userlogin = repositorioUsuario.buscarLogin(user, senha);
		return userlogin;
	}
		 
}