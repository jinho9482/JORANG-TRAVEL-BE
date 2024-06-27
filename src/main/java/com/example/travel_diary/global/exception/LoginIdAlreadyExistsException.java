package com.example.travel_diary.global.exception;

public class LoginIdAlreadyExistsException extends IllegalArgumentException{
    public LoginIdAlreadyExistsException() {
        super("Login ID already exists.");
    }
}
