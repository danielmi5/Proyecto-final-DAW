package com.estimplytics.backend.dto;

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
public class ComponentUpdateDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String category;

    @NotNull
    private Boolean active;
}