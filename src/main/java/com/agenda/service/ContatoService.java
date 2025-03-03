package com.agenda.service;

import com.agenda.domain.Contato;
import com.agenda.repository.ContatoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ContatoService {

    private final ContatoRepository contatoRepository;

    public ContatoService(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    @Transactional(readOnly = true)
    public List<Contato> listarTodos() {
        return contatoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Contato> buscarPorId(Long id) {
        return contatoRepository.findById(id);
    }

    public Contato salvar(Contato contato) {
        return contatoRepository.save(contato);
    }

    public Optional<Contato> atualizar(Long id, Contato contato) {
        return contatoRepository.findById(id)
                .map(contatoExistente -> {
                    contato.setId(id);
                    return contatoRepository.save(contato);
                });
    }

    public boolean deletar(Long id) {
        return contatoRepository.findById(id)
                .map(contato -> {
                    contatoRepository.delete(contato);
                    return true;
                })
                .orElse(false);
    }
} 