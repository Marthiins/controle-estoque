package br.com.unieuro.stock.domain.dto;

import java.io.Serializable;

import br.com.unieuro.stock.domain.Categoria;

public class CategoriaDTO implements Serializable{ //DTO que representa um Categoria no sistema.
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	
	public CategoriaDTO() {
		}
	
	public CategoriaDTO(Categoria obj) { //Polimorfismo
		id = obj.getId();
		name = obj.getName();			
	}

	public CategoriaDTO(Long id, String name) { //Construtor
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
