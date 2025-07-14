package com.jtmjinfo.delivery_api.service;

import com.jtmjinfo.delivery_api.entity.model.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ClienteServiceTest {
    @Autowired
    private ClienteService clienteService;

    @Test
    void cadastrarClienteTest(){
        Cliente cliente = new Cliente();
        cliente.setNome("Jackson Moraes");
        cliente.setEmail("jackson@gmail.com");
        cliente.setAtivo(true);
        clienteService.cadastrarCliente(cliente);
    }



}
