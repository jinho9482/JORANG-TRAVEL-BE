package com.example.travel_diary.global.request;

import com.example.travel_diary.global.domain.entity.Expense;
import com.example.travel_diary.global.domain.entity.ExpenseDetail;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ExpenseDetailRequestDto(
        Long expenseId,
        int cost,
        String place,
        String category,
        String scope,
        String country

) {
    public ExpenseDetail toEntity() {
        Expense expense = Expense.builder().id(this.expenseId).build();
        return ExpenseDetail.builder()
                .expense(expense)
                .cost(this.cost)
                .place(this.place)
                .category(this.category)
                .scope(this.scope)
                .country(this.country)
                .createdAt(LocalDateTime.now())

                .build();
    }
}
