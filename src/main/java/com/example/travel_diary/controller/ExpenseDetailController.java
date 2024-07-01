package com.example.travel_diary.controller;

import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.domain.entity.ExpenseDetail;
import com.example.travel_diary.global.request.ExpenseDetailRequestDto;
import com.example.travel_diary.global.response.ExpenseDetailByUserAndCountryResponseDto;
import com.example.travel_diary.global.response.ExpenseDetailChartResponseDto;
import com.example.travel_diary.global.response.ExpenseDetailResponseDto;
import com.example.travel_diary.service.ExpenseDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/expense-details")
@RequiredArgsConstructor
public class ExpenseDetailController {
    private final ExpenseDetailService expenseDetailService;

    @PostMapping("expenses/{expenseId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveExpenseDetailbyExpenseId( @PathVariable("expenseId") Long expenseId ,@RequestBody List<ExpenseDetailRequestDto> requestDto) {

        expenseDetailService.saveExpenseDetailbyExpenseId(expenseId ,requestDto);

    }


//("/expenses/{expenseId}")
    @PutMapping("/{id}")
    public void updateExpenseDetail(@PathVariable(name = "id") Long id, @RequestBody ExpenseDetailRequestDto requestDto) {
        expenseDetailService.updateExpenseDetail(id, requestDto);
    }
//@GetMapping("/{expenseId")
//public List<ExpenseDetail> getAllbyExpenseId(@PathVariable Long expenseId) {}

    @GetMapping("/{id}")
    public ExpenseDetailResponseDto getExpenseDetailById(@PathVariable (name = "id")Long id) {
        return expenseDetailService.getExpenseDetailById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteExpenseDetailById(@PathVariable(name = "id") Long id) {
        expenseDetailService.deleteExpenseDetailById(id);
    }

//    @GetMapping("/mypage")
//    public List<ExpenseDetailByUserAndCountryResponseDto> getExpenseDetailByUserAndCountry(@AuthenticationPrincipal User user) {
//        return expenseDetailService.getExpenseDetailByUserAndCountry(user);
//    }


    @GetMapping("/by-post/{postId}")
    public List<ExpenseDetailResponseDto> getExpenseDetailsByPostId(@PathVariable (name="postId") Long postId) {
        return expenseDetailService.getExpenseDetailsByPostId(postId);
    }


//@GetMapping("/by-post/{postId}")
//public List<ExpenseDetailResponseDto> getExpenseDetailsByPostId(@PathVariable(name = "postId") Long postId) {
//    // postId에 해당하는 모든 ExpenseDetails를 가져오는 구현
//    return expenseDetailServive.getExpenseDetailsByPostId(postId);
//}
//@GetMapping("/by-post/{postId}")
//public List<ExpenseDetailResponseDto> getExpenseDetailsByPostId(@PathVariable Long postId) {
//    return expenseDetailService.getExpenseDetailsByPostId(postId);
    @GetMapping("/chart/postId/{postId}")
    public List<ExpenseDetailChartResponseDto> getExpenseDetailChart(@PathVariable("postId") Long postId) {
        return expenseDetailService.getExpenseDetailChart(postId);
    }
}


