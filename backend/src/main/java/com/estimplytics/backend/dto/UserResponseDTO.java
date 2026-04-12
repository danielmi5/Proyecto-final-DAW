package com.estimplytics.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {
    
    @Schema(description = "Identificador único del usuario", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID id;
    
    @Schema(description = "Nombre completo del usuario", example = "Daniel")
    private String name;
    
    @Schema(description = "Correo electrónico del usuario", example = "daniel@estymplytics.es")
    private String email;
    
    @Schema(description = "Rol del usuario", example = "ANALYST")
    private String role;
    
    @Schema(description = "Fecha y hora de creación del usuario", example = "2026-05-08T08:00:00")
    private LocalDateTime createdAt;
}
