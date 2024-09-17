package com.example.travel_diary.controller;

import com.example.travel_diary.global.domain.entity.Expense;
import com.example.travel_diary.global.request.ExpenseRequest;
import com.example.travel_diary.global.response.ExpenseResponseDto;
import com.example.travel_diary.service.ExpenseService;
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

//    @PostMapping("/posts/{postId}")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Long create(@PathVariable(name = "postId") Long postId,@RequestBody ExpenseRequest expenseRequest) {
//        return expenseService.createExpense(postId, expenseRequest);
//    }
//
//
//
//    @PutMapping("/update/{id}")
//    public Expense update(@PathVariable(name = "id") Long id, @RequestBody ExpenseRequest req) {
//        return expenseService.updateExpense(id, req);
//    }

    @PostMapping("/posts/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    public List<ExpenseResponseDto> saveExpenses(@PathVariable(name = "postId") Long postId, @RequestBody List<ExpenseRequest> req){
        return expenseService.saveExpenses(postId, req);
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

