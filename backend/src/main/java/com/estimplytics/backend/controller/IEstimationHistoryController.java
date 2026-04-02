package com.estimplytics.backend.controller;

import com.estimplytics.backend.dto.EstimationHistoryRequestDTO;
import com.estimplytics.backend.dto.EstimationHistoryResponseDTO;
import com.estimplytics.backend.dto.EstimationHistoryUpdateDTO;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/estimation-histories")
public interface IEstimationHistoryController extends ICrudController<EstimationHistoryRequestDTO, EstimationHistoryResponseDTO, EstimationHistoryUpdateDTO, Long> {
}