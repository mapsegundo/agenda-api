package com.agenda.mapper;

import com.agenda.domain.Contato;
import com.agenda.domain.Usuario;
import com.agenda.dto.ContatoDTO;
import org.springframework.stereotype.Component;

@Component
public class ContatoMapper {
    
    public ContatoDTO toDTO(Contato contato) {
        if (contato == null) {
            return null;
        }
        
        return ContatoDTO.builder()
                .id(contato.getId())
                .nome(contato.getNome())
                .telefone(contato.getTelefone())
                .email(contato.getEmail())
                .endereco(contato.getEndereco())
                .usuarioId(contato.getUsuario() != null ? contato.getUsuario().getId() : null)
                .build();
    }
    
    public Contato toEntity(ContatoDTO dto) {
        if (dto == null) {
            return null;
        }
        
        return Contato.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .telefone(dto.getTelefone())
                .email(dto.getEmail())
                .endereco(dto.getEndereco())
                .build();
    }
    
    public Contato toEntity(ContatoDTO dto, Usuario usuario) {
        if (dto == null) {
            return null;
        }
        
        return Contato.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .telefone(dto.getTelefone())
                .email(dto.getEmail())
                .endereco(dto.getEndereco())
                .usuario(usuario)
                .build();
    }
    
    public void updateEntity(Contato contato, ContatoDTO dto) {
        if (contato != null && dto != null) {
            contato.setNome(dto.getNome());
            contato.setTelefone(dto.getTelefone());
            contato.setEmail(dto.getEmail());
            contato.setEndereco(dto.getEndereco());
        }
    }
}