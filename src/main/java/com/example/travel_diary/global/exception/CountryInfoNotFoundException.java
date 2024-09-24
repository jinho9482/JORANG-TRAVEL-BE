package com.example.travel_diary.global.exception;

public class CountryInfoNotFoundException extends IllegalArgumentException{
    public CountryInfoNotFoundException() {
        super("Country info not found.");
    }
}
