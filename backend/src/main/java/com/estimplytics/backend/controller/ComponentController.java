package com.estimplytics.backend.controller;

import com.estimplytics.backend.dto.ComponentRequestDTO;
import com.estimplytics.backend.dto.ComponentResponseDTO;
import com.estimplytics.backend.dto.ComponentUpdateDTO;
import com.estimplytics.backend.service.IComponentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class ComponentController implements IComponentController {

    private final IComponentService service;

    public ComponentController(IComponentService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<ComponentResponseDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Override
    public ResponseEntity<ComponentResponseDTO> getById(UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<ComponentResponseDTO> create(ComponentRequestDTO request) {
        ComponentResponseDTO response = service.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ComponentResponseDTO> update(UUID id, ComponentUpdateDTO request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @Override
    public ResponseEntity<Void> delete(UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}