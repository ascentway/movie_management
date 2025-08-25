package com.movie.management.movieManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleMovieNotFoundException(MovieNotFoundException ex){
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.NOT_FOUND);
        body.put("error","Movie Not Found");
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
    }


}
