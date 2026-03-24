package com.estimplytics.backend.mapper;

public interface IMapper<Entity, Request, Response, Update> {
    Response toResponseDTO(Entity entity);
    Entity toEntity(Request createDto);
    void updateEntityFromDTO(Update updateDto, Entity entity);
}
