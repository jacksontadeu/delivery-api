package com.jtmjinfo.delivery_api.service;

import com.jtmjinfo.delivery_api.entity.dto.Response.ClienteResponse;
import com.jtmjinfo.delivery_api.entity.model.Cliente;
import com.jtmjinfo.delivery_api.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente cadastrarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    public ClienteResponse buscarClientePorId(Long id){
        Cliente cliente = clienteRepository.findById(id).get();
        return new ClienteResponse(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getAtivo());
    }
    public List<Cliente> listarClientes(){
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes;

    }
}
