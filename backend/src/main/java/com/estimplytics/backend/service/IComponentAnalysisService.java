package com.estimplytics.backend.service;

import com.estimplytics.backend.dto.ComponentAnalysisRequestDTO;
import com.estimplytics.backend.dto.ComponentAnalysisResponseDTO;
import com.estimplytics.backend.dto.ComponentAnalysisUpdateDTO;
import java.util.UUID;

public interface IComponentAnalysisService extends ICrudService<ComponentAnalysisRequestDTO, ComponentAnalysisResponseDTO, ComponentAnalysisUpdateDTO, UUID> {
}