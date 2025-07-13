package com.jtmjinfo.delivery_api.repository;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jtmjinfo.delivery_api.entity.enums.StatusPedido;

import com.jtmjinfo.delivery_api.entity.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByClienteId(Long clienteId);
    List<Pedido> findByRestauranteId(Long restauranteId);
    List<Pedido> findByStatus(StatusPedido status);
    List<Pedido> findByDataPedidoBetween(LocalDateTime inicio, LocalDateTime fim);

    //      // Buscar pedidos por cliente ID
//    List<Pedido> findByClienteIdOrderByDataPedidoDesc(Long clienteId);
//
//    // Pedidos por cliente
//    List<Pedido> findByClienteId(Long clienteId);
//
//    // Pedidos por status
//    List<Pedido> findByStatus(StatusPedido status);
//
//    // 10 pedidos mais recentes
//    List<Pedido> findTop10ByOrderByDataPedidoDesc();
//
//    // Pedidos por período
//    List<Pedido> findByDataPedidoBetween(LocalDateTime inicio, LocalDateTime fim);


}
