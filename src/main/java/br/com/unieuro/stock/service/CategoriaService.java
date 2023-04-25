package br.com.unieuro.stock.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.unieuro.stock.domain.Categoria;
import br.com.unieuro.stock.repositories.CategoriaRepository;
import br.com.unieuro.stock.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	private final CategoriaRepository repository; // Importando o repository

	@Autowired
	public CategoriaService(CategoriaRepository repository) { // Construtor do repository responsavel pelo CRUD
		this.repository = repository;
	}

	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String directions) {// pagição categoria findPage busca paginada																																																																										
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(directions), orderBy); // retorna um page request com os parametros passado																																																	
		return repository.findAll(pageRequest);
	}

	public Categoria findById(Long id) {// Metodo buscar por ID
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ID: " + " , Tipo: " + Categoria.class.getName())); // orElseThrow Fazendo um tratamento de excessão do erro do id
																							
	}
}
