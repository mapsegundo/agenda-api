package com.agenda.repository;

import com.agenda.domain.Contato;
import com.agenda.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
    List<Contato> findByUsuario(Usuario usuario);
    List<Contato> findByUsuarioId(Long usuarioId);
    Optional<Contato> findByIdAndUsuarioId(Long id, Long usuarioId);
    List<Contato> findByNomeContainingIgnoreCase(String nome);
    Optional<Contato> findByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCase(String email);
} 