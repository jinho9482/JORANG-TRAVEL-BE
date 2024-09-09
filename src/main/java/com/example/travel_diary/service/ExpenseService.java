package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Expense;
import com.example.travel_diary.global.request.ExpenseRequest;
import com.example.travel_diary.global.response.ExpenseResponseDto;

import java.util.List;

public interface ExpenseService {
    long saveExpense(Long postId, ExpenseRequest expenseRequest);
    Expense updateExpense(Long id, ExpenseRequest expenseRequest);
    List<Expense> getAllByPostId(Long id);
    ExpenseResponseDto getExpenseById(Long id);
    void deleteExpenseById(Long id);
//    List<Expense> getAllExpense();
//
//    List<ExpenseByUserAndCountryResponseDto>getExpenseByUserAndCountry(User user);


}
