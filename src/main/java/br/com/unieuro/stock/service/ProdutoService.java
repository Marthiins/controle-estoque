package br.com.unieuro.stock.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.unieuro.stock.domain.Produto;
import br.com.unieuro.stock.domain.dto.ProdutoDTO;
import br.com.unieuro.stock.repositories.ProdutoRepository;
import br.com.unieuro.stock.service.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	private final ProdutoRepository repository; // Importando o repository

	@Autowired
	public ProdutoService(ProdutoRepository repository) { //Construtor de ProdutoService
		this.repository = repository;
	}

	public Page<Produto> findPage(Integer page, Integer linesPerPage, String orderBy, String directions) {// pagição Produto findPage busca paginada																																																																										
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(directions), orderBy); // retorna um page request com os parametros passado																																																	
		return repository.findAll(pageRequest);
	}

	public Produto findById(Long id) {// Metodo buscar por ID
		Optional<Produto> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ID: " + " , Tipo: " + Produto.class.getName())); // orElseThrow Fazendo um tratamento de excessão do erro do id
																							
	}
	
	public List<ProdutoDTO> findByName(String nome) {
		List<ProdutoDTO> produtos = repository.findByNomeLike(nome);
		return produtos;
	}
	
	public Produto update (Produto obj) {
		Produto newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}
	
	private void updateData(Produto newObj, Produto obj) { //Alteração do produto
		newObj.setNome(obj.getNome());
		newObj.setPreco(obj.getPreco());
		newObj.setQuantidade(obj.getQuantidade());
	}
	
	public Produto fromDTO (ProdutoDTO objDto) { //Transformando uma Produto em DTO, e criando um novo produto
		return new Produto(objDto.getId(), objDto.getNome() , objDto.getPreco(), objDto.getQuantidade(), objDto.getCategoria());
		
	}
	
	public Produto insert(Produto obj) {
		obj.setId(null); //Devido ter criado o banco de dados passando o autoincremento
		return repository.save(obj);
	}
	
	public void delete (Long id) {//void porque o delete não retorna a nada
	    findById(id);
	    repository.deleteById(id);
	
	}
}
