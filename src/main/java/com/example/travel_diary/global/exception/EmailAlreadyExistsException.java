package com.example.travel_diary.global.exception;

public class EmailAlreadyExistsException extends IllegalArgumentException{
    public EmailAlreadyExistsException() {
        super("Email already exists.");
    }
}
