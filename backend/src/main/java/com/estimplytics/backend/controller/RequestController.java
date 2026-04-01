package com.estimplytics.backend.controller;

import com.estimplytics.backend.dto.RequestRequestDTO;
import com.estimplytics.backend.dto.RequestResponseDTO;
import com.estimplytics.backend.dto.RequestUpdateDTO;
import com.estimplytics.backend.service.IRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class RequestController implements IRequestController {

    private final IRequestService requestService;

    public RequestController(IRequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public ResponseEntity<List<RequestResponseDTO>> getAll() {
        return ResponseEntity.ok(requestService.findAll());
    }

    @Override
    public ResponseEntity<RequestResponseDTO> getById(UUID id) {
        return requestService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<RequestResponseDTO> create(RequestRequestDTO request) {
        RequestResponseDTO response = requestService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<RequestResponseDTO> update(UUID id, RequestUpdateDTO request) {
        RequestResponseDTO response = requestService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> delete(UUID id) {
        requestService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
