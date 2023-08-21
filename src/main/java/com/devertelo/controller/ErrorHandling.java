package com.devertelo.controller;

import com.devertelo.application.exceptions.AlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ErrorHandling {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({MethodArgumentNotValidException.class, AlreadyExistsException.class})
    public String handleConstraintViolation(MethodArgumentNotValidException exception) {
        return "Request Inválida";
    }


//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(AlreadyExistsException.class)
//    public String handleConstraintViolation(AlreadyExistsException exception) {
//
//        return "Request Inválida";
//    }


}
