package com.jtmjinfo.delivery_api.controller;


import com.jtmjinfo.delivery_api.entity.dto.Request.ClienteRequestDTO;
import com.jtmjinfo.delivery_api.entity.dto.Response.ClienteResponseDTO;
import com.jtmjinfo.delivery_api.entity.model.Cliente;
import com.jtmjinfo.delivery_api.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/cadastrar")
    public ResponseEntity<ClienteResponseDTO> cadastrarCliente(@RequestBody ClienteRequestDTO request) {
        Cliente cliente = Cliente.builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .ativo(true)
                .build();
        Cliente clienteSalvo = clienteService.cadastrarCliente(cliente);
        return ResponseEntity.ok(new ClienteResponseDTO(clienteSalvo.getId(), clienteSalvo.getNome(),
                clienteSalvo.getEmail(), clienteSalvo.getAtivo()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarClientePorId(@PathVariable Long id) {
        Optional<Cliente> clienteOptional = clienteService.buscarClientePorId(id);
        if (clienteOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            Cliente cliente = clienteOptional.get();
            return ResponseEntity.ok(new ClienteResponseDTO(cliente.getId(),
                    cliente.getNome(),
                    cliente.getEmail(),
                    cliente.getAtivo()));
        }
    }

    @GetMapping("/listar")
    public List<ClienteResponseDTO> listarClientes() {
        return clienteService.listarClientes().
                stream().map(c -> new ClienteResponseDTO(c.getId(),
                        c.getNome(), c.getEmail(), c.getAtivo())).collect(Collectors.toList());

    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> alterarCliente(@PathVariable Long id, @RequestBody ClienteRequestDTO request) {
        Optional<Cliente> clienteEntidade = clienteService.buscarClientePorId(id);
        if(clienteEntidade.isPresent()) {
            Cliente cliente = Cliente.builder()
                    .nome(request.getNome())
                    .email(request.getEmail())
                    .build();

            Cliente clienteSalvo = clienteService.alterarCliente(id, cliente);
            return ResponseEntity.ok(new ClienteResponseDTO(clienteSalvo.getId(),
                    clienteSalvo.getNome(), clienteSalvo.getEmail(), clienteSalvo.getAtivo()));
        }else{
            return ResponseEntity.notFound().build();
        }

    }


}
