package com.example.travel_diary.global.response;

public record ExpenseDetailChartResponseDto(
        int cost, int total, String category, double percent
) {
}