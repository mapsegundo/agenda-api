package com.agenda.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroDTO {
    
    @NotBlank(message = "O nome é obrigatório")
    private String nome;
    
    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Formato de email inválido")
    private String email;
    
    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
    private String senha;
} 