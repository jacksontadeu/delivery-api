package com.deliverytech.delivery_api.entity.model;

import java.time.LocalDateTime;
import java.util.List;


import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro= LocalDateTime.now();

    @Column(nullable = true)
    private Boolean ativo= true;

    @OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

    public void inativar(){
        this.ativo = false;
    }

}
