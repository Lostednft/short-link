package com.Exception;

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
}