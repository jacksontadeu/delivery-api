package com.jtmjinfo.delivery_api.entity.dto.Response;

import com.jtmjinfo.delivery_api.entity.model.Produto;

import java.math.BigDecimal;

public record ProdutoResponseDTO(Long id,
                                 String nome,
                                 String descricao,
                                 BigDecimal preco,
                                 Boolean disponvel){}



