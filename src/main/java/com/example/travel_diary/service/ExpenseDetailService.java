package com.example.travel_diary.service;

import com.example.travel_diary.global.request.ExpenseDetailRequest;
import com.example.travel_diary.global.response.ExpenseDetailChartResponseDto;

import java.util.List;

public interface ExpenseDetailService {
    void saveExpenseDetails(Long expenseId, List<ExpenseDetailRequest> req);
    List<ExpenseDetailChartResponseDto> getExpenseDetailChart(Long postId);
    void deleteAllByExpenseId(Long expenseId);

}
