package br.com.unieuro.stock.service.exceptions;

public class ObjectNotFoundException extends RuntimeException {//Exception quando não for encontrado.
	private static final long serialVersionUID = 1L; 
	
	public ObjectNotFoundException(String msg) {
		super(msg);	
	}
	
	public ObjectNotFoundException(String msg, Throwable cause) { //Sobreescrevendo a classe com um metodo
		super(msg, cause);	
	}

}
