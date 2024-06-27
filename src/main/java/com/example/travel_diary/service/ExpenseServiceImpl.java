package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Expense;
import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.domain.repository.ExpenseRepository;
import com.example.travel_diary.global.request.ExpenseRequestDto;
import com.example.travel_diary.global.response.ExpenseDetailByUserAndCountryResponseDto;
import com.example.travel_diary.global.response.ExpenseResponseDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    @Transactional
    @Override
    public void saveExpense(ExpenseRequestDto expenseRequestDto) {


       expenseRepository.save(expenseRequestDto.toEntity());

    }

    @Override
    public List<Expense> getAllByPostId(Long postId){ return expenseRepository.findAllByPost_Id(postId);}

    @Override
    public ExpenseResponseDto getExpenseById(Long id){
        Expense expense = expenseRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        // 에러처리(orelseget)
        return ExpenseResponseDto.from(expense);
    }
    @Transactional
    @Override
    public Expense updateExpense(Long id, ExpenseRequestDto req){
        Expense expense = expenseRepository.findById(id).orElseThrow(
                EntityNotFoundException::new);
        expense.setDate(req.date());

        //set이란 save 중 하나만
        //save를 하면 중복된 값 생길수도?insert와 update의 구분 기준이 없음 -> 어떤 기준?
        // set을 쓰면 @Transactional //더티체킹-> 원본데이터
        return expense;
    }
    @Transactional
    @Override
    public void deleteExpenseById(Long id){
        Expense expense = expenseRepository.findById(id).orElseThrow(
                EntityNotFoundException::new
        );
        expenseRepository.deleteById(id);
    }

//@Transactional지우다가뭔가 오류-> 실패->롤백하는게 목표
    //    @Override
//    public List<Expense> getAllExpenses(){
//        return expenseRepository.findAll();
//    }
}