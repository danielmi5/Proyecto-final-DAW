package com.estimplytics.backend.mapper;

import com.estimplytics.backend.dto.EstimationHistoryRequestDTO;
import com.estimplytics.backend.dto.EstimationHistoryResponseDTO;
import com.estimplytics.backend.dto.EstimationHistoryUpdateDTO;
import com.estimplytics.backend.entity.EstimationHistory;
import com.estimplytics.backend.entity.Estimation;
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
        if (dto.getFrozenVersion() != null) {
            entity.setFrozenVersion(dto.getFrozenVersion());
        }
        if (dto.getSnapshotData() != null) {
            entity.setSnapshotData(dto.getSnapshotData());
        }
        if (dto.getModifiedAt() != null) {
            entity.setModifiedAt(dto.getModifiedAt());
        }
        
        return entity;
    }

    @Override
    public EstimationHistoryResponseDTO toResponseDTO(EstimationHistory entity) {
        if (entity == null) return null;
        return EstimationHistoryResponseDTO.builder()
                .id(entity.getId())
                .estimationId(entity.getEstimation() != null ? entity.getEstimation().getId() : null)
                .frozenVersion(entity.getFrozenVersion())
                .snapshotData(entity.getSnapshotData())
                .modifiedAt(entity.getModifiedAt())
                .build();
    }

    @Override
    public void updateEntityFromDTO(EstimationHistoryUpdateDTO dto, EstimationHistory entity) {
        if (dto == null) return;
        if (dto.getFrozenVersion() != null) {
            entity.setFrozenVersion(dto.getFrozenVersion());
        }
        if (dto.getSnapshotData() != null) {
            entity.setSnapshotData(dto.getSnapshotData());
        }
        if (dto.getModifiedAt() != null) {
            entity.setModifiedAt(dto.getModifiedAt());
        }
    }
}
