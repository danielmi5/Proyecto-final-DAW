package com.estimplytics.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ICrudController<Request, Response, Update, ID> {
    
    @GetMapping
    ResponseEntity<List<Response>> getAll();
    
    @GetMapping("/{id}")
    ResponseEntity<Response> getById(@PathVariable ID id);
    
    @PostMapping
    ResponseEntity<Response> create(@RequestBody Request request);
    
    @PutMapping("/{id}")
    ResponseEntity<Response> update(@PathVariable ID id, @RequestBody Update updateRequest);
    
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable ID id);
}
