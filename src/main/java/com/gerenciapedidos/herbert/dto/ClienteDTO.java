package com.gerenciapedidos.herbert.dto;

import com.gerenciapedidos.herbert.entity.Endereco;

public record ClienteDTO(
		String nome,
		String email,
		Endereco endereco
		) {

}
