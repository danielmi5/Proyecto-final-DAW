package com.estimplytics.backend.controller;

import com.estimplytics.backend.dto.ComponentRequestDTO;
import com.estimplytics.backend.dto.ComponentResponseDTO;
import com.estimplytics.backend.dto.ComponentUpdateDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.UUID;

@RequestMapping("/api/components")
public interface IComponentController extends ICrudController<ComponentRequestDTO, ComponentResponseDTO, ComponentUpdateDTO, UUID> {
}