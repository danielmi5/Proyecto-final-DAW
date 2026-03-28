package com.estimplytics.backend.exception;

import com.estimplytics.backend.dto.ApiErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorDTO> handleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(createErrorBody(status, request, e, "Invalid parameter"));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiErrorDTO> handleUserNotFoundException(UserNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(createErrorBody(status, request, e, "User not found"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorDTO> handleValidationException(MethodArgumentNotValidException e, HttpServletRequest request) {
        List<String> errors = e.getBindingResult()
                .getAllErrors()
                .stream().map(error -> error.getDefaultMessage()).toList();

        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(createErrorBody(status, request, e, errors.toString()));
    }

    private ApiErrorDTO createErrorBody(HttpStatus status, HttpServletRequest request, Exception e, String desc) {
        String message;
        if (e instanceof MethodArgumentNotValidException) {
            message = "The provided data is not valid";
        } else {
            message = e.getMessage();
        }

        return ApiErrorDTO.builder()
                .timestamp(LocalDateTime.now())
                .stateNum(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .description(desc)
                .path(request.getRequestURI())
                .build();
    }
}