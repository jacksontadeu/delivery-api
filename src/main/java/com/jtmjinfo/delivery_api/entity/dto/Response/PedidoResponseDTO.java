package com.jtmjinfo.delivery_api.entity.dto.Response;

import com.jtmjinfo.delivery_api.entity.enums.StatusPedido;
import com.jtmjinfo.delivery_api.entity.model.Endereco;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponseDTO<status>(
        Long id,
        Long clienteId,
        Long restauranteId,
        Endereco enderecoEntrega,
        BigDecimal valorTotal,
        StatusPedido status,
        LocalDateTime dataPedido,
        List<ItemPedidoResponseDTO> itens
) {
}
