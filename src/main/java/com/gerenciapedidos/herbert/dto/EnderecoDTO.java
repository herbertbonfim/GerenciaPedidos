package com.gerenciapedidos.herbert.dto;

public record EnderecoDTO(
		String cep,
		String rua,
		String bairro,
		String cidade,
		String uf) {

}
