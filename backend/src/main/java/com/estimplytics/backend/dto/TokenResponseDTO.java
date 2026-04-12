package com.estimplytics.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenResponseDTO {
    @Schema(description = "Token JWT de acceso", example = "eyJhbGciOiJIUzI1NiJ9...")
    private String accessToken;
    @Schema(description = "Tipo de token", example = "Bearer")
    private String tokenType;
    @Schema(description = "Tiempo de expiración en milisegundos", example = "3600000")
    private long expiresIn;
}
