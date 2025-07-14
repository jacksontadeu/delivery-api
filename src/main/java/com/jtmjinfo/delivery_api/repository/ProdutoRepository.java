package com.jtmjinfo.delivery_api.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jtmjinfo.delivery_api.entity.model.Produto;
import com.jtmjinfo.delivery_api.entity.model.Restaurante;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByRestauranteId(Long restauranteId);

    List<Produto> findByDisponivelTrue();
}

