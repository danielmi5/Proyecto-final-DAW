package com.estimplytics.backend.controller;

import com.estimplytics.backend.dto.ImpactAnalysisRequestDTO;
import com.estimplytics.backend.dto.ImpactAnalysisResponseDTO;
import com.estimplytics.backend.dto.ImpactAnalysisUpdateDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.UUID;

@RequestMapping("/api/impact-analyses")
public interface IImpactAnalysisController extends ICrudController<ImpactAnalysisRequestDTO, ImpactAnalysisResponseDTO, ImpactAnalysisUpdateDTO, UUID> {
}