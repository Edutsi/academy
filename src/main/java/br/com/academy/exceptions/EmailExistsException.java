package br.com.academy.exceptions;

public class EmailExistsException extends Exception {

	public EmailExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;//é a definição da classe -opcional, se eu não colocar o eclipse se encarrega
}
