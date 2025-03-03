package com.agenda.service;

import com.agenda.domain.Usuario;
import com.agenda.dto.RegistroDTO;
import com.agenda.dto.UsuarioDTO;
import com.agenda.exception.EmailJaExisteException;
import com.agenda.mapper.UsuarioMapper;
import com.agenda.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o email: " + email));
    }

    @Transactional(readOnly = true)
    public UserDetails loadUserById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o id: " + id));
    }

    @Transactional(readOnly = true)
    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o id: " + id));
    }

    @Transactional(readOnly = true)
    public UsuarioDTO getUsuarioDTOById(Long id) {
        Usuario usuario = getUsuarioById(id);
        return usuarioMapper.toDTO(usuario);
    }
    
    @Transactional
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public UsuarioDTO registrarUsuario(RegistroDTO registroDTO) {
        if (usuarioRepository.existsByEmail(registroDTO.getEmail())) {
            throw new EmailJaExisteException(registroDTO.getEmail());
        }

        Usuario usuario = usuarioMapper.toEntity(registroDTO);
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        
        return usuarioMapper.toDTO(usuarioSalvo);
    }
} 