package com.jtmjinfo.delivery_api.controller;

import com.jtmjinfo.delivery_api.entity.dto.Request.PedidoRequestDTO;
import com.jtmjinfo.delivery_api.entity.dto.Response.ItemPedidoResponseDTO;
import com.jtmjinfo.delivery_api.entity.dto.Response.PedidoResponseDTO;
import com.jtmjinfo.delivery_api.entity.enums.StatusPedido;
import com.jtmjinfo.delivery_api.entity.model.*;
import com.jtmjinfo.delivery_api.service.ClienteService;
import com.jtmjinfo.delivery_api.service.PedidoService;
import com.jtmjinfo.delivery_api.service.ProdutoService;
import com.jtmjinfo.delivery_api.service.RestauranteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedido")
@RequiredArgsConstructor
@Tag(name = "Pedido", description = "API REST para Pedido")
@Slf4j
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private RestauranteService restauranteService;
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/criarpedido")
    @Operation(summary = "Cadastrar um novo pedido")
    public ResponseEntity<PedidoResponseDTO> cadastrarPedido(@RequestBody PedidoRequestDTO request) {
        log.info("Cadastro do pedido ID: {}", request.clienteId());
        Cliente cliente = clienteService.buscarClientePorId(request.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Restaurante restaurante = restauranteService.buscarRestaurantePorId(request.restauranteId())
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        List<ItemPedido> itensPedido = request.itens().stream().map(item -> {
            Produto produto = produtoService.buscarProdutoPorId(item.produtoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            return ItemPedido.builder()
                    .produto(produto)
                    .quantidade(item.quantidade())
                    .precoUnitario(produto.getPreco())
                    .build();
        }).collect(Collectors.toList());


        BigDecimal total = itensPedido.stream().map(i ->
                        i.getPrecoUnitario().multiply(BigDecimal.valueOf(i.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Pedido pedido = Pedido.builder()
                .cliente(cliente)
                .restaurante(restaurante)
                .valorTotal(total)
                .enderecoEntrega(request.enderecoEntrega())
                .dataPedido(java.time.LocalDateTime.now())
                .status(StatusPedido.CRIADO)
                .itens(itensPedido)
                .build();
        Pedido salvo = pedidoService.criarPedido(pedido);
        List<ItemPedidoResponseDTO> itensResp = salvo.getItens().stream()
                .map(i -> new ItemPedidoResponseDTO(i.getProduto().getId(), i.getProduto().getNome(), i.getQuantidade(), i.getPrecoUnitario()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new PedidoResponseDTO(
                salvo.getId(),
                cliente.getId(),
                restaurante.getId(),
                salvo.getEnderecoEntrega(),
                salvo.getValorTotal(),
                salvo.getStatus(),
                salvo.getDataPedido(),
                itensResp
        ));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar um pedido pelo seu ID")
    public ResponseEntity<PedidoResponseDTO> buscarPorId(@PathVariable Long id) {
       log.info("Consulta do pedido ID: {}", id);
        Optional<Pedido> pedidoEntidade = pedidoService.buscarPedidoPorId(id);
        if (pedidoEntidade.isEmpty())
            return ResponseEntity.notFound().build();
        else {
            Pedido pedido = pedidoEntidade.get();
            List<ItemPedidoResponseDTO> itensResp = pedido.getItens().stream()
                    .map(i -> new ItemPedidoResponseDTO(i.getProduto().getId(), i.getProduto().getNome(), i.getQuantidade(), i.getPrecoUnitario()))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(new PedidoResponseDTO(
                    pedido.getId(),
                    pedido.getCliente().getId(),
                    pedido.getRestaurante().getId(),
                    pedido.getEnderecoEntrega(),
                    pedido.getValorTotal(),
                    pedido.getStatus(),
                    pedido.getDataPedido(),
                    itensResp
            ));
        }

    }

    @GetMapping("/listarpedidos/{clienteId}")
    @Operation(summary = "Listar todos os pedidos cadastrados pelo cliente")
    public List<Pedido> listarPedidoPorCliente(@PathVariable Long clienteId) {
        List<Pedido> pedidos = pedidoService.listarPedidos(clienteId);
        return pedidos;

    }
}
