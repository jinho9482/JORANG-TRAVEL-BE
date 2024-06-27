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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody ExpenseRequestDto req) {
        expenseService.saveExpense(req);
    }


    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody ExpenseRequestDto req) {
        expenseService.updateExpense(id, req);
    }
    @GetMapping("/{postId}")
    public List<Expense> getAllByPostId(@PathVariable Long postId) {return expenseService.getAllByPostId(postId);


    @PutMapping("/update/{id}")
    public Expense update(@PathVariable(name = "id") Long id, @RequestBody ExpenseRequestDto req) {
        return expenseService.updateExpense(id, req);

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

