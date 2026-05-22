package com.estimplytics.backend.service;

import com.estimplytics.backend.dto.ProjectRequestDTO;
import com.estimplytics.backend.dto.ProjectResponseDTO;
import com.estimplytics.backend.dto.ProjectUpdateDTO;

import java.util.UUID;

public interface IProjectService extends ICrudService<ProjectRequestDTO, ProjectResponseDTO, ProjectUpdateDTO, UUID> {
}
