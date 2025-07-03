package com.deliverytech.delivery_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deliverytech.delivery_api.entity.model.Produto;

public interface ProdutoRespository extends JpaRepository<Produto,Long>{

}
