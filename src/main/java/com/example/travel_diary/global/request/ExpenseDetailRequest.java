package com.example.travel_diary.global.request;

import com.example.travel_diary.global.domain.entity.Expense;
import com.example.travel_diary.global.domain.entity.ExpenseDetail;

import java.time.LocalDateTime;

public record ExpenseDetailRequest(
        int cost,
        String place,
        String category

//        Expense expense

) {
    public ExpenseDetail toEntity(Long expenseId) {
        Expense expense = Expense.builder().id(expenseId).build();
        return ExpenseDetail.builder()
                .cost(cost)
                .place(place)
                .category(category)
                .createdAt(LocalDateTime.now())
                .expense(expense)
                .build();
    }
}