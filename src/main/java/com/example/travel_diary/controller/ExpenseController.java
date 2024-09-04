package com.example.travel_diary.controller;

import com.example.travel_diary.global.domain.entity.Expense;
import com.example.travel_diary.global.request.ExpenseRequestDto;
import com.example.travel_diary.global.response.ExpenseResponseDto;
import com.example.travel_diary.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/expenses")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;

    @PostMapping("/posts/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    public long save(@PathVariable(name = "postId") Long postId,@RequestBody ExpenseRequestDto expenseRequestDto) {
        return expenseService.saveExpense(postId,expenseRequestDto);
    }



    @PutMapping("/update/{id}")
    public Expense update(@PathVariable(name = "id") Long id, @RequestBody ExpenseRequestDto req) {
        return expenseService.updateExpense(id, req);


    
    }
    @GetMapping("/{postId}")
    public List<Expense> getAllByPostId(@PathVariable Long postId) {
        return expenseService.getAllByPostId(postId);
    }



    @GetMapping("/{id}")
    public ExpenseResponseDto getExpenseById(@PathVariable(name = "id") Long id) {
        return expenseService.getExpenseById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteExpenseById(@PathVariable(name = "id") Long id) {
        expenseService.deleteExpenseById(id);
    }
}

