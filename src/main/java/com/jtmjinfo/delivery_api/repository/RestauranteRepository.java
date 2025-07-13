package com.jtmjinfo.delivery_api.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jtmjinfo.delivery_api.entity.model.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    List<Restaurante> findByCategoria(String categoria);
    List<Restaurante> findByAtivoTrue();
//    // Buscar por nome
//    Optional<Restaurante> findByNome(String nome);
//
//    // Buscar restaurantes a􀆟vos
//    List<Restaurante> findByAtivoTrue();
//
//    // Buscar por categoria
//    List<Restaurante> findByCategoriaAndAtivoTrue(String categoria);
//
//    // Buscar por nome contendo (case insensi􀆟ve)
//    List<Restaurante> findByNomeContainingIgnoreCaseAndAtivoTrue(String nome);
//
//    // Buscar por avaliação mínima
//    List<Restaurante> findByAvaliacaoGreaterThanEqualAndAtivoTrue(BigDecimal avaliacao);
//
//    // Ordenar por avaliação (descendente)
//    List<Restaurante> findByAtivoTrueOrderByAvaliacaoDesc();
//
//    // Query customizada - restaurantes com produtos
//    @Query("SELECT DISTINCT r FROM Restaurante r JOIN r.produtos p WHERE r.a􀆟vo = true")
//    List<Restaurante> findRestaurantesComProdutos();
//
//    // Buscar por faixa de taxa de entrega
//    @Query("SELECT r FROM Restaurante r WHERE r.taxaEntrega BETWEEN :min AND :max AND r.ativo = true")
//    List<Restaurante> findByTaxaEntregaBetween(@Param("min") BigDecimal min,
//    @Param("max") BigDecimal max);
//
//    // Categorias disponíveis
//    @Query("SELECT DISTINCT r.categoria FROM Restaurante r WHERE r.ativo = true ORDER BYr.categoria")
//    List<String> findCategoriasDisponiveis();
}
