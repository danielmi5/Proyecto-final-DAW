package com.estimplytics.backend.controller;

import com.estimplytics.backend.dto.EstimationRequestDTO;
import com.estimplytics.backend.dto.EstimationResponseDTO;
import com.estimplytics.backend.dto.EstimationUpdateDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.UUID;

@RequestMapping("/api/estimations")
public interface IEstimationController extends ICrudController<EstimationRequestDTO, EstimationResponseDTO, EstimationUpdateDTO, UUID> {
}