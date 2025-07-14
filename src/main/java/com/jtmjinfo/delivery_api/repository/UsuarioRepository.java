package com.jtmjinfo.delivery_api.repository;

import com.jtmjinfo.delivery_api.entity.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);


}
