package com.example.travel_diary.controller;

import com.example.travel_diary.global.domain.entity.Expense;
import com.example.travel_diary.global.request.ExpenseRequest;
import com.example.travel_diary.global.response.ExpenseResponseDto;
import com.example.travel_diary.service.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/expenses")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;

    @PostMapping("/posts/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "여행 경비 생성 및 업데이트")
    public List<ExpenseResponseDto> saveExpenses(@PathVariable(name = "postId") Long postId, @RequestBody List<ExpenseRequest> req){
        return expenseService.saveExpenses(postId, req);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "여행기 삭제")
    public void deleteExpenseById(@PathVariable(name = "id") Long id) {
        expenseService.deleteExpenseById(id);
    }
}

