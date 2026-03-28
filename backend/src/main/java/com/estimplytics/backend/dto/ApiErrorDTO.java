package com.estimplytics.backend.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiErrorDTO {

    private LocalDateTime timestamp;

    private int stateNum;

    private String error;

    private String message;

    private String description;

    private String path;
}
