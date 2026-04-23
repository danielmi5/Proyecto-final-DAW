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
public class ProjectRequestDTO {

    @NotBlank
    @Schema(description = "Project name", example = "Internal backlog")
    private String name;

    @Schema(description = "Project description", example = "Custom requests managed inside Estimplytics")
    private String description;
}
