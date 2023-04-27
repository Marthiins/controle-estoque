package br.com.unieuro.stock.service.exceptions;

public class DataIntegrityViolation extends RuntimeException {//Exception quando não for encontrado.
	private static final long serialVersionUID = 1L; 
	
	public DataIntegrityViolation(String msg) {
		super(msg);	
	}
	
	public DataIntegrityViolation(String msg, Throwable cause) { //Sobreescrevendo a classe com um metodo
		super(msg, cause);	
	}
}
