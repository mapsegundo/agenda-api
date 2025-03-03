package com.agenda.mapper;

import com.agenda.dto.ContatoDTO;
import com.agenda.domain.Contato;
import org.springframework.stereotype.Component;

@Component
public class ContatoMapper {
    
    public ContatoDTO toDTO(Contato contato) {
        if (contato == null) {
            return null;
        }

        ContatoDTO dto = new ContatoDTO();
        dto.setId(contato.getId());
        dto.setNome(contato.getNome());
        dto.setTelefone(contato.getTelefone());
        dto.setEmail(contato.getEmail());
        dto.setEndereco(contato.getEndereco());
        return dto;
    }
    
    public Contato toEntity(ContatoDTO dto) {
        if (dto == null) {
            return null;
        }

        Contato contato = new Contato();
        contato.setId(dto.getId());
        contato.setNome(dto.getNome());
        contato.setTelefone(dto.getTelefone());
        contato.setEmail(dto.getEmail());
        contato.setEndereco(dto.getEndereco());
        return contato;
    }
} 