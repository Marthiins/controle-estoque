package br.com.unieuro.stock.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.unieuro.stock.service.exceptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice //é utilizada para lidar com exceções lançadas em qualquer lugar da sua aplicação, não só pelo controller.
public class ResourceExceptionHandler { //Manipular os recursos de excessões
	
	
	@ExceptionHandler(ObjectNotFoundException.class) //Nome da classe que foi criado
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){//Metodo
	    StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
	    		"Não encontrado", e.getMessage(), request.getRequestURI());
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

}
