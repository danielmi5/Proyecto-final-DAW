package com.estimplytics.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestRequestDTO {
    private Integer redmineId;
    private String originRequestCode;

    @NotBlank
    private String projectName;

    private String demandType;

    @NotBlank
    private String title;

    private String description;

    @NotBlank
    private String status;

    @NotBlank
    private String priority;

    private String assigneeName;
    private LocalDate startDate;
    private LocalDate endDate;

    @NotNull
    private LocalDateTime createdDate;
}
