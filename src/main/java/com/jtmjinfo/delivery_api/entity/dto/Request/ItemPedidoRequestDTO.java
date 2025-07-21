package com.jtmjinfo.delivery_api.entity.dto.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemPedidoRequestDTO(
        @NotNull
        Long produtoId,

        @Positive
        Integer quantidade
) {}
