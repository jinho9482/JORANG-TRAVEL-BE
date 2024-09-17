package com.example.travel_diary.global.response;

import com.example.travel_diary.global.domain.entity.ExpenseDetail;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ExpenseDetailResponseDto(
        Long id,
        int cost,
        String place,

        String category,
        LocalDateTime createdAt

) {
    public static ExpenseDetailResponseDto from(ExpenseDetail expenseDetail) {
        return new ExpenseDetailResponseDto(
                expenseDetail.getId(),
                expenseDetail.getCost(),
                expenseDetail.getPlace(),
                expenseDetail.getCategory(),
                expenseDetail.getCreatedAt()
        );
    }
}
