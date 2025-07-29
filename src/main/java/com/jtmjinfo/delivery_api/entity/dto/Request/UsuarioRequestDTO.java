package com.jtmjinfo.delivery_api.entity.dto.Request;

import com.jtmjinfo.delivery_api.entity.enums.Role;

public record UsuarioRequestDTO(String nome,
                                String email,
                                String senha,
                                Role role) {
}
