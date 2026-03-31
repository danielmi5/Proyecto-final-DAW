package com.estimplytics.backend.mapper;

import com.estimplytics.backend.dto.EstimationHistoryRequestDTO;
import com.estimplytics.backend.dto.EstimationHistoryResponseDTO;
import com.estimplytics.backend.dto.EstimationHistoryUpdateDTO;
import com.estimplytics.backend.entity.EstimationHistory;
import com.estimplytics.backend.entity.Estimation;
import com.estimplytics.backend.entity.ImpactAnalysis;
import org.springframework.stereotype.Service;

@Service
public class EstimationHistoryMapper implements IMapper<EstimationHistory, EstimationHistoryRequestDTO, EstimationHistoryResponseDTO, EstimationHistoryUpdateDTO> {

    @Override
    public EstimationHistory toEntity(EstimationHistoryRequestDTO dto) {
        if (dto == null) return null;
        EstimationHistory entity = new EstimationHistory();
        
        if (dto.getEstimationId() != null) {
            Estimation estimation = new Estimation();
            estimation.setId(dto.getEstimationId());
            entity.setEstimation(estimation);
        }
        
        if (dto.getAnalysisId() != null) {
            ImpactAnalysis analysis = new ImpactAnalysis();
            analysis.setId(dto.getAnalysisId());
            entity.setAnalysis(analysis);
        }
        
        entity.setTotalHours(dto.getTotalHours());
        entity.setVersion(dto.getVersion());
        
        return entity;
    }

    @Override
    public EstimationHistoryResponseDTO toResponseDTO(EstimationHistory entity) {
        if (entity == null) return null;
        return EstimationHistoryResponseDTO.builder()
                .id(entity.getId())
                .estimationId(entity.getEstimation() != null ? entity.getEstimation().getId() : null)
                .analysisId(entity.getAnalysis() != null ? entity.getAnalysis().getId() : null)
                .totalHours(entity.getTotalHours())
                .version(entity.getVersion())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    @Override
    public void updateEntityFromDTO(EstimationHistoryUpdateDTO dto, EstimationHistory entity) {
        if (dto == null) return;
        if (dto.getTotalHours() != null) {
            entity.setTotalHours(dto.getTotalHours());
        }
        if (dto.getVersion() != null) {
            entity.setVersion(dto.getVersion());
        }
    }
}
