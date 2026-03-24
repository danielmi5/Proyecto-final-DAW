package com.estimplytics.backend.controller;

import com.estimplytics.backend.dto.UserRequestDTO;
import com.estimplytics.backend.dto.UserResponseDTO;
import com.estimplytics.backend.dto.UserUpdateDTO;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RequestMapping("/api/users")
public interface IUserController extends ICrudController<UserRequestDTO, UserResponseDTO, UserUpdateDTO, UUID> {
}
