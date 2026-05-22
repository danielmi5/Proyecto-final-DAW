package com.estimplytics.backend.mapper;

import com.estimplytics.backend.dto.ImpactAnalysisHistoryRequestDTO;
import com.estimplytics.backend.dto.ImpactAnalysisHistoryResponseDTO;
import com.estimplytics.backend.dto.ImpactAnalysisHistoryUpdateDTO;
import com.estimplytics.backend.entity.ImpactAnalysisHistory;
import com.estimplytics.backend.entity.ImpactAnalysis;
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
        if (dto.getFrozenVersion() != null) {
            entity.setFrozenVersion(dto.getFrozenVersion());
        }
        if (dto.getSnapshotData() != null) {
            entity.setSnapshotData(dto.getSnapshotData());
        }
        if (dto.getComponentsSnapshot() != null) {
            entity.setComponentsSnapshot(dto.getComponentsSnapshot());
        }
        if (dto.getModifiedAt() != null) {
            entity.setModifiedAt(dto.getModifiedAt());
        }
        
        return entity;
    }

    @Override
    public ImpactAnalysisHistoryResponseDTO toResponseDTO(ImpactAnalysisHistory entity) {
        if (entity == null) return null;
        return ImpactAnalysisHistoryResponseDTO.builder()
                .id(entity.getId())
                .analysisId(entity.getAnalysis() != null ? entity.getAnalysis().getId() : null)
                .frozenVersion(entity.getFrozenVersion())
                .snapshotData(entity.getSnapshotData())
                .componentsSnapshot(entity.getComponentsSnapshot())
                .modifiedAt(entity.getModifiedAt())
                .build();
    }

    @Override
    public void updateEntityFromDTO(ImpactAnalysisHistoryUpdateDTO dto, ImpactAnalysisHistory entity) {
        if (dto == null) return;
        if (dto.getFrozenVersion() != null) {
            entity.setFrozenVersion(dto.getFrozenVersion());
        }
        if (dto.getSnapshotData() != null) {
            entity.setSnapshotData(dto.getSnapshotData());
        }
        if (dto.getComponentsSnapshot() != null) {
            entity.setComponentsSnapshot(dto.getComponentsSnapshot());
        }
        if (dto.getModifiedAt() != null) {
            entity.setModifiedAt(dto.getModifiedAt());
        }
    }
}
