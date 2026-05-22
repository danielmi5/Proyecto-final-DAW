package com.estimplytics.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface ICrudService<Request, Response, Update, ID> {
    Page<Response> findAll(Pageable pageable);
    Optional<Response> findById(ID id);
    Response create(Request request);
    Response update(ID id, Update updateRequest);
    void delete(ID id);
}
