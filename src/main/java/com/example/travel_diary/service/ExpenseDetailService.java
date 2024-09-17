package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.request.ExpenseDetailRequest;
import com.example.travel_diary.global.response.ExpenseDetailByUserAndCountryResponseDto;
import com.example.travel_diary.global.response.ExpenseDetailChartResponseDto;
import com.example.travel_diary.global.response.ExpenseDetailResponseDto;

import java.util.List;

public interface ExpenseDetailService {


//    void saveExpenseDetailByExpenseId(Long expenseId, List<ExpenseDetailRequest> requestDto);
    ExpenseDetailResponseDto getExpenseDetailById(Long id);
//    void updateExpenseDetail(Long id, ExpenseDetailRequest requestDto);
    void deleteExpenseDetailById(Long id);
    List<ExpenseDetailByUserAndCountryResponseDto> getExpenseDetailByUserAndCountry(User user);

    List<ExpenseDetailResponseDto> getExpenseDetailsByPostId(Long postId);


    List<ExpenseDetailChartResponseDto> getExpenseDetailChart(Long postId);

    void saveExpenseDetails(Long expenseId, List<ExpenseDetailRequest> req);

}
