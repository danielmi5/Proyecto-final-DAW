package com.estimplytics.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenRequestDTO {
    @NotBlank
    @Email
    @Schema(description = "User email", example = "daniel@estymplytics.es")
    private String email;
    @NotBlank
    @Schema(description = "User password", example = "Zx23edfzTF")
    private String password;
}
