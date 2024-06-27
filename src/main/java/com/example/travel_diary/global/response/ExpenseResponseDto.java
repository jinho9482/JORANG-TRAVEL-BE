package com.example.travel_diary.global.response;

import com.example.travel_diary.global.domain.entity.Expense;

import java.time.LocalDate;
import java.util.UUID;

public record ExpenseResponseDto (
        Long id,
    LocalDate date

    ){
    public static ExpenseResponseDto from(Expense expense) {
        return new ExpenseResponseDto(
                expense.getId(),

                expense.getDate()

        );
    }
}
