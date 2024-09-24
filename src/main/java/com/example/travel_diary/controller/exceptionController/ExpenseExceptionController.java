package com.example.travel_diary.controller.exceptionController;

import com.example.travel_diary.global.exception.DiaryNotFoundException;
import com.example.travel_diary.global.exception.ExpenseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExpenseExceptionController {

    @ExceptionHandler(ExpenseNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String diaryNotFoundExceptionHandler(ExpenseNotFoundException e) {
        return e.getMessage();
    }

}