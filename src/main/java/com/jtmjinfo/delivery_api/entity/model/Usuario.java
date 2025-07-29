package com.jtmjinfo.delivery_api.entity.model;

import com.jtmjinfo.delivery_api.entity.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private Role role;

    private Boolean ativo;;

    private LocalDateTime dataCriacao;

    private Long restauranteId;
}
