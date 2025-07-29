package com.jtmjinfo.delivery_api.service;

import com.jtmjinfo.delivery_api.entity.enums.StatusPedido;
import com.jtmjinfo.delivery_api.entity.model.Pedido;
import com.jtmjinfo.delivery_api.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido criarPedido(Pedido pedido) {
        pedido.setStatus(StatusPedido.CRIADO);
        return pedidoRepository.save(pedido);
    }

    public Optional<Pedido> buscarPedidoPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public List<Pedido> listarPedidos(Long clienteId) {

        return pedidoRepository.findByClienteId(clienteId);
    }

//    public List<Pedido> listarPorRestaurante(Long restauranteId) {
//        return pedidoRepository.findByRestauranteId(restauranteId);
//    }

    public Pedido alterarStatusPedido(Long id, StatusPedido status) {
        return pedidoRepository.findById(id).
                map(p -> {
                    p.setStatus(status);
                    return pedidoRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public void cancelarPedido(Long id) {
        pedidoRepository.findById(id).
                map(p -> {
                    p.setStatus(StatusPedido.CANCELADO);
                    return pedidoRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

}
