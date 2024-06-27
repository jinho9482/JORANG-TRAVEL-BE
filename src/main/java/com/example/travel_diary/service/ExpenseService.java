package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Expense;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.request.ExpenseRequestDto;
import com.example.travel_diary.global.response.ExpenseResponseDto;

import java.util.List;

public interface ExpenseService {
    void saveExpense(ExpenseRequestDto expenseRequestDto);
    Expense updateExpense(Long id,ExpenseRequestDto expenseRequestDto);
    List<Expense> getAllByPostId(Long id);
    ExpenseResponseDto getExpenseById(Long id);
    void deleteExpenseById(Long id);
//    List<Expense> getAllExpense();
//
//    List<ExpenseByUserAndCountryResponseDto>getExpenseByUserAndCountry(User user);


}

