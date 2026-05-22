package com.estimplytics.backend.service;

import com.estimplytics.backend.dto.UserRequestDTO;
import com.estimplytics.backend.dto.UserResponseDTO;
import com.estimplytics.backend.dto.UserUpdateDTO;

import java.util.UUID;

public interface IUserService extends ICrudService<UserRequestDTO, UserResponseDTO, UserUpdateDTO, UUID> {
}
