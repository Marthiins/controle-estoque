package br.com.unieuro.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.unieuro.stock.domain.Categoria;
import br.com.unieuro.stock.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	private final CategoriaRepository repository; //Importando o repository
	
	@Autowired
	public CategoriaService(CategoriaRepository repository) { //Construtor do repository
		this.repository = repository;
	}


	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy , String directions){//pagição categoria findPage busca paginada
	    PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(directions), orderBy); //retorna um page request com os parametros passado
	    return repository.findAll(pageRequest);
	}
}
