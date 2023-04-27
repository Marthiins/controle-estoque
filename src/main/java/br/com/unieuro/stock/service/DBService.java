package br.com.unieuro.stock.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unieuro.stock.domain.Categoria;
import br.com.unieuro.stock.domain.Produto;
import br.com.unieuro.stock.repositories.CategoriaRepository;
import br.com.unieuro.stock.repositories.ProdutoRepository;

@Service
public class DBService {
	
	private final CategoriaRepository categoriaRepository;
	
	private final ProdutoRepository produtoRepository; //Buscar o Repositorio de produtos
	
	@Autowired
	public DBService(CategoriaRepository categoriaRepository , ProdutoRepository produtoRepository) { //construtor DBservice com os parametros de categoria e produtos 
		this.categoriaRepository = categoriaRepository;
		this.produtoRepository = produtoRepository;
	}
	
	public void instantiateDatabase() {
		
		Categoria cat1 = new Categoria(null , "Ferramentas");
		Categoria cat2 = new Categoria(null , "Informatica");
		Categoria cat3 = new Categoria(null , "Eletronicos");
		
		
		Produto p1 = new Produto(null, "furadeira", 250.55, 10, cat1);
		Produto p2 = new Produto(null, "maquita", 351.55, 10, cat1);
		Produto p3 = new Produto(null, "arco de serra", 35.00, 10, cat1);
		Produto p4 = new Produto(null, "martelo", 37.55, 10, cat1);
		Produto p5 = new Produto(null, "impressora", 900.55, 10, cat2);
		Produto p6 = new Produto(null, "mouse", 99.99, 9, cat2);
		Produto p7 = new Produto(null, "teclado", 50.55, 10, cat2);
		Produto p8 = new Produto(null, "cabiomputador", 2470.55, 10, cat2);
		Produto p9 = new Produto(null, "fone de ouvido", 60.55, 10, cat3);
		Produto p10 = new Produto(null, "lampada de mesa", 49.99, 10, cat3);
		Produto p11 = new Produto(null, "calculadora", 35.99, 10, cat3);
		Produto p12= new Produto(null, "despertadore", 26.89, 10, cat3);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3 ,p4));
		cat2.getProdutos().addAll(Arrays.asList(p5, p6, p7 ,p8));
		cat3.getProdutos().addAll(Arrays.asList(p9, p10, p11 ,p12));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3 ,p4 , p5, p6, p7 ,p8 , p9, p10, p11 ,p12));//Salvando os produtos
	}

}
