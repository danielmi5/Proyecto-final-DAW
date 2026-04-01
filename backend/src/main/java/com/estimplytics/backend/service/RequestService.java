package com.estimplytics.backend.service;

import com.estimplytics.backend.dto.RequestRequestDTO;
import com.estimplytics.backend.dto.RequestResponseDTO;
import com.estimplytics.backend.dto.RequestUpdateDTO;
import com.estimplytics.backend.entity.Request;
import com.estimplytics.backend.exception.RequestNotFoundException;
import com.estimplytics.backend.mapper.RequestMapper;
import com.estimplytics.backend.repository.RequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RequestService implements IRequestService {

    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;

    public RequestService(RequestRepository requestRepository, RequestMapper requestMapper) {
        this.requestRepository = requestRepository;
        this.requestMapper = requestMapper;
    }

    @Override
    public List<RequestResponseDTO> findAll() {
        return requestRepository.findAll().stream()
                .map(requestMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RequestResponseDTO> findById(UUID id) {
        return requestRepository.findById(id).map(requestMapper::toResponseDTO);
    }

    @Override
    @Transactional
    public RequestResponseDTO create(RequestRequestDTO dto) {
        Request entity = requestMapper.toEntity(dto);
        Request savedEntity = requestRepository.save(entity);
        return requestMapper.toResponseDTO(savedEntity);
    }

    @Override
    @Transactional
    public RequestResponseDTO update(UUID id, RequestUpdateDTO dto) {
        return requestRepository.findById(id).map(entity -> {
            requestMapper.updateEntityFromDTO(dto, entity);
            return requestMapper.toResponseDTO(requestRepository.save(entity));
        }).orElseThrow(() -> new RequestNotFoundException("Request not found with id %s".formatted(id)));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (!requestRepository.existsById(id)) {
            throw new RequestNotFoundException("Request not found with id %s".formatted(id));
        }
        requestRepository.deleteById(id);
    }
}
