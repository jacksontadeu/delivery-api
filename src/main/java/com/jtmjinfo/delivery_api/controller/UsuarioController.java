package com.jtmjinfo.delivery_api.controller;

import com.jtmjinfo.delivery_api.entity.dto.Request.UsuarioRequestDTO;
import com.jtmjinfo.delivery_api.entity.dto.Response.UsuarioResponseDTO;
import com.jtmjinfo.delivery_api.entity.enums.Role;
import com.jtmjinfo.delivery_api.entity.model.Usuario;
import com.jtmjinfo.delivery_api.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuario", description = "API REST para Usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    @Operation(summary = "Realiza o cadastro de usuario")
    public ResponseEntity<UsuarioResponseDTO> cadastrarUsuario(@RequestBody UsuarioRequestDTO request){
        Usuario usuario = Usuario.builder()
                .nome(request.nome())
                .email(request.email())
                .senha(request.senha())
                .role(request.role() == null ? Role.CLIENTE : request.role())
                .build();

        Usuario usuarioSalvo = usuarioService.cadastrarUsuario(usuario);

        return ResponseEntity.ok(new UsuarioResponseDTO(
                usuarioSalvo.getId(),usuarioSalvo.getNome(),
                usuarioSalvo.getEmail()
        ));

    }
    @GetMapping("/{email}")
    @Operation(summary = "Busca um usuario pelo seu email")
    public ResponseEntity<UsuarioResponseDTO> buscarPorEmail(@PathVariable String email){
        Usuario usuario = usuarioService.obterEmail(email);
        if(usuario == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(new UsuarioResponseDTO(
                    usuario.getId(),usuario.getNome(),
                    usuario.getEmail()
            ));
        }
    }

}
