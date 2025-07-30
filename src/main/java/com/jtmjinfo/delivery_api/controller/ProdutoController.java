package com.jtmjinfo.delivery_api.controller;

import com.jtmjinfo.delivery_api.entity.dto.Request.ProdutoRequestDTO;
import com.jtmjinfo.delivery_api.entity.dto.Response.ProdutoResponseDTO;
import com.jtmjinfo.delivery_api.entity.model.Produto;
import com.jtmjinfo.delivery_api.entity.model.Restaurante;
import com.jtmjinfo.delivery_api.service.ProdutoService;
import com.jtmjinfo.delivery_api.service.RestauranteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produto")
@Tag(name = "Produto", description = "API REST para Produto")
@Slf4j
public class ProdutoController {


    public ProdutoController(ProdutoService produtoService,
                             RestauranteService restauranteService) {
        this.produtoService = produtoService;
        this.restauranteService = restauranteService;
    }

    private ProdutoService produtoService;
    private RestauranteService restauranteService;

    @PostMapping("/cadastrar")
    @Operation(summary = "Cadastrar um novo produto")
    public ResponseEntity<ProdutoResponseDTO> cadastrarProduto(
            @RequestBody ProdutoRequestDTO request) {
        log.info("Cadastro do produto: {}", request.nome());
        Restaurante restaurante = restauranteService.
                buscarRestaurantePorId(request.restauranteId()).orElseThrow(()->
                        new RuntimeException("Produto não encontrado"));

        Produto produto = Produto.builder()
                .nome(request.nome())
                .descricao(request.descricao())
                .preco(request.preco())
                .disponivel(request.disponivel())
                .restaurante(restaurante)
                .build();
        Produto produtoSalvo = produtoService.cadstrarProduto(produto);
        return ResponseEntity.ok(new ProdutoResponseDTO(
                produtoSalvo.getId(), produtoSalvo.getNome(), produtoSalvo.getDescricao(),
                produtoSalvo.getPreco(),produtoSalvo.getDisponivel()));
    }

}
