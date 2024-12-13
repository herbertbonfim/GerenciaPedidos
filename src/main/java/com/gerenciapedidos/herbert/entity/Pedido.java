package com.gerenciapedidos.herbert.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.gerenciapedidos.herbert.dto.AtualizaPedidoDTO;
import com.gerenciapedidos.herbert.dto.InserePedidoDTO;
import com.gerenciapedidos.herbert.enums.EnumStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Pedido")
@Entity(name = "pedidos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pedido {
	
	public Pedido(InserePedidoDTO pedidoDTO) {
		// TODO Auto-generated constructor stub
		this.ativo = true;
		this.codigo = pedidoDTO.codigo();
		this.emissao = pedidoDTO.emissao();
		this.cliente = pedidoDTO.cliente();
		this.status = pedidoDTO.status();
		this.valor_produtos = pedidoDTO.valorProdutos();
		this.valor_total = pedidoDTO.valorTotal();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private Long codigo;
	private LocalDate emissao;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cliente.id")
	private Cliente cliente;
	
	@OneToMany(cascade = CascadeType.ALL)
    private List<ItemPedido> itens = new ArrayList<>();
	
	@Enumerated(EnumType.STRING)
	private EnumStatus status;
	
	private double valor_produtos;
	private double valor_total;
	private boolean ativo;

	public void alterar(@Valid AtualizaPedidoDTO atualizaPedidoDTO) {
		// TODO Auto-generated method stub
		if(atualizaPedidoDTO.status() != null) {
			this.status = atualizaPedidoDTO.status();
		}
		
	}
	
	public void ativar() {
		// TODO Auto-generated method stub
		this.ativo = true;
	}

	public void inativar() {
		// TODO Auto-generated method stub
		this.ativo = false;		
	}

    public void adicionarItem(Produto produto, int sequencia, Double valor) {
        ItemPedido item = new ItemPedido(produto, sequencia, valor);
        itens.add(item);
    }

    public void removerItem(ItemPedido item) {
        itens.remove(item);
    }

}
