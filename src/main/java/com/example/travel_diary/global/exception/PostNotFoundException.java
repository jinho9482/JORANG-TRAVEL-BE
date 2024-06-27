package com.example.travel_diary.global.exception;

public class PostNotFoundException extends IllegalArgumentException{
    public PostNotFoundException() {
        super("Post not found.");
    }
}
