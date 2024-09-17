package com.example.travel_diary.global.exception;

public class PhotoLimitExceededException extends IllegalArgumentException{
    public PhotoLimitExceededException() {
        super("Photos are up to 5");
    }
}