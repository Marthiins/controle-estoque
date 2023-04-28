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

import br.com.unieuro.stock.domain.Categoria;
import br.com.unieuro.stock.domain.dto.CategoriaDTO;
import br.com.unieuro.stock.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Categorias Endpoint", description = "for Managing Categoria")
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	private final CategoriaService service; //Importando a classe de CategoriaService

	@Autowired
	public CategoriaController(CategoriaService service) {
		this.service = service;
	}
	
	@Operation(summary =  "FIND a specific categories")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value = "page" , defaultValue = "0")Integer page,
			@RequestParam(value = "linesPerPage" , defaultValue = "24")Integer linesPerPage,
			@RequestParam(value = "directions" , defaultValue = "ASC")String directions,
			@RequestParam(value = "orderBy" , defaultValue = "name") String name){
		
		Page<Categoria> list = service.findPage(page, linesPerPage, name, directions);
		
		Page<CategoriaDTO> listDTO = list.map(obj -> new CategoriaDTO(obj));//conversão passando essa categoria para categoriaDTO
		
		return ResponseEntity.ok().body(listDTO);
	}
	
	@Operation(summary =  "FIND a specific categories by id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> findById (@PathVariable Long id) { // Encontra uma categoria no banco por ID.
		Categoria obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@Operation(summary =  "PUT a specific categories by id")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDto, @PathVariable Long id) {
		Categoria obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary =  "POST a specific categories")
	@RequestMapping(method = RequestMethod.POST)//verbo HTTP
	public ResponseEntity<Void> insert (@Valid @RequestBody CategoriaDTO objDto){ //Inserir uma categoria
		Categoria obj = service.fromDTO(objDto); //Passando os objetos do FromDTO
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest() //Responsavel por trazer o caminho do produto http://localhost:8081/produto-server/categorias
				.path("/{id}").buildAndExpand(obj.getId()).toUri(); //dentro do path vamos pegar o id que foi criado
		return ResponseEntity.created(uri).build();
	}
	
	
	// Metodo para deletar uma categoria é quase igual o findById
	@Operation(summary =  "DELETE a specific categories")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE) //Http do rest
	public ResponseEntity<Void> delete (@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();// Retornar no jason no content status 204
		
	}
}
