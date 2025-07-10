package com.deliverytech.delivery_api.entity.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String categoria;

    private Integer tempoEntrega;

    @Column(name = "taxa_entrega")
    private BigDecimal taxaEntrega;

    private Boolean ativo= true;

    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos;

    @OneToMany(mappedBy = "restaurante")
    private List<Pedido> pedidos;

    public void inativar(){
        this.ativo =false;
    }

}
