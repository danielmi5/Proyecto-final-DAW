package com.estimplytics.backend.mapper;

import com.estimplytics.backend.dto.ImpactAnalysisRequestDTO;
import com.estimplytics.backend.dto.ImpactAnalysisResponseDTO;
import com.estimplytics.backend.dto.ImpactAnalysisUpdateDTO;
import com.estimplytics.backend.entity.ImpactAnalysis;
import com.estimplytics.backend.entity.Request;
import com.estimplytics.backend.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ImpactAnalysisMapper implements IMapper<ImpactAnalysis, ImpactAnalysisRequestDTO, ImpactAnalysisResponseDTO, ImpactAnalysisUpdateDTO> {

    @Override
    public ImpactAnalysis toEntity(ImpactAnalysisRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        ImpactAnalysis analysis = new ImpactAnalysis();
        if (dto.getRequestId() != null) {
            Request request = new Request();
            request.setId(dto.getRequestId());
            analysis.setRequest(request);
        }
        if (dto.getUserId() != null) {
            User user = new User();
            user.setId(dto.getUserId());
            analysis.setAnalyst(user);
        }
        return analysis;
    }

    @Override
    public ImpactAnalysisResponseDTO toResponseDTO(ImpactAnalysis entity) {
        if (entity == null) {
            return null;
        }
        return ImpactAnalysisResponseDTO.builder()
                .id(entity.getId())
                .requestId(entity.getRequest() != null ? entity.getRequest().getId() : null)
                .userId(entity.getAnalyst() != null ? entity.getAnalyst().getId() : null)
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    @Override
    public void updateEntityFromDTO(ImpactAnalysisUpdateDTO dto, ImpactAnalysis entity) {
        if (dto == null || entity == null) {
            return;
        }
        if (dto.getRequestId() != null) {
            if (entity.getRequest() == null) {
                entity.setRequest(new Request());
            }
            entity.getRequest().setId(dto.getRequestId());
        }
        if (dto.getUserId() != null) {
            if (entity.getAnalyst() == null) {
                entity.setAnalyst(new User());
            }
            entity.getAnalyst().setId(dto.getUserId());
        }
    }
}