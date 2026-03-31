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
                .title(dto.getTitle())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .build();
    }

    @Override
    public RequestResponseDTO toResponseDTO(Request entity) {
        if (entity == null) {
            return null;
        }
        return RequestResponseDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
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
