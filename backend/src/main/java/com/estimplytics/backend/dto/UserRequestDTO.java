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
    
    @Schema(description = "User full name", example = "Daniel")
    private String name;

    @NotBlank
    @Schema(description = "User unique email address", example = "daniel@estimplytics.es")
    private String email;

    @NotBlank
    @Schema(description = "Password hashed", example = "Zx23edfzTF")
    private String password;

    @NotBlank
    @Schema(description = "User role (ADMIN, ANALYST)", example = "ANALYST")
    private String role;
}
