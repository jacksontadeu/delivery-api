package com.jtmjinfo.delivery_api.entity.model;

import com.jtmjinfo.delivery_api.entity.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    private String senha;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean ativo = true;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    private Long restauranteId;
}
