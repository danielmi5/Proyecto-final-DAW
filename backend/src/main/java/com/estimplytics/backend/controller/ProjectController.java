package com.estimplytics.backend.controller;

import com.estimplytics.backend.dto.ProjectRequestDTO;
import com.estimplytics.backend.dto.ProjectResponseDTO;
import com.estimplytics.backend.dto.ProjectUpdateDTO;
import com.estimplytics.backend.service.IProjectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ProjectController implements IProjectController {

    private final IProjectService projectService;

    public ProjectController(IProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public ResponseEntity<Page<ProjectResponseDTO>> getAll(Pageable pageable) {
        return ResponseEntity.ok(projectService.findAll(pageable));
    }

    @Override
    public ResponseEntity<ProjectResponseDTO> getById(UUID id) {
        return projectService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<ProjectResponseDTO> create(ProjectRequestDTO request) {
        ProjectResponseDTO response = projectService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ProjectResponseDTO> update(UUID id, ProjectUpdateDTO request) {
        return ResponseEntity.ok(projectService.update(id, request));
    }

    @Override
    public ResponseEntity<Void> delete(UUID id) {
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
