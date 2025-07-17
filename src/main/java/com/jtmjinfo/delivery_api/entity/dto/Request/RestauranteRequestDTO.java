package com.jtmjinfo.delivery_api.entity.dto.Request;

import com.jtmjinfo.delivery_api.entity.model.Restaurante;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record RestauranteRequestDTO(
        @NotBlank
        String nome,
        @NotBlank
        String categoria,
        @DecimalMin("0.0")
        BigDecimal taxaEntrega,
        @Min(10)
        @Max(120)
        Integer tempoEntrega,
        Boolean ativo
) {}
