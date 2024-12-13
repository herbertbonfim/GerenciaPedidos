package com.gerenciapedidos.herbert.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Item_Pedido")
@Entity(name = "itens_pedidos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedido {

	public ItemPedido(Produto produto, int sequencia, Double valor) {
		// TODO Auto-generated constructor stub
		this.produto = produto;
		this.sequencia = sequencia;
		this.valor = valor;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "produto.id")
	private Produto produto;
	
	private int sequencia;
	private double valor;
	
	
}
