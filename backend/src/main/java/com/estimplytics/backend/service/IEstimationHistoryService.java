package com.estimplytics.backend.service;

import com.estimplytics.backend.dto.EstimationHistoryRequestDTO;
import com.estimplytics.backend.dto.EstimationHistoryResponseDTO;
import com.estimplytics.backend.dto.EstimationHistoryUpdateDTO;

public interface IEstimationHistoryService extends ICrudService<EstimationHistoryRequestDTO, EstimationHistoryResponseDTO, EstimationHistoryUpdateDTO, Long> {
}