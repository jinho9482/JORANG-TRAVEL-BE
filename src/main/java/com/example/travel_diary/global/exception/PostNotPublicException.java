package com.example.travel_diary.global.exception;

public class PostNotPublicException extends IllegalArgumentException{
    public PostNotPublicException() {
        super("This post is not public.");
    }
}
