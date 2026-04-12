package com.estimplytics.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface ICrudController<Request, Response, Update, ID> {
    
    @GetMapping
    @Operation(summary = "Obtener todos los registros", description = "Retorna una página con todos los registros disponibles")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Registros obtenidos exitosamente", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "401", description = "No autenticado"),
        @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    ResponseEntity<Page<Response>> getAll(Pageable pageable);
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un registro por ID", description = "Retorna un registro específico por su identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Registro encontrado", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "404", description = "Registro no encontrado"),
        @ApiResponse(responseCode = "401", description = "No autenticado")
    })
    ResponseEntity<Response> getById(@PathVariable ID id);
    
    @PostMapping
    @Operation(summary = "Crear un nuevo registro", description = "Crea un nuevo registro con los datos proporcionados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Registro creado exitosamente", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "401", description = "No autenticado")
    })
    ResponseEntity<Response> create(@RequestBody Request request);
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un registro", description = "Actualiza un registro existente con los nuevos datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Registro actualizado exitosamente", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "404", description = "Registro no encontrado"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "401", description = "No autenticado")
    })
    ResponseEntity<Response> update(@PathVariable ID id, @RequestBody Update updateRequest);
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un registro", description = "Elimina un registro específico por su identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Registro eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Registro no encontrado"),
        @ApiResponse(responseCode = "401", description = "No autenticado")
    })
    ResponseEntity<Void> delete(@PathVariable ID id);
}
