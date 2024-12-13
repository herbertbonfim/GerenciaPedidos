package com.gerenciapedidos.herbert.dto;

import com.gerenciapedidos.herbert.enums.EnumStatus;

import jakarta.validation.constraints.NotNull;

public record AtualizaPedidoDTO(
		@NotNull
		Long id,
		EnumStatus status) {

}
