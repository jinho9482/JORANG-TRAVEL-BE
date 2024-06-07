package com.example.travel_diary.controller;

import com.example.travel_diary.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/Expenses")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;
}
