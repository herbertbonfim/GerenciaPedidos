package com.gerenciapedidos.herbert.entity;

import com.gerenciapedidos.herbert.dto.EnderecoDTO;

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

@Table(name = "Endereco")
@Entity(name = "enderecos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Endereco {
	
	public Endereco(EnderecoDTO enderecoDTO) {
		// TODO Auto-generated constructor stub
		this.cep = enderecoDTO.cep();
		this.rua = enderecoDTO.rua();
		this.bairro = enderecoDTO.bairro();
		this.cidade = enderecoDTO.cidade();
		this.uf = enderecoDTO.uf();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cep;
	private String rua;
	private String bairro;
	private String cidade;
	private String uf;

}
