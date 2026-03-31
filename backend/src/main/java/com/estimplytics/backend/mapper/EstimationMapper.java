package com.estimplytics.backend.mapper;

import com.estimplytics.backend.dto.EstimationRequestDTO;
import com.estimplytics.backend.dto.EstimationResponseDTO;
import com.estimplytics.backend.dto.EstimationUpdateDTO;
import com.estimplytics.backend.entity.Estimation;
import com.estimplytics.backend.entity.ImpactAnalysis;
import org.springframework.stereotype.Component;

@Component
public class EstimationMapper implements IMapper<Estimation, EstimationRequestDTO, EstimationResponseDTO, EstimationUpdateDTO> {

    @Override
    public Estimation toEntity(EstimationRequestDTO dto) {
        if (dto == null) return null;
        Estimation estimation = new Estimation();
        estimation.setTotalHours(dto.getTotalHours());
        
        if (dto.getAnalysisId() != null) {
            ImpactAnalysis analysis = new ImpactAnalysis();
            analysis.setId(dto.getAnalysisId());
            estimation.setAnalysis(analysis);
        }
        return estimation;
    }

    @Override
    public EstimationResponseDTO toResponseDTO(Estimation entity) {
        if (entity == null) return null;
        return EstimationResponseDTO.builder()
                .id(entity.getId())
                .analysisId(entity.getAnalysis() != null ? entity.getAnalysis().getId() : null)
                .totalHours(entity.getTotalHours())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    @Override
    public void updateEntityFromDTO(EstimationUpdateDTO dto, Estimation entity) {
        if (dto == null || entity == null) return;
        if (dto.getTotalHours() != null) {
            entity.setTotalHours(dto.getTotalHours());
        }
    }
}