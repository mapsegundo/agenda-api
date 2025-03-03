package com.agenda.service;

import com.agenda.domain.Contato;
import com.agenda.domain.Usuario;
import com.agenda.repository.ContatoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    private final ContatoRepository contatoRepository;

    public ContatoService(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    @Transactional(readOnly = true)
    public List<Contato> listarTodos(Usuario usuario) {
        return contatoRepository.findByUsuario(usuario);
    }

    @Transactional(readOnly = true)
    public Optional<Contato> buscarPorId(Long id, Long usuarioId) {
        return contatoRepository.findByIdAndUsuarioId(id, usuarioId);
    }

    @Transactional
    public Contato salvar(Contato contato) {
        return contatoRepository.save(contato);
    }

    @Transactional
    public Optional<Contato> atualizar(Long id, Contato contato, Long usuarioId) {
        return buscarPorId(id, usuarioId)
                .map(contatoExistente -> {
                    contatoExistente.setNome(contato.getNome());
                    contatoExistente.setTelefone(contato.getTelefone());
                    contatoExistente.setEmail(contato.getEmail());
                    contatoExistente.setEndereco(contato.getEndereco());
                    return contatoRepository.save(contatoExistente);
                });
    }

    @Transactional
    public boolean deletar(Long id, Long usuarioId) {
        return buscarPorId(id, usuarioId)
                .map(contato -> {
                    contatoRepository.delete(contato);
                    return true;
                })
                .orElse(false);
    }
} 