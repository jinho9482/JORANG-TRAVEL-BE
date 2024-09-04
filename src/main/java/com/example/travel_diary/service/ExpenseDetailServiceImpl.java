package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Expense;
import com.example.travel_diary.global.domain.entity.ExpenseDetail;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.domain.repository.ExpenseDetailRepository;
import com.example.travel_diary.global.domain.repository.ExpenseRepository;
import com.example.travel_diary.global.request.ExpenseDetailRequestDto;
import com.example.travel_diary.global.response.ExpenseDetailByUserAndCountryResponseDto;
import com.example.travel_diary.global.response.ExpenseDetailChartResponseDto;
import com.example.travel_diary.global.response.ExpenseDetailChartTempResponseDto;
import com.example.travel_diary.global.response.ExpenseDetailResponseDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseDetailServiceImpl implements ExpenseDetailService {
    private final ExpenseDetailRepository expenseDetailRepository;
    private final ExpenseRepository expenseRepository;

    @Transactional
    @Override
    public void saveExpenseDetailbyExpenseId(Long expenseId, List<ExpenseDetailRequestDto> requestDto) {
        Expense expense = expenseRepository.findById(expenseId).orElseThrow(EntityNotFoundException::new);
        List<ExpenseDetail> expenseDetails = requestDto.stream()
                .map(dto -> dto.toEntity(expense))  // Expense 객체를 포함하여 Entity 생성
                .collect(Collectors.toList());
        expenseDetailRepository.saveAll(expenseDetails);
//        requestDto.forEach(e -> expenseDetailRepository.save(e.toEntity()));

//        expenseDetailRepository.save(requestDto.toEntity());

   // public void saveExpenseDetail(ExpenseDetailRequestDto requestDto) {
     //   expenseDetailRepository.save(requestDto.toEntity());

    }

    @Override
    public ExpenseDetailResponseDto getExpenseDetailById(Long id) {
        ExpenseDetail expenseDetail = expenseDetailRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return ExpenseDetailResponseDto.from(expenseDetail);
    }

    @Transactional
    @Override
    public void updateExpenseDetail(Long id, ExpenseDetailRequestDto requestDto) {
        ExpenseDetail expenseDetail = expenseDetailRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        expenseDetail.setCost(requestDto.cost());
        expenseDetail.setPlace(requestDto.place());
        expenseDetail.setCategory(requestDto.category());
    }

    @Transactional
    @Override
    public void deleteExpenseDetailById(Long id) {
        ExpenseDetail expenseDetail = expenseDetailRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        expenseDetailRepository.deleteById(id);
    }

    @Override
    public List<ExpenseDetailByUserAndCountryResponseDto> getExpenseDetailByUserAndCountry(User user) {
        List<ExpenseDetail> allAndPostUser = expenseDetailRepository.findAllByExpense_Post_User(user);
        List<String> countryByUser = new ArrayList<>(); // 초기화
        List<ExpenseDetailByUserAndCountryResponseDto> result = new ArrayList<>(); // 초기화
        int total = 0;


        return result;
    }

    @Override
    public List<ExpenseDetailResponseDto> getExpenseDetailsByPostId(Long postId) {
        List<ExpenseDetail> expenseDetails = expenseDetailRepository.findAllByExpense_Post_Id(postId);
        return expenseDetails.stream()
                .map(ExpenseDetailResponseDto::from)
                .collect(Collectors.toList());

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
}
