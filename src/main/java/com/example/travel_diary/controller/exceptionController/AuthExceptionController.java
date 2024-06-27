package com.example.travel_diary.controller.exceptionController;

import com.example.travel_diary.global.exception.EmailAlreadyExistsException;
import com.example.travel_diary.global.exception.LoginFailedException;
import com.example.travel_diary.global.exception.LoginIdAlreadyExistsException;
import com.example.travel_diary.global.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionController {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userNotFoundExceptionHandler(UserNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(LoginFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String LoginFailedExceptionHandler(LoginFailedException e) {
        return e.getMessage();
    }

    @ExceptionHandler(LoginIdAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String LoginIdAlreadyExistsExceptionHandler(LoginIdAlreadyExistsException e) {
        return e.getMessage();
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String EmailAlreadyExistsExceptionHandler(EmailAlreadyExistsException e) {
        return e.getMessage();
    }

}
