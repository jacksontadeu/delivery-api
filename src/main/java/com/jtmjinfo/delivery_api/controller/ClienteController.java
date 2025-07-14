package com.jtmjinfo.delivery_api.controller;


import com.jtmjinfo.delivery_api.entity.dto.Request.ClienteRequest;
import com.jtmjinfo.delivery_api.entity.dto.Response.ClienteResponse;
import com.jtmjinfo.delivery_api.entity.model.Cliente;
import com.jtmjinfo.delivery_api.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/cadastrar")
    public ResponseEntity<ClienteResponse> cadastrarCliente(@RequestBody ClienteRequest request){
        Cliente cliente = Cliente.builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .ativo(true)
                .build();
       Cliente clienteSalvo =clienteService.cadastrarCliente(cliente);
        return ResponseEntity.ok(new ClienteResponse(clienteSalvo.getId(), clienteSalvo.getNome(),
                clienteSalvo.getEmail(), clienteSalvo.getAtivo()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse>buscarClientePorId(@PathVariable Long id){
        ClienteResponse cliente = clienteService.buscarClientePorId(id);
        if(cliente == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(cliente);

    }
    @GetMapping("/listar")
    public List<ClienteResponse>listarClientes(){
         return clienteService.listarClientes().
                stream().map(c -> new ClienteResponse(c.getId(),
                         c.getNome(),c.getEmail(),c.getAtivo())).collect(Collectors.toList());

    }

}
