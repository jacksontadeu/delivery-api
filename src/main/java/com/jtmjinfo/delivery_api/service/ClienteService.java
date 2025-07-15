package com.jtmjinfo.delivery_api.service;

import com.jtmjinfo.delivery_api.entity.model.Cliente;
import com.jtmjinfo.delivery_api.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente cadastrarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    public Optional<Cliente> buscarClientePorId(Long id){

        return clienteRepository.findById(id);
    }
    public List<Cliente> listarClientes(){
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes;
    }
    public Cliente alterarCliente(Long id, Cliente atualizado){
        return  clienteRepository.findById(id).
                map(c -> {
                    c.setNome(atualizado.getNome());
                    c.setEmail(atualizado.getEmail());
                    return clienteRepository.save(c);
                }).orElseThrow(()-> new RuntimeException("Cliente não encontrado"));


    }
}
