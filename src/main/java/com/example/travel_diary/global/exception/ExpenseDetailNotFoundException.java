package com.example.travel_diary.global.exception;

public class ExpenseDetailNotFoundException extends IllegalArgumentException{
    public ExpenseDetailNotFoundException() {
        super("Expense detail does not exist.");
    }
}




