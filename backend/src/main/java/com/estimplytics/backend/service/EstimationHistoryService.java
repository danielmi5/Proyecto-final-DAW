package com.estimplytics.backend.service;

import com.estimplytics.backend.dto.EstimationHistoryRequestDTO;
import com.estimplytics.backend.dto.EstimationHistoryResponseDTO;
import com.estimplytics.backend.dto.EstimationHistoryUpdateDTO;
import com.estimplytics.backend.entity.EstimationHistory;
import com.estimplytics.backend.mapper.EstimationHistoryMapper;
import com.estimplytics.backend.repository.EstimationHistoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EstimationHistoryService implements IEstimationHistoryService {

    private final EstimationHistoryRepository repository;
    private final EstimationHistoryMapper mapper;

    public EstimationHistoryService(EstimationHistoryRepository repository, EstimationHistoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<EstimationHistoryResponseDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toResponseDTO);
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