package com.estimplytics.backend.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface ICrudController<Request, Response, Update, ID> {
    
    @GetMapping
    ResponseEntity<Page<Response>> getAll(Pageable pageable);
    
    @GetMapping("/{id}")
    ResponseEntity<Response> getById(@PathVariable ID id);
    
    @PostMapping
    ResponseEntity<Response> create(@RequestBody Request request);
    
    @PutMapping("/{id}")
    ResponseEntity<Response> update(@PathVariable ID id, @RequestBody Update updateRequest);
    
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable ID id);
}
