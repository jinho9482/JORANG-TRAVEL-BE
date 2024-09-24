package com.example.travel_diary.global.exception;

public class ExpenseNotFoundException extends IllegalArgumentException{
    public ExpenseNotFoundException() {
        super("Expense not found.");
    }
}
