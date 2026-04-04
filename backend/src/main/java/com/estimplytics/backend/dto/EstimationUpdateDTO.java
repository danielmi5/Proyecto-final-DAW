package com.estimplytics.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstimationUpdateDTO {
    @NotNull
    private Integer versionNumber;

    private Integer fiability;
    private Integer hoursAn;
    private Integer hoursAs;
    private Integer hoursDe;
    private Integer totalHours;
    private Integer actualHoursFeedback;
    private String justification;

    @NotNull
    private LocalDateTime updatedAt;
}