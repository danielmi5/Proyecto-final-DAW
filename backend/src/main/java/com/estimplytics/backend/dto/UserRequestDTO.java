package com.estimplytics.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDTO {
    
    @Schema(description = "Nombre completo del usuario", example = "Daniel")
    private String name;

    @NotBlank
    @Schema(description = "Correo electrónico único del usuario", example = "daniel@estymplytics.es")
    private String email;

    @NotBlank
    @Schema(description = "Contraseña en texto plano", example = "Zx23edfzTF")
    private String password;

    @NotBlank
    @Schema(description = "Rol del usuario (ADMIN, ANALYST)", example = "ANALYST")
    private String role;
}
