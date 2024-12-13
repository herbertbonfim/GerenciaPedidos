package com.gerenciapedidos.herbert.entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	List<Pedido> findAllByAtivoTrue();
	
	List<Pedido> findByEmissaoBetween(LocalDate dtInicio, LocalDate dtFim, Pageable page);
}
