package com.gerenciapedidos.herbert.dto;

import java.time.LocalDate;
import java.util.List;

import com.gerenciapedidos.herbert.entity.Cliente;
import com.gerenciapedidos.herbert.entity.ItemPedido;
import com.gerenciapedidos.herbert.entity.Pedido;
import com.gerenciapedidos.herbert.enums.EnumStatus;

public record ListaPedidoDTO(
		Long id,
		Long codigo, 
		LocalDate emissao,
		Cliente cliente,
		List<ItemPedido> itens,
		EnumStatus status,
		double valorProdutos,
		double valorTotal) {
	
	public ListaPedidoDTO(Pedido pedido) {
		this(pedido.getId(), pedido.getCodigo(), pedido.getEmissao(), pedido.getCliente(), pedido.getItens(), pedido.getStatus(), pedido.getValor_produtos(), pedido.getValor_total());
	}

}
