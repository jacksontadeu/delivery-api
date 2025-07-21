package com.jtmjinfo.delivery_api.repository;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jtmjinfo.delivery_api.entity.enums.StatusPedido;

import com.jtmjinfo.delivery_api.entity.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByClienteId(Long clienteId);
//    List<Pedido> findByRestauranteId(Long restauranteId);
//    List<Pedido> findByStatus(StatusPedido status);
//    List<Pedido> findByDataPedidoBetween(LocalDateTime inicio, LocalDateTime fim);

}
