package com.jtmjinfo.delivery_api.entity.model;

import com.jtmjinfo.delivery_api.entity.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_pedido")
    private LocalDateTime dataPedido = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;

    @Embedded
    private Endereco enderecoEntrega;

}
