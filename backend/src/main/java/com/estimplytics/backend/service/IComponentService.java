package com.estimplytics.backend.service;

import com.estimplytics.backend.dto.ComponentRequestDTO;
import com.estimplytics.backend.dto.ComponentResponseDTO;
import com.estimplytics.backend.dto.ComponentUpdateDTO;
import java.util.UUID;

public interface IComponentService extends ICrudService<ComponentRequestDTO, ComponentResponseDTO, ComponentUpdateDTO, UUID> {
}