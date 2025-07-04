package com.deliverytech.delivery_api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deliverytech.delivery_api.entity.DTOs.PedidoDTO;
import com.deliverytech.delivery_api.entity.enums.StatusPedido;
import com.deliverytech.delivery_api.entity.model.Cliente;
import com.deliverytech.delivery_api.entity.model.ItemPedido;
import com.deliverytech.delivery_api.entity.model.Pedido;
import com.deliverytech.delivery_api.entity.model.Produto;
import com.deliverytech.delivery_api.entity.model.Restaurante;
import com.deliverytech.delivery_api.repository.ClienteRepository;
import com.deliverytech.delivery_api.repository.PedidoRepository;
import com.deliverytech.delivery_api.repository.ProdutoRepository;
import com.deliverytech.delivery_api.repository.RestauranteRepository;



@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Criar novo pedido
     */
    public Pedido criarPedido(PedidoDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
            .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado: " + dto.getClienteId()));

        Restaurante restaurante = restauranteRepository.findById(dto.getRestauranteId())
            .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado: " + dto.getRestauranteId()));

        if (!cliente.isAtivo()) {
            throw new IllegalArgumentException("Cliente inativo não pode fazer pedidos");
        }

        if (!restaurante.isAtivo()) {
            throw new IllegalArgumentException("Restaurante não está disponível");
        }

        Pedido pedido = new Pedido();
        pedido.setClienteId(cliente.getId());
        pedido.setRestaurante(restaurante);
        pedido.setStatus(StatusPedido.PENDENTE);
        pedido.setDataPedido(dto.getDataPedido());
        pedido.setNumeroPedido(dto.getNumeroPedido());
        pedido.setValorTotal(dto.getValorTotal());
        pedido.setObservacoes(dto.getObservacoes());
        pedido.setItens(dto.getItens());

        return pedidoRepository.save(pedido);
    }

    /**
     * Listar pedidos por cliente
     */
    @Transactional(readOnly = true)
    public List<Pedido> listarPorCliente(Long clienteId) {
        return pedidoRepository.findByClienteIdOrderByDataPedidoDesc(clienteId);
    }

    /**
     * Atualizar status do pedido
     */
    public Pedido atualizarStatus(Long pedidoId, StatusPedido status) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
            .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado: " + pedidoId));

        if (pedido.getStatus().equals(StatusPedido.ENTREGUE.name())) {
            throw new IllegalArgumentException("Pedido já finalizado: " + pedidoId);
        }

        pedido.setStatus(status));
        return pedidoRepository.save(pedido);
    }
    // Pedidos por cliente
    public List<Pedido> buscarPedidosPorCliente(Long clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }
    // listar por status
    public List<Pedido> listarPorStatus(StatusPedido status) {
        return pedidoRepository.findByStatus(status);
    }
    // Listar os 10 pedidos mais recentes
    public List<Pedido> listarRecentes() {
        return pedidoRepository.findTop10ByOrderByDataPedidoDesc();
    }
    /**
     * Listar pedidos por período
     */
    public List<Pedido> listarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return pedidoRepository.findByDataPedidoBetween(inicio, fim);
    }
}