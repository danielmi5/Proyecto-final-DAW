package com.estimplytics.backend.service;

import com.estimplytics.backend.dto.ComponentRequestDTO;
import com.estimplytics.backend.dto.ComponentResponseDTO;
import com.estimplytics.backend.dto.ComponentUpdateDTO;
import com.estimplytics.backend.entity.Component;
import com.estimplytics.backend.exception.ComponentNotFoundException;
import com.estimplytics.backend.mapper.ComponentMapper;
import com.estimplytics.backend.repository.ComponentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class ComponentService implements IComponentService {

    private final ComponentRepository repository;
    private final ComponentMapper mapper;

    public ComponentService(ComponentRepository repository, ComponentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<ComponentResponseDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toResponseDTO);
    }

    @Override
    public Optional<ComponentResponseDTO> findById(UUID id) {
        return repository.findById(id).map(mapper::toResponseDTO);
    }

    @Override
    @Transactional
    public ComponentResponseDTO create(ComponentRequestDTO dto) {
        Component entity = mapper.toEntity(dto);
        Component savedEntity = repository.save(entity);
        return mapper.toResponseDTO(savedEntity);
    }

    @Override
    @Transactional
    public ComponentResponseDTO update(UUID id, ComponentUpdateDTO dto) {
        return repository.findById(id).map(entity -> {
            mapper.updateEntityFromDTO(dto, entity);
            return mapper.toResponseDTO(repository.save(entity));
        }).orElseThrow(() -> new ComponentNotFoundException("Component not found with id %s".formatted(id)));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ComponentNotFoundException("Component not found with id %s".formatted(id));
        }
        repository.deleteById(id);
    }
}