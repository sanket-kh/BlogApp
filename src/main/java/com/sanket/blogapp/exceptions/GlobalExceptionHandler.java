package com.sanket.blogapp.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //  the type of exception whenever thrown, this method handles the exception and sends the response
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String , String >> beanValidationExceptionHandler(MethodArgumentNotValidException ex){
        Map<String, String> map = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            map.put(fieldName, message);
        });
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);



    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> BadCredentialsExceptionHandler(BadCredentialsException ex){
        String message= ex.getMessage();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);

    }



}
