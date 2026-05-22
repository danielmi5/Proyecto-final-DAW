package com.estimplytics.backend.service;

import com.estimplytics.backend.dto.UserRequestDTO;
import com.estimplytics.backend.dto.UserResponseDTO;
import com.estimplytics.backend.dto.UserUpdateDTO;
import com.estimplytics.backend.entity.User;
import com.estimplytics.backend.mapper.UserMapper;
import com.estimplytics.backend.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;
import com.estimplytics.backend.exception.UserNotFoundException;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Page<UserResponseDTO> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toResponseDTO);
    }

    @Override
    public Optional<UserResponseDTO> findById(UUID id) {
        return userRepository.findById(id).map(userMapper::toResponseDTO);
    }

    @Override
    @Transactional
    public UserResponseDTO create(UserRequestDTO request) {
        User user = userMapper.toEntity(request);
        User savedUser = userRepository.save(user);
        return userMapper.toResponseDTO(savedUser);
    }

    @Override
    @Transactional
    public UserResponseDTO update(UUID id, UserUpdateDTO request) {
        return userRepository.findById(id).map(user -> {
            userMapper.updateEntityFromDTO(request, user);
            return userMapper.toResponseDTO(userRepository.save(user));
        }).orElseThrow(() -> new UserNotFoundException("User not found with id %s".formatted(id)));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with id %s".formatted(id));
        }
        userRepository.deleteById(id);
    }
}
