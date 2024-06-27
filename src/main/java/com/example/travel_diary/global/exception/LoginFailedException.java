package com.example.travel_diary.global.exception;

public class LoginFailedException extends IllegalArgumentException{
    public LoginFailedException() {
        super("Login failed.");
    }
}
