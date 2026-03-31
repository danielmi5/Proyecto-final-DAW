package com.estimplytics.backend.mapper;

import com.estimplytics.backend.dto.ComponentRequestDTO;
import com.estimplytics.backend.dto.ComponentResponseDTO;
import com.estimplytics.backend.dto.ComponentUpdateDTO;
import com.estimplytics.backend.entity.Component;
import org.springframework.stereotype.Service;

@Service
public class ComponentMapper implements IMapper<Component, ComponentRequestDTO, ComponentResponseDTO, ComponentUpdateDTO> {

    @Override
    public Component toEntity(ComponentRequestDTO dto) {
        if (dto == null) return null;
        return Component.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }

    @Override
    public ComponentResponseDTO toResponseDTO(Component entity) {
        if (entity == null) return null;
        return ComponentResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    @Override
    public void updateEntityFromDTO(ComponentUpdateDTO dto, Component entity) {
        if (dto == null || entity == null) return;
        if (dto.getName() != null) {
            entity.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            entity.setDescription(dto.getDescription());
        }
    }
}