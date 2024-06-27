package com.example.travel_diary.controller.exceptionController;

import com.example.travel_diary.global.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DiaryExceptionController {

    @ExceptionHandler(DiaryNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String diaryNotFoundExceptionHandler(DiaryNotFoundException e) {
        return e.getMessage();
    }

}
