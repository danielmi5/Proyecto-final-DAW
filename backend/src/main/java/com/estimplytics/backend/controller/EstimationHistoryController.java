package com.estimplytics.backend.controller;

import com.estimplytics.backend.dto.EstimationHistoryRequestDTO;
import com.estimplytics.backend.dto.EstimationHistoryResponseDTO;
import com.estimplytics.backend.dto.EstimationHistoryUpdateDTO;
import com.estimplytics.backend.service.IEstimationHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EstimationHistoryController implements IEstimationHistoryController {

    private final IEstimationHistoryService service;

    public EstimationHistoryController(IEstimationHistoryService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<EstimationHistoryResponseDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Override
    public ResponseEntity<EstimationHistoryResponseDTO> getById(Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<EstimationHistoryResponseDTO> create(EstimationHistoryRequestDTO request) {
        EstimationHistoryResponseDTO response = service.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<EstimationHistoryResponseDTO> update(Long id, EstimationHistoryUpdateDTO request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}