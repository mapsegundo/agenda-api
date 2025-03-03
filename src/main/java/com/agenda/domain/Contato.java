package com.agenda.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade que representa um contato na agenda
 * 
 * @author Marshall Paiva
 * @version 1.0.0
 * @since 2024-03-03
 * @see https://www.linkedin.com/in/marshallpaiva/
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contato {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    
    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Telefone deve estar no formato (99) 99999-9999")
    private String telefone;
    
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    private String email;
    
    private String endereco;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
} 