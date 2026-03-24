package com.estimplytics.backend.mapper;

import com.estimplytics.backend.dto.UserRequestDTO;
import com.estimplytics.backend.dto.UserResponseDTO;
import com.estimplytics.backend.dto.UserUpdateDTO;
import com.estimplytics.backend.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDTO toResponseDTO(User user) {
        if (user == null) {
            return null;
        }
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public User toEntity(UserRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }
        return User.builder()
                .name(requestDTO.getName())
                .email(requestDTO.getEmail())
                .passwordHash(requestDTO.getPassword())
                .role(requestDTO.getRole())
                .build();
    }

    public void updateEntityFromDTO(UserUpdateDTO updateDTO, User user) {
        if (updateDTO == null || user == null) {
            return;
        }
        if (updateDTO.getName() != null) {
            user.setName(updateDTO.getName());
        }
        if (updateDTO.getEmail() != null) {
            user.setEmail(updateDTO.getEmail());
        }
        if (updateDTO.getPassword() != null) {
            user.setPasswordHash(updateDTO.getPassword());
        }
        if (updateDTO.getRole() != null) {
            user.setRole(updateDTO.getRole());
        }
    }
}