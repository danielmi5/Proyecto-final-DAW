package com.estimplytics.backend.mapper;

import com.estimplytics.backend.dto.ImpactAnalysisHistoryRequestDTO;
import com.estimplytics.backend.dto.ImpactAnalysisHistoryResponseDTO;
import com.estimplytics.backend.dto.ImpactAnalysisHistoryUpdateDTO;
import com.estimplytics.backend.entity.ImpactAnalysisHistory;
import com.estimplytics.backend.entity.ImpactAnalysis;
import com.estimplytics.backend.entity.Request;
import com.estimplytics.backend.entity.User;
import org.springframework.stereotype.Service;

@Service
public class ImpactAnalysisHistoryMapper implements IMapper<ImpactAnalysisHistory, ImpactAnalysisHistoryRequestDTO, ImpactAnalysisHistoryResponseDTO, ImpactAnalysisHistoryUpdateDTO> {

    @Override
    public ImpactAnalysisHistory toEntity(ImpactAnalysisHistoryRequestDTO dto) {
        if (dto == null) return null;
        ImpactAnalysisHistory entity = new ImpactAnalysisHistory();
        
        if (dto.getAnalysisId() != null) {
            ImpactAnalysis analysis = new ImpactAnalysis();
            analysis.setId(dto.getAnalysisId());
            entity.setAnalysis(analysis);
        }
        
        if (dto.getRequestId() != null) {
            Request request = new Request();
            request.setId(dto.getRequestId());
            entity.setRequest(request);
        }

        if (dto.getAnalystId() != null) {
            User analyst = new User();
            analyst.setId(dto.getAnalystId());
            entity.setAnalyst(analyst);
        }
        
        entity.setVersion(dto.getVersion());
        
        return entity;
    }

    @Override
    public ImpactAnalysisHistoryResponseDTO toResponseDTO(ImpactAnalysisHistory entity) {
        if (entity == null) return null;
        return ImpactAnalysisHistoryResponseDTO.builder()
                .id(entity.getId())
                .analysisId(entity.getAnalysis() != null ? entity.getAnalysis().getId() : null)
                .requestId(entity.getRequest() != null ? entity.getRequest().getId() : null)
                .analystId(entity.getAnalyst() != null ? entity.getAnalyst().getId() : null)
                .version(entity.getVersion())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    @Override
    public void updateEntityFromDTO(ImpactAnalysisHistoryUpdateDTO dto, ImpactAnalysisHistory entity) {
        if (dto == null) return;
        if (dto.getVersion() != null) {
            entity.setVersion(dto.getVersion());
        }
    }
}
