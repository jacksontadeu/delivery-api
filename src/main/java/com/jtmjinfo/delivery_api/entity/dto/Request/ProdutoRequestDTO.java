package com.jtmjinfo.delivery_api.entity.dto.Request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;


public record ProdutoRequestDTO(
        @NotBlank
        String nome,


        @NotBlank
        String descricao,

        @DecimalMin("0.1")
        @DecimalMax("500.0")
        BigDecimal preco,

        Boolean disponivel,

        Long restauranteId) {
}
