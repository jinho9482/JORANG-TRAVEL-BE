package com.example.travel_diary.global.exception;


public class PhotoNotFoundException extends IllegalArgumentException{
    public PhotoNotFoundException() {
        super("Photo not found.");
    }
}
