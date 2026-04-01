package com.estimplytics.backend.service;

import com.estimplytics.backend.dto.ImpactAnalysisRequestDTO;
import com.estimplytics.backend.dto.ImpactAnalysisResponseDTO;
import com.estimplytics.backend.dto.ImpactAnalysisUpdateDTO;
import com.estimplytics.backend.entity.ImpactAnalysis;
import com.estimplytics.backend.exception.ImpactAnalysisNotFoundException;
import com.estimplytics.backend.mapper.ImpactAnalysisMapper;
import com.estimplytics.backend.repository.ImpactAnalysisRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ImpactAnalysisService implements IImpactAnalysisService {

    private final ImpactAnalysisRepository repository;
    private final ImpactAnalysisMapper mapper;

    public ImpactAnalysisService(ImpactAnalysisRepository repository, ImpactAnalysisMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<ImpactAnalysisResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ImpactAnalysisResponseDTO> findById(UUID id) {
        return repository.findById(id).map(mapper::toResponseDTO);
    }

    @Override
    @Transactional
    public ImpactAnalysisResponseDTO create(ImpactAnalysisRequestDTO dto) {
        ImpactAnalysis entity = mapper.toEntity(dto);
        ImpactAnalysis savedEntity = repository.save(entity);
        return mapper.toResponseDTO(savedEntity);
    }

    @Override
    @Transactional
    public ImpactAnalysisResponseDTO update(UUID id, ImpactAnalysisUpdateDTO dto) {
        return repository.findById(id).map(entity -> {
            mapper.updateEntityFromDTO(dto, entity);
            return mapper.toResponseDTO(repository.save(entity));
        }).orElseThrow(() -> new ImpactAnalysisNotFoundException("ImpactAnalysis not found with id %s".formatted(id)));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ImpactAnalysisNotFoundException("ImpactAnalysis not found with id %s".formatted(id));
        }
        repository.deleteById(id);
    }
}