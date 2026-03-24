package com.estimplytics.backend.service;

import java.util.List;
import java.util.Optional;

public interface ICrudService<Request, Response, Update, ID> {
    List<Response> findAll();
    Optional<Response> findById(ID id);
    Response create(Request request);
    Response update(ID id, Update updateRequest);
    void delete(ID id);
}
