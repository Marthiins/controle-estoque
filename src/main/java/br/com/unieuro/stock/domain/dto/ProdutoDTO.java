package br.com.unieuro.stock.domain.dto;

import java.io.Serializable;

import br.com.unieuro.stock.domain.Categoria;
import br.com.unieuro.stock.domain.Produto;

public class ProdutoDTO implements Serializable{ //DTO que representa um Categoria no sistema.
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private Double preco;
	private Integer quantidade;
	
	private Categoria categoria;

	public ProdutoDTO() {

	}

	public ProdutoDTO(Produto obj) { // Polimorfismo esse aqui vai ser o Objeto dos produtos
		id = obj.getId();
		nome = obj.getNome();
		preco = obj.getPreco();
		quantidade = obj.getQuantidade();
		categoria = obj.getCategoria();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	
}
