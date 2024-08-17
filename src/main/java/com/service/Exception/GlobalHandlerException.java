package com.service.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity UrlNullException(NullPointerException nullPointerException){

        return new ResponseEntity(nullPointerException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity urlNotAllowed(IllegalArgumentException ilegalException){

        return new ResponseEntity(ilegalException.getMessage(), HttpStatus.NOT_FOUND);
    }
}