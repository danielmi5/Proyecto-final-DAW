package com.estimplytics.backend.service;

import com.estimplytics.backend.dto.ImpactAnalysisHistoryRequestDTO;
import com.estimplytics.backend.dto.ImpactAnalysisHistoryResponseDTO;
import com.estimplytics.backend.dto.ImpactAnalysisHistoryUpdateDTO;
import com.estimplytics.backend.entity.ImpactAnalysisHistory;
import com.estimplytics.backend.mapper.ImpactAnalysisHistoryMapper;
import com.estimplytics.backend.repository.ImpactAnalysisHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImpactAnalysisHistoryService implements IImpactAnalysisHistoryService {

    private final ImpactAnalysisHistoryRepository repository;
    private final ImpactAnalysisHistoryMapper mapper;

    public ImpactAnalysisHistoryService(ImpactAnalysisHistoryRepository repository, ImpactAnalysisHistoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<ImpactAnalysisHistoryResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ImpactAnalysisHistoryResponseDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toResponseDTO);
    }

    @Override
    @Transactional
    public ImpactAnalysisHistoryResponseDTO create(ImpactAnalysisHistoryRequestDTO dto) {
        ImpactAnalysisHistory entity = mapper.toEntity(dto);
        ImpactAnalysisHistory savedEntity = repository.save(entity);
        return mapper.toResponseDTO(savedEntity);
    }

    @Override
    @Transactional
    public ImpactAnalysisHistoryResponseDTO update(Long id, ImpactAnalysisHistoryUpdateDTO dto) {
        throw new UnsupportedOperationException("Update not supported for history");
    }

    @Override
    @Transactional
    public void delete(Long id) {
        throw new UnsupportedOperationException("Delete not supported for history");
    }
}