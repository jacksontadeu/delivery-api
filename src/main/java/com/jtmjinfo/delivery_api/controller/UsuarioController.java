package com.jtmjinfo.delivery_api.controller;

import com.jtmjinfo.delivery_api.entity.dto.Request.UsuarioRequestDTO;
import com.jtmjinfo.delivery_api.entity.dto.Response.UsuarioResponseDTO;
import com.jtmjinfo.delivery_api.entity.enums.Role;
import com.jtmjinfo.delivery_api.entity.model.Usuario;
import com.jtmjinfo.delivery_api.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioResponseDTO> cadastrarUsuario(@RequestBody UsuarioRequestDTO request){
        Usuario usuario = Usuario.builder()
                .nome(request.nome())
                .email(request.email())
                .senha(request.senha())
                .role(Role.CLIENTE)
                .build();

        Usuario usuarioSalvo = usuarioService.cadastrarUsuario(usuario);

        return ResponseEntity.ok(new UsuarioResponseDTO(
                usuarioSalvo.getId(),usuarioSalvo.getNome(),
                usuarioSalvo.getEmail()
        ));

    }

}
