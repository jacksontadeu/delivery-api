package com.jtmjinfo.delivery_api.entity.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Endereco {
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

}
