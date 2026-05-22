package com.estimplytics.backend.mapper;

import com.estimplytics.backend.dto.ComponentAnalysisRequestDTO;
import com.estimplytics.backend.dto.ComponentAnalysisResponseDTO;
import com.estimplytics.backend.dto.ComponentAnalysisUpdateDTO;
import com.estimplytics.backend.entity.ComponentAnalysis;
import com.estimplytics.backend.entity.Component;
import com.estimplytics.backend.entity.ImpactAnalysis;
import org.springframework.stereotype.Service;

@Service
public class ComponentAnalysisMapper implements IMapper<ComponentAnalysis, ComponentAnalysisRequestDTO, ComponentAnalysisResponseDTO, ComponentAnalysisUpdateDTO> {

    @Override
    public ComponentAnalysis toEntity(ComponentAnalysisRequestDTO dto) {
        if (dto == null) return null;
        ComponentAnalysis entity = new ComponentAnalysis();

        if (dto.getAnalysisId() != null) {
            ImpactAnalysis analysis = new ImpactAnalysis();
            analysis.setId(dto.getAnalysisId());
            entity.setAnalysis(analysis);
        }

        if (dto.getComponentId() != null) {
            Component component = new Component();
            component.setId(dto.getComponentId());
            entity.setComponent(component);
        }

        return entity;
    }

    @Override
    public ComponentAnalysisResponseDTO toResponseDTO(ComponentAnalysis entity) {
        if (entity == null) return null;
        return ComponentAnalysisResponseDTO.builder()
                .id(entity.getId())
                .analysisId(entity.getAnalysis() != null ? entity.getAnalysis().getId() : null)
                .componentId(entity.getComponent() != null ? entity.getComponent().getId() : null)
                .createdAt(entity.getCreatedAt())
                .build();
    }

    @Override
    public void updateEntityFromDTO(ComponentAnalysisUpdateDTO dto, ComponentAnalysis entity) {
    }
}