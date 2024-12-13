package com.gerenciapedidos.herbert.dto;

import java.time.LocalDate;
import java.util.List;

import com.gerenciapedidos.herbert.entity.Cliente;
import com.gerenciapedidos.herbert.entity.Produto;
import com.gerenciapedidos.herbert.enums.EnumStatus;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public record InserePedidoDTO(
		@NotNull
		Long codigo,

		LocalDate emissao,
		
		@NotNull
		Cliente cliente,
		
		@Enumerated
		EnumStatus status,
		
		List<Produto> produtos,
		
		@NotNull
		double valorProdutos,
		
		@NotNull
		double valorTotal) {	

}