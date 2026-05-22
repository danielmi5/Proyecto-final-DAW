package com.estimplytics.backend.controller;

import com.estimplytics.backend.dto.RequestRequestDTO;
import com.estimplytics.backend.dto.RequestResponseDTO;
import com.estimplytics.backend.dto.RequestUpdateDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.UUID;

@RequestMapping("/api/requests")
public interface IRequestController extends ICrudController<RequestRequestDTO, RequestResponseDTO, RequestUpdateDTO, UUID> {
}
