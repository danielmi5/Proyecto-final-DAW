package com.estimplytics.backend.service;

import com.estimplytics.backend.dto.EstimationRequestDTO;
import com.estimplytics.backend.dto.EstimationResponseDTO;
import com.estimplytics.backend.dto.EstimationUpdateDTO;
import com.estimplytics.backend.entity.Estimation;
import com.estimplytics.backend.exception.EstimationNotFoundException;
import com.estimplytics.backend.mapper.EstimationMapper;
import com.estimplytics.backend.repository.EstimationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EstimationService implements IEstimationService {

    private final EstimationRepository repository;
    private final EstimationMapper mapper;

    public EstimationService(EstimationRepository repository, EstimationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<EstimationResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EstimationResponseDTO> findById(UUID id) {
        return repository.findById(id).map(mapper::toResponseDTO);
    }

    @Override
    @Transactional
    public EstimationResponseDTO create(EstimationRequestDTO dto) {
        Estimation entity = mapper.toEntity(dto);
        Estimation savedEntity = repository.save(entity);
        return mapper.toResponseDTO(savedEntity);
    }

    @Override
    @Transactional
    public EstimationResponseDTO update(UUID id, EstimationUpdateDTO dto) {
        return repository.findById(id).map(entity -> {
            mapper.updateEntityFromDTO(dto, entity);
            return mapper.toResponseDTO(repository.save(entity));
        }).orElseThrow(() -> new EstimationNotFoundException("Estimation not found with id %s".formatted(id)));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new EstimationNotFoundException("Estimation not found with id %s".formatted(id));
        }
        repository.deleteById(id);
    }
}