package com.example.travel_diary.controller.exceptionController;

import com.example.travel_diary.global.exception.ExpenseDetailNotFoundException;
import com.example.travel_diary.global.exception.PostNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExpenseDetailExceptionController {


    @ExceptionHandler(ExpenseDetailNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String expenseDetailNotFoundExceptionHandler(ExpenseDetailNotFoundException e) {
        return e.getMessage();
    }

}
