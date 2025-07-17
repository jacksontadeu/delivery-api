package com.jtmjinfo.delivery_api.entity.dto.Response;

import com.jtmjinfo.delivery_api.entity.model.Restaurante;

import java.math.BigDecimal;

public record RestauranteResponseDTO(
        Long id,
        String nome, String categoria,
        Integer tempoEntrega, BigDecimal taxaEntrega,
        Boolean ativo) {
}
