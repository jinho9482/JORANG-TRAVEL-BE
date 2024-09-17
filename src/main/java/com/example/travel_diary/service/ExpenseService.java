package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Expense;
import com.example.travel_diary.global.request.ExpenseRequest;
import com.example.travel_diary.global.response.ExpenseResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ExpenseService {
//    Long createExpense(Long postId, ExpenseRequest expenseRequest);
//    Expense updateExpense(Long id, ExpenseRequest expenseRequest);
    List<Expense> getAllByPostId(Long id);
    ExpenseResponseDto getExpenseById(Long id);
    void deleteExpenseById(Long id);
    List<ExpenseResponseDto> saveExpenses(Long postId, List<ExpenseRequest> req);
//    List<Expense> getAllExpense();
//
//    List<ExpenseByUserAndCountryResponseDto>getExpenseByUserAndCountry(User user);


}
