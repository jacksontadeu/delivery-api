package com.jtmjinfo.delivery_api.entity.dto.Request;

import com.jtmjinfo.delivery_api.entity.model.Endereco;
import jakarta.validation.constraints.NotNull;

import java.util.List;


public record PedidoRequestDTO(

        Long clienteId,


        Long restauranteId,


        Endereco enderecoEntrega,


        List<ItemPedidoRequestDTO> itens
) {

}
