package com.gerenciapedidos.herbert.entity;

import com.gerenciapedidos.herbert.dto.ProdutoDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Produto")
@Entity(name = "produtos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {
	
	public Produto(ProdutoDTO produtoDTO) {
		// TODO Auto-generated constructor stub
		this.codigo = produtoDTO.codigo();
		this.nome = produtoDTO.nome();
		this.valor = produtoDTO.valor();
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long codigo;
	private String nome;
	private double valor;

}
