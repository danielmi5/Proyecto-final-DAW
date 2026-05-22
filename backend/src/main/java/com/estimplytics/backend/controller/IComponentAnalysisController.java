package com.estimplytics.backend.controller;

import com.estimplytics.backend.dto.ComponentAnalysisRequestDTO;
import com.estimplytics.backend.dto.ComponentAnalysisResponseDTO;
import com.estimplytics.backend.dto.ComponentAnalysisUpdateDTO;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RequestMapping("/api/component-analyses")
public interface IComponentAnalysisController extends ICrudController<ComponentAnalysisRequestDTO, ComponentAnalysisResponseDTO, ComponentAnalysisUpdateDTO, UUID> {
}