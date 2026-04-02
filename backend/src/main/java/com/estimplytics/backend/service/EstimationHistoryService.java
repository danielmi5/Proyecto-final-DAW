package com.estimplytics.backend.service;

import com.estimplytics.backend.dto.EstimationHistoryRequestDTO;
import com.estimplytics.backend.dto.EstimationHistoryResponseDTO;
import com.estimplytics.backend.dto.EstimationHistoryUpdateDTO;
import com.estimplytics.backend.entity.EstimationHistory;
import com.estimplytics.backend.mapper.EstimationHistoryMapper;
import com.estimplytics.backend.repository.EstimationHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstimationHistoryService implements IEstimationHistoryService {

    private final EstimationHistoryRepository repository;
    private final EstimationHistoryMapper mapper;

    public EstimationHistoryService(EstimationHistoryRepository repository, EstimationHistoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<EstimationHistoryResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EstimationHistoryResponseDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toResponseDTO);
    }

    @Override
    @Transactional
    public EstimationHistoryResponseDTO create(EstimationHistoryRequestDTO dto) {
        EstimationHistory entity = mapper.toEntity(dto);
        EstimationHistory savedEntity = repository.save(entity);
        return mapper.toResponseDTO(savedEntity);
    }

    @Override
    @Transactional
    public EstimationHistoryResponseDTO update(Long id, EstimationHistoryUpdateDTO dto) {
        throw new UnsupportedOperationException("Update not supported for history");
    }

    @Override
    @Transactional
    public void delete(Long id) {
        throw new UnsupportedOperationException("Delete not supported for history");
    }
}