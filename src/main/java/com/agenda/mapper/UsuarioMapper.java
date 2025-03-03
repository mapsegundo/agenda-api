package com.agenda.mapper;

import com.agenda.domain.Usuario;
import com.agenda.dto.RegistroDTO;
import com.agenda.dto.UsuarioDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    
    private final PasswordEncoder passwordEncoder;
    
    public UsuarioMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    
    public Usuario toEntity(RegistroDTO dto) {
        return Usuario.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(passwordEncoder.encode(dto.getSenha()))
                .build();
    }
    
    public UsuarioDTO toDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .build();
    }
} 