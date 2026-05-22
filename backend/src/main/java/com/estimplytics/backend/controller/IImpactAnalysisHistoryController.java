package com.estimplytics.backend.controller;

import com.estimplytics.backend.dto.ImpactAnalysisHistoryRequestDTO;
import com.estimplytics.backend.dto.ImpactAnalysisHistoryResponseDTO;
import com.estimplytics.backend.dto.ImpactAnalysisHistoryUpdateDTO;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/impact-analysis-histories")
public interface IImpactAnalysisHistoryController extends ICrudController<ImpactAnalysisHistoryRequestDTO, ImpactAnalysisHistoryResponseDTO, ImpactAnalysisHistoryUpdateDTO, Long> {
}