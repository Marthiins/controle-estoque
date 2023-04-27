package br.com.unieuro.stock.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.unieuro.stock.domain.Produto;
import br.com.unieuro.stock.domain.dto.ProdutoDTO;
import br.com.unieuro.stock.service.ProdutoService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	private final ProdutoService service; //Importando a classe de ProdutoService

	@Autowired
	public ProdutoController(ProdutoService service) {
		this.service = service;
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value = "page" , defaultValue = "0")Integer page,
			@RequestParam(value = "linesPerPage" , defaultValue = "24")Integer linesPerPage,
			@RequestParam(value = "directions" , defaultValue = "ASC")String directions,
			@RequestParam(value = "orderBy" , defaultValue = "nome") String nome){
		
		Page<Produto> list = service.findPage(page, linesPerPage, nome, directions);
		
		Page<ProdutoDTO> listDTO = list.map(obj -> new ProdutoDTO(obj));//conversão passando essa Produto para ProdutoDTO
		
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> findById (@PathVariable Long id) { // Encontra uma Produto no banco por ID.
		Produto obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ProdutoDTO objDto, @PathVariable Long id) {
		Produto obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.POST)//verbo HTTP
	public ResponseEntity<Void> insert (@Valid @RequestBody ProdutoDTO objDto){ //Inserir uma Produto
		Produto obj = service.fromDTO(objDto); //Passando os objetos do FromDTO
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest() //Responsavel por trazer o caminho do produto http://localhost:8081/produto-server/Produtos
				.path("/{id}").buildAndExpand(obj.getId()).toUri(); //dentro do path vamos pegar o id que foi criado
		return ResponseEntity.created(uri).build();
	}
	
	
	// Metodo para deletar uma Produto é quase igual o findById
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE) //Http do rest
	public ResponseEntity<Void> delete (@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();// Retornar no jason no content status 204
		
	}
}
