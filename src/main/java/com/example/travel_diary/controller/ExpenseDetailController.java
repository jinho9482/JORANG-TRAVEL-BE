package com.example.travel_diary.controller;

import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.request.ExpenseDetailRequest;
import com.example.travel_diary.global.response.ExpenseDetailByUserAndCountryResponseDto;
import com.example.travel_diary.global.response.ExpenseDetailChartResponseDto;
import com.example.travel_diary.global.response.ExpenseDetailResponseDto;
import com.example.travel_diary.service.ExpenseDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/expense-details")
@RequiredArgsConstructor
@Tag(name = "여행 경비 상세")
public class ExpenseDetailController {
    private final ExpenseDetailService expenseDetailService;

    @PostMapping("expenses/{expenseId}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "여행 경비 상세 생성 및 업데이트")
    public void saveExpenseDetails(@PathVariable(name = "expenseId") Long expenseId, @RequestBody List<ExpenseDetailRequest> req) {
        expenseDetailService.saveExpenseDetails(expenseId, req);
    }

    @GetMapping("/chart/postId/{postId}")
    @Operation(summary = "여행 경비 도표화에 필요한 데이터 가져오기")
    public List<ExpenseDetailChartResponseDto> getExpenseDetailChart(@PathVariable("postId") Long postId) {
        return expenseDetailService.getExpenseDetailChart(postId);
    }

    @DeleteMapping("/expense/{expenseId}")
    @Operation(summary = "여행 경비 상세 삭제")
    public void deleteAllByExpenseId(@PathVariable(name = "expenseId") Long expenseId) {
        expenseDetailService.deleteAllByExpenseId(expenseId);
    }
}


