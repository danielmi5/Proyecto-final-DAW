package com.estimplytics.backend.service;

import com.estimplytics.backend.dto.ProjectRequestDTO;
import com.estimplytics.backend.dto.ProjectResponseDTO;
import com.estimplytics.backend.dto.ProjectUpdateDTO;
import com.estimplytics.backend.entity.Project;
import com.estimplytics.backend.exception.ProjectNotFoundException;
import com.estimplytics.backend.mapper.ProjectMapper;
import com.estimplytics.backend.repository.ProjectRepository;
import com.estimplytics.backend.repository.RequestRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectService implements IProjectService {

    private final ProjectRepository projectRepository;
    private final RequestRepository requestRepository;
    private final ProjectMapper projectMapper;

    public ProjectService(ProjectRepository projectRepository,
                          RequestRepository requestRepository,
                          ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.requestRepository = requestRepository;
        this.projectMapper = projectMapper;
    }

    @Override
    public Page<ProjectResponseDTO> findAll(Pageable pageable) {
        return projectRepository.findAll(pageable).map(projectMapper::toResponseDTO);
    }

    @Override
    public Optional<ProjectResponseDTO> findById(UUID id) {
        return projectRepository.findById(id).map(projectMapper::toResponseDTO);
    }

    @Override
    @Transactional
    public ProjectResponseDTO create(ProjectRequestDTO dto) {
        Project entity = projectMapper.toEntity(dto);
        Project savedEntity = projectRepository.save(entity);
        return projectMapper.toResponseDTO(savedEntity);
    }

    @Override
    @Transactional
    public ProjectResponseDTO update(UUID id, ProjectUpdateDTO dto) {
        return projectRepository.findById(id).map(entity -> {
            projectMapper.updateEntityFromDTO(dto, entity);
            return projectMapper.toResponseDTO(projectRepository.save(entity));
        }).orElseThrow(() -> new ProjectNotFoundException("Project not found with id %s".formatted(id)));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (!projectRepository.existsById(id)) {
            throw new ProjectNotFoundException("Project not found with id %s".formatted(id));
        }
        if (requestRepository.existsByProjectId(id)) {
            throw new IllegalArgumentException("Cannot delete project with associated manual requests");
        }
        projectRepository.deleteById(id);
    }
}
