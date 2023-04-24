package br.com.unieuro.stock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.unieuro.stock.domain.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{ //Long por causa do Id

}
