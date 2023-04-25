package br.com.unieuro.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.unieuro.stock.domain.Categoria;
import br.com.unieuro.stock.domain.dto.CategoriaDTO;
import br.com.unieuro.stock.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	private final CategoriaService service; //Importando a classe de CategoriaService

	@Autowired
	public CategoriaController(CategoriaService service) {
		this.service = service;
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value = "page" , defaultValue = "0")Integer page,
			@RequestParam(value = "linesPerPage" , defaultValue = "24")Integer linesPerPage,
			@RequestParam(value = "directions" , defaultValue = "ASC")String directions,
			@RequestParam(value = "orderBy" , defaultValue = "name")String name){
		
		Page<Categoria> list = service.findPage(page, linesPerPage, name, directions);
		
		Page<CategoriaDTO> listDTO = list.map(obj -> new CategoriaDTO(obj));//conversão passando essa categoria para categoriaDTO
		
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> findById (@PathVariable Long id) { // Encontra uma categoria no banco por ID.
		Categoria obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
