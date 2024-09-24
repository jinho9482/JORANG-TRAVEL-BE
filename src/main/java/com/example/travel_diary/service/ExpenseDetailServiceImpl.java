package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Expense;
import com.example.travel_diary.global.domain.entity.ExpenseDetail;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.domain.repository.ExpenseDetailRepository;
import com.example.travel_diary.global.domain.repository.ExpenseRepository;
import com.example.travel_diary.global.exception.ExpenseDetailNotFoundException;
import com.example.travel_diary.global.request.ExpenseDetailRequest;
import com.example.travel_diary.global.response.ExpenseDetailByUserAndCountryResponseDto;
import com.example.travel_diary.global.response.ExpenseDetailChartResponseDto;
import com.example.travel_diary.global.response.ExpenseDetailChartTempResponseDto;
import com.example.travel_diary.global.response.ExpenseDetailResponseDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExpenseDetailServiceImpl implements ExpenseDetailService {
    private final ExpenseDetailRepository expenseDetailRepository;

    @Override
    @Transactional
    public void saveExpenseDetails(Long expenseId, List<ExpenseDetailRequest> req) {
        log.info(req.toString());
        List<ExpenseDetail> details = expenseDetailRepository.findAllByExpense_Id(expenseId);
        if (details.isEmpty()) req.forEach(el -> expenseDetailRepository.save(el.toEntity(expenseId)));
        else {
            expenseDetailRepository.deleteAllByExpense_Id(expenseId);
            req.forEach(el -> expenseDetailRepository.save(el.toEntity(expenseId)));
        }
    }

    @Override
    public List<ExpenseDetailChartResponseDto> getExpenseDetailChart(Long postId) {
        List<ExpenseDetail> allByExpensePostId = expenseDetailRepository.findAllByExpense_Post_Id(postId);
        List<String> getCategory = new ArrayList<>(); // 초기화
        List<ExpenseDetailChartTempResponseDto> temp = new ArrayList<>();
        List<ExpenseDetailChartResponseDto> result = new ArrayList<>(); // 초기화
        int totalByCategory = 0;
        int total = 0;
        double percent = 0;

        for(ExpenseDetail expenseDetail : allByExpensePostId) {
            if(!getCategory.contains(expenseDetail.getCategory())){
                getCategory.add(expenseDetail.getCategory());
            }
        }
        for(String category : getCategory) {
            List<ExpenseDetail> allByCategoryAndExpensePostId = expenseDetailRepository.findAllByCategoryAndExpense_Post_Id(category, postId);
            totalByCategory = 0;
            for(ExpenseDetail expenseDetail : allByCategoryAndExpensePostId) {
                totalByCategory += expenseDetail.getCost();
                total += expenseDetail.getCost();
            }
            temp.add(new ExpenseDetailChartTempResponseDto(totalByCategory, category));
        }

        for(ExpenseDetailChartTempResponseDto dto : temp) {
            percent = Double.parseDouble(String.format("%.2f",(double) dto.cost() /total)) * 100;

            result.add(new ExpenseDetailChartResponseDto(dto.cost(), total, dto.category(), percent));
        }
        return result;

    }

    @Transactional
    @Override
    public void deleteAllByExpenseId(Long expenseId) {
        List<ExpenseDetail> expenseDetail = expenseDetailRepository.findAllByExpense_Id(expenseId);
        if (expenseDetail.isEmpty()) throw new ExpenseDetailNotFoundException();
        expenseDetailRepository.deleteAllByExpense_Id(expenseId);
    }
}
