package com.deliverytech.delivery_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deliverytech.delivery_api.entity.enums.StatusPedido;
import com.deliverytech.delivery_api.entity.model.Pedido;
import com.deliverytech.delivery_api.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    /**
         * Criar novo pedido
         */
@PostMapping
public ResponseEntity<?> criarPedido(@RequestParam Long clienteId,
@RequestParam Long restauranteId) {
try {
Pedido pedido = pedidoService.criarPedido(clienteId, restauranteId);
return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
} catch (IllegalArgumentExcep􀆟on e) {
return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
} catch (Excep􀆟on e) {
return ResponseEn􀆟ty.status(H􀆩pStatus.INTERNAL_SERVER_ERROR)
.body("Erro interno do servidor");
}
}

    /**
     * Adicionar item ao pedido
     */
@PostMapping("/{pedidoId}/itens")
public ResponseEn􀆟ty<?> adicionarItem(@PathVariable Long pedidoId,
@RequestParam Long produtoId,
@RequestParam Integer quan􀆟dade) {
try {
Pedido pedido = pedidoService.adicionarItem(pedidoId, produtoId, quan􀆟dade);
return ResponseEn􀆟ty.ok(pedido);
} catch (IllegalArgumentExcep􀆟on e) {
return ResponseEn􀆟ty.badRequest().body("Erro: " + e.getMessage());
} catch (Excep􀆟on e) {
return ResponseEn􀆟ty.status(H􀆩pStatus.INTERNAL_SERVER_ERROR)
.body("Erro interno do servidor");
}
}
/**
* Confirmar pedido
*/
@PutMapping("/{pedidoId}/confirmar")
public ResponseEn􀆟ty<?> confirmarPedido(@PathVariable Long pedidoId) {
try {
Pedido pedido = pedidoService.confirmarPedido(pedidoId);
return ResponseEn􀆟ty.ok(pedido);
} catch (IllegalArgumentExcep􀆟on e) {
return ResponseEn􀆟ty.badRequest().body("Erro: " + e.getMessage());
} catch (Excep􀆟on e) {
return ResponseEn􀆟ty.status(H􀆩pStatus.INTERNAL_SERVER_ERROR)
.body("Erro interno do servidor");
}
}

    /**
     * Buscar pedido por ID
     */
@GetMapping("/{id}")
public ResponseEn􀆟ty<?> buscarPorId(@PathVariable Long id) {
Op􀆟onal<Pedido> pedido = pedidoService.buscarPorId(id);
if (pedido.isPresent()) {
return ResponseEn􀆟ty.ok(pedido.get());
} else {
return ResponseEn􀆟ty.notFound().build();
}
}

    /**
     * Listar pedidos por cliente
     */
@GetMapping("/cliente/{clienteId}")
public ResponseEn􀆟ty<List<Pedido>> listarPorCliente(@PathVariable Long clienteId) {
List<Pedido> pedidos = pedidoService.listarPorCliente(clienteId);
return ResponseEn􀆟ty.ok(pedidos);
}

    /**
     * Buscar pedido por número
     */
@GetMapping("/numero/{numeroPedido}")
public ResponseEn􀆟ty<?> buscarPorNumero(@PathVariable String numeroPedido) {
Op􀆟onal<Pedido> pedido = pedidoService.buscarPorNumero(numeroPedido);
if (pedido.isPresent()) {
return ResponseEn􀆟ty.ok(pedido.get());
} else {
return ResponseEn􀆟ty.notFound().build();
}
}

    /**
     * Atualizar status do pedido
     */
@PutMapping("/{pedidoId}/status")
public ResponseEn􀆟ty<?> atualizarStatus(@PathVariable Long pedidoId,
@RequestParam StatusPedido status) {
try {
Pedido pedido = pedidoService.atualizarStatus(pedidoId, status);
return ResponseEn􀆟ty.ok(pedido);
} catch (IllegalArgumentExcep􀆟on e) {
return ResponseEn􀆟ty.badRequest().body("Erro: " + e.getMessage());
} catch (Excep􀆟on e) {
return ResponseEn􀆟ty.status(H􀆩pStatus.INTERNAL_SERVER_ERROR)
.body("Erro interno do servidor");
}
}

    /**
     * Cancelar pedido
     */
@PutMapping("/{pedidoId}/cancelar")
public ResponseEn􀆟ty<?> cancelarPedido(@PathVariable Long pedidoId,
@RequestParam(required = false) String mo􀆟vo) {
try {
Pedido pedido = pedidoService.cancelarPedido(pedidoId, mo􀆟vo);
return ResponseEn􀆟ty.ok(pedido);
} catch (IllegalArgumentExcep􀆟on e) {
return ResponseEn􀆟ty.badRequest().body("Erro: " + e.getMessage());
} catch (Excep􀆟on e) {
return ResponseEn􀆟ty.status(H􀆩pStatus.INTERNAL_SERVER_ERROR)
.body("Erro interno do servidor");
}
}
}􁇤􁇥􁇦􁇧􁇨

}
