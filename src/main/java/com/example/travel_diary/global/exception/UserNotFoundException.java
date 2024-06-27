package com.example.travel_diary.global.exception;

public class UserNotFoundException extends IllegalArgumentException{
    public UserNotFoundException() {
        super("User does not exist.");
    }
}




