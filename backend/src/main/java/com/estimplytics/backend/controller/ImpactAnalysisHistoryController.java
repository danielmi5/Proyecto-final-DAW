package com.estimplytics.backend.controller;

import com.estimplytics.backend.dto.ImpactAnalysisHistoryRequestDTO;
import com.estimplytics.backend.dto.ImpactAnalysisHistoryResponseDTO;
import com.estimplytics.backend.dto.ImpactAnalysisHistoryUpdateDTO;
import com.estimplytics.backend.service.IImpactAnalysisHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ImpactAnalysisHistoryController implements IImpactAnalysisHistoryController {

    private final IImpactAnalysisHistoryService service;

    public ImpactAnalysisHistoryController(IImpactAnalysisHistoryService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<ImpactAnalysisHistoryResponseDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Override
    public ResponseEntity<ImpactAnalysisHistoryResponseDTO> getById(Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<ImpactAnalysisHistoryResponseDTO> create(ImpactAnalysisHistoryRequestDTO request) {
        ImpactAnalysisHistoryResponseDTO response = service.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ImpactAnalysisHistoryResponseDTO> update(Long id, ImpactAnalysisHistoryUpdateDTO request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}