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
                .category(dto.getCategory())
                .active(dto.getActive())
                .build();
    }

    @Override
    public ComponentResponseDTO toResponseDTO(Component entity) {
        if (entity == null) return null;
        return ComponentResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .category(entity.getCategory())
                .active(entity.getActive())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    @Override
    public void updateEntityFromDTO(ComponentUpdateDTO dto, Component entity) {
        if (dto == null || entity == null) return;
        if (dto.getName() != null) {
            entity.setName(dto.getName());
        }
        if (dto.getCategory() != null) {
            entity.setCategory(dto.getCategory());
        }

        if (dto.getActive() != null){
            entity.setActive(dto.getActive());
        }
    }
}