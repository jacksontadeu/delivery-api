package com.deliverytech.delivery_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.deliverytech.delivery_api.entity.model.Cliente;
import com.deliverytech.delivery_api.repository.ClienteRepository;

public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos(){
        return clienteRepository.findAll();
    }

}
