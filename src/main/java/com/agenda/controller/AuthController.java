package com.agenda.controller;

import com.agenda.dto.LoginDTO;
import com.agenda.dto.RegistroDTO;
import com.agenda.dto.TokenDTO;
import com.agenda.dto.UsuarioDTO;
import com.agenda.mapper.UsuarioMapper;
import com.agenda.security.TokenService;
import com.agenda.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para autenticação e registro de usuários
 * 
 * @author Marshall Paiva
 * @version 1.0.0
 * @since 2024-03-03
 * @see https://www.linkedin.com/in/marshallpaiva/
 */
@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Autenticação", description = "API para autenticação e registro de usuários")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;
    private final TokenService tokenService;
    private final UsuarioMapper usuarioMapper;

    public AuthController(
            AuthenticationManager authenticationManager,
            UsuarioService usuarioService,
            TokenService tokenService,
            UsuarioMapper usuarioMapper) {
        this.authenticationManager = authenticationManager;
        this.usuarioService = usuarioService;
        this.tokenService = tokenService;
        this.usuarioMapper = usuarioMapper;
    }

    @Operation(summary = "Fazer login", description = "Autentica um usuário com email e senha")
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken authToken = 
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getSenha());
        
        Authentication authentication = authenticationManager.authenticate(authToken);
        
        String jwt = tokenService.generateToken(authentication);
        UsuarioDTO usuarioDTO = usuarioMapper.toDTO((com.agenda.domain.Usuario) authentication.getPrincipal());
        
        return ResponseEntity.ok(TokenDTO.builder()
                .accessToken(jwt)
                .tokenType("Bearer")
                .expiresIn(tokenService.getExpiration() / 1000) // Convertendo de milissegundos para segundos
                .usuario(usuarioDTO)
                .build());
    }

    @Operation(summary = "Registrar novo usuário", description = "Registra um novo usuário no sistema")
    @PostMapping("/registro")
    public ResponseEntity<UsuarioDTO> registro(@Valid @RequestBody RegistroDTO registroDTO) {
        UsuarioDTO usuarioDTO = usuarioService.registrarUsuario(registroDTO);
        return ResponseEntity.ok(usuarioDTO);
    }
} 