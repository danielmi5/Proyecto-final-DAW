package com.estimplytics.backend.mapper;

import com.estimplytics.backend.dto.RequestRequestDTO;
import com.estimplytics.backend.dto.RequestResponseDTO;
import com.estimplytics.backend.dto.RequestUpdateDTO;
import com.estimplytics.backend.entity.Request;
import org.springframework.stereotype.Component;

@Component
public class RequestMapper implements IMapper<Request, RequestRequestDTO, RequestResponseDTO, RequestUpdateDTO> {

    @Override
    public Request toEntity(RequestRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        return Request.builder()
                .redmineId(dto.getRedmineId())
                .originRequestCode(dto.getOriginRequestCode())
                .projectName(dto.getProjectName())
                .demandType(dto.getDemandType())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .priority(dto.getPriority())
                .assigneeName(dto.getAssigneeName())
                .authorName(dto.getAuthorName())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .redmineCreatedDate(dto.getRedmineCreatedDate())
                .redmineUpdatedDate(dto.getRedmineUpdatedDate())
                .redmineClosedDate(dto.getRedmineClosedDate())
                .doneRatio(dto.getDoneRatio())
                .estimatedHours(dto.getEstimatedHours())
                .spentHours(dto.getSpentHours())
                .createdDate(dto.getCreatedDate())
                .build();
    }

    @Override
    public RequestResponseDTO toResponseDTO(Request entity) {
        if (entity == null) {
            return null;
        }
        return RequestResponseDTO.builder()
                .id(entity.getId())
                .redmineId(entity.getRedmineId())
                .originRequestCode(entity.getOriginRequestCode())
                .projectName(entity.getProjectName())
                .demandType(entity.getDemandType())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .priority(entity.getPriority())
                .assigneeName(entity.getAssigneeName())
                .authorName(entity.getAuthorName())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .redmineCreatedDate(entity.getRedmineCreatedDate())
                .redmineUpdatedDate(entity.getRedmineUpdatedDate())
                .redmineClosedDate(entity.getRedmineClosedDate())
                .doneRatio(entity.getDoneRatio())
                .estimatedHours(entity.getEstimatedHours())
                .spentHours(entity.getSpentHours())
                .createdDate(entity.getCreatedDate())
                .build();
    }

    @Override
    public void updateEntityFromDTO(RequestUpdateDTO dto, Request entity) {
        if (dto == null || entity == null) {
            return;
        }
        if (dto.getTitle() != null) {
            entity.setTitle(dto.getTitle());
        }
        if (dto.getDescription() != null) {
            entity.setDescription(dto.getDescription());
        }
        if (dto.getStatus() != null) {
            entity.setStatus(dto.getStatus());
        }
    }
}
