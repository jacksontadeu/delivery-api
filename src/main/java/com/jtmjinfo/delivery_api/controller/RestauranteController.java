package com.jtmjinfo.delivery_api.controller;

import com.jtmjinfo.delivery_api.entity.dto.Request.RestauranteRequestDTO;
import com.jtmjinfo.delivery_api.entity.dto.Response.RestauranteResponseDTO;
import com.jtmjinfo.delivery_api.entity.model.Restaurante;
import com.jtmjinfo.delivery_api.service.RestauranteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurante")
@Tag(name = "Restaurante", description = "API REST para Restaurante")
@Slf4j
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @PostMapping("/cadastrar")
    @Operation(summary = "Cadastrar um novo restaurante")
    public ResponseEntity<RestauranteResponseDTO> cadastrarRestaurante(@RequestBody RestauranteRequestDTO request){
       log.info("Cadastro do restaurante: {}", request.nome());
        Restaurante restaurante = Restaurante.builder()
                .nome(request.nome())
                .categoria(request.categoria())
                .taxaEntrega(request.taxaEntrega())
                .tempoEntrega(request.tempoEntrega())
                .ativo(request.ativo())
                .build();

        Restaurante salvo =restauranteService.cadastrarRestaurante(restaurante);
        return ResponseEntity.ok(new RestauranteResponseDTO(
                salvo.getId(), salvo.getNome(),
                salvo.getCategoria(), salvo.getTempoEntrega(),
                salvo.getTaxaEntrega(), salvo.getAtivo()
        ));
    }
    @GetMapping("/{id}")
    @Operation(summary = "Buscar um restaurante pelo seu ID")
    public ResponseEntity<RestauranteResponseDTO> buscarPorId(@PathVariable Long id){
        log.info("Consulta do restaurante ID: {}", id);
        Optional<Restaurante> restauranteEntidade = restauranteService.buscarRestaurantePorId(id);
        if(restauranteEntidade.isEmpty())
            return ResponseEntity.notFound().build();
        else{
            Restaurante restaurante = restauranteEntidade.get();
            return ResponseEntity.ok(new RestauranteResponseDTO(
                    restaurante.getId(), restaurante.getNome(),
                    restaurante.getCategoria(),restaurante.getTempoEntrega(),
                    restaurante.getTaxaEntrega(), restaurante.getAtivo()));
        }
    }
    @GetMapping("/listartodos")
    @Operation(summary = "Listar todos os restaurantes cadastrados")
    public List<RestauranteResponseDTO> listarTodos(){
        List<RestauranteResponseDTO> restaurantes = restauranteService.listarRestaurantes()
                .stream().map(r -> new RestauranteResponseDTO(r.getId(),r.getNome(),
        r.getCategoria(),r.getTempoEntrega(),r.getTaxaEntrega(),r.getAtivo())).collect(Collectors.toList());
        return restaurantes;
    }
    @PutMapping("/{id}")
    @Operation(summary = "Alterar um restaurante pelo seu ID")
    public ResponseEntity<RestauranteResponseDTO> alterarREstaurante(@PathVariable Long id,
                                                                     @RequestBody RestauranteRequestDTO request){
       log.info("Alteração do restaurante ID: {}", id);
        Optional<Restaurante> restauranteEntidade = restauranteService.buscarRestaurantePorId(id);
        if (restauranteEntidade.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Restaurante restaurante = Restaurante.builder()
                .nome(request.nome())
                .categoria(request.categoria())
                .taxaEntrega(request.taxaEntrega())
                .tempoEntrega(request.tempoEntrega())
                .ativo(request.ativo())
                .build();
        Restaurante restSalvo = restauranteService.alterarRestaurante(id, restaurante);
        return ResponseEntity.ok(new RestauranteResponseDTO(restSalvo.getId(),
                restSalvo.getNome(),restSalvo.getCategoria(), restSalvo.getTempoEntrega(),
                restSalvo.getTaxaEntrega(), restSalvo.getAtivo()));
    }
}
