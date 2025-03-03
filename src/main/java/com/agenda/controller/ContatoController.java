package com.agenda.controller;

import com.agenda.domain.Usuario;
import com.agenda.dto.ContatoDTO;
import com.agenda.mapper.ContatoMapper;
import com.agenda.service.ContatoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador REST para gerenciamento de contatos
 * 
 * @author Marshall Paiva
 * @version 1.0.0
 * @since 2024-03-03
 * @see https://www.linkedin.com/in/marshallpaiva/
 */
@RestController
@RequestMapping("/api/v1/contatos")
@Tag(name = "Contatos", description = "API para gerenciamento de contatos")
@SecurityRequirement(name = "bearerAuth")
public class ContatoController {

    private final ContatoService contatoService;
    private final ContatoMapper contatoMapper;

    public ContatoController(ContatoService contatoService, ContatoMapper contatoMapper) {
        this.contatoService = contatoService;
        this.contatoMapper = contatoMapper;
    }

    @Operation(summary = "Lista todos os contatos", description = "Retorna uma lista com todos os contatos do usuário logado")
    @GetMapping
    public ResponseEntity<List<ContatoDTO>> listarContatos(@AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.ok(
            contatoService.listarTodos(usuario).stream()
                .map(contatoMapper::toDTO)
                .collect(Collectors.toList())
        );
    }

    @Operation(summary = "Busca um contato pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contato encontrado"),
        @ApiResponse(responseCode = "404", description = "Contato não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ContatoDTO> buscarContato(
            @PathVariable Long id,
            @AuthenticationPrincipal Usuario usuario) {
        return contatoService.buscarPorId(id, usuario.getId())
                .map(contato -> ResponseEntity.ok(contatoMapper.toDTO(contato)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cria um novo contato")
    @PostMapping
    public ResponseEntity<ContatoDTO> criarContato(
            @Valid @RequestBody ContatoDTO contatoDTO,
            @AuthenticationPrincipal Usuario usuario) {
        var contato = contatoMapper.toEntity(contatoDTO, usuario);
        var novoContato = contatoService.salvar(contato);
        var novoContatoDTO = contatoMapper.toDTO(novoContato);
        
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoContatoDTO.getId())
                .toUri();
                
        return ResponseEntity.created(location).body(novoContatoDTO);
    }

    @Operation(summary = "Atualiza um contato existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contato atualizado"),
        @ApiResponse(responseCode = "404", description = "Contato não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ContatoDTO> atualizarContato(
            @PathVariable Long id,
            @Valid @RequestBody ContatoDTO contatoDTO,
            @AuthenticationPrincipal Usuario usuario) {
        var contato = contatoMapper.toEntity(contatoDTO);
        return contatoService.atualizar(id, contato, usuario.getId())
                .map(contatoAtualizado -> ResponseEntity.ok(contatoMapper.toDTO(contatoAtualizado)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Remove um contato")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Contato removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Contato não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarContato(
            @PathVariable Long id,
            @AuthenticationPrincipal Usuario usuario) {
        if (contatoService.deletar(id, usuario.getId())) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
} 