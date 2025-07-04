package com.deliverytech.delivery_api.entity.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.deliverytech.delivery_api.entity.enums.StatusPedido;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="numero_pedido")
    private String numeroPedido;

    @Column(name = "data_pedido")
    private LocalDateTime dataPedido;
    

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens;
}
