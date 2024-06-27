package com.example.travel_diary.global.exception;

public class DiaryNotFoundException extends IllegalArgumentException{
    public DiaryNotFoundException() {
        super("Diary not found.");
    }
}
