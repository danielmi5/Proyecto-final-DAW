package com.estimplytics.backend.controller;

import com.estimplytics.backend.dto.ProjectRequestDTO;
import com.estimplytics.backend.dto.ProjectResponseDTO;
import com.estimplytics.backend.dto.ProjectUpdateDTO;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RequestMapping("/api/projects")
public interface IProjectController extends ICrudController<ProjectRequestDTO, ProjectResponseDTO, ProjectUpdateDTO, UUID> {
}
