package com.estimplytics.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComponentRequestDTO {
    @NotBlank
    @Schema(description = "Component name", example = "API Gateway")
    private String name;

    @NotBlank
    @Schema(description = "Component category", example = "INFRASTRUCTURE")
    private String category;

    @NotNull
    @Schema(description = "Indicates whether the component is active", example = "true")
    private Boolean active;
}