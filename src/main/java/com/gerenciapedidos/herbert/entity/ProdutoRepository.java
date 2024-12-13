package com.gerenciapedidos.herbert.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	List<Produto> findByCodigo(Long codigo);

}
