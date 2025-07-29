package com.jtmjinfo.delivery_api.service;

import com.jtmjinfo.delivery_api.entity.model.Usuario;
import com.jtmjinfo.delivery_api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    @Autowired
    private final UsuarioRepository usuarioRepository;
    @Autowired
    private final PasswordEncoder encoder;

    public Usuario cadastrarUsuario(Usuario usuario){
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        usuario.setAtivo(true);
        usuario.setDataCriacao(LocalDateTime.now());
        return usuarioRepository.save(usuario);
    }
    public Usuario obterEmail(String email){
        return usuarioRepository.findByEmail(email).
               orElse(null);

    }
}
