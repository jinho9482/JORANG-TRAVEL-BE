package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Expense;
import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.repository.ExpenseRepository;
import com.example.travel_diary.global.domain.repository.PostRepository;
import com.example.travel_diary.global.exception.ExpenseNotFoundException;
import com.example.travel_diary.global.request.ExpenseRequest;
import com.example.travel_diary.global.response.ExpenseResponseDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    @Transactional
    @Override
    public void deleteExpenseById(Long id){
        Expense expense = expenseRepository.findById(id).orElseThrow(
                ExpenseNotFoundException::new
        );
        expenseRepository.deleteById(id);
    }

    // ex. [1,2,3] 이 db에 있고 [1,2,6]이 들어왔을 때, 1,2,6의 id를 return 하고 3은 db에서 삭제해야 한다.
    @Override
    public List<ExpenseResponseDto> saveExpenses(Long postId, List<ExpenseRequest> req) {
        // post Id를 기준으로 expense 찾기
        List<Expense> expenses = expenseRepository.findAllByPost_Id(postId);
        List<LocalDate> dates = expenses.stream().map(Expense::getDate).toList();
        // expense의 날짜를 가져오기
        boolean[] matched = new boolean[expenses.size()]; // false 인 것은 DB에서 삭제
        List<ExpenseResponseDto> res = new ArrayList<>();
        req.forEach((el) -> {
            int index = dates.indexOf(el.date());
            // DB에 없으면 새로 생성
            if (index == -1) {
                Post post = Post.builder().id(postId).build();
                Expense expense = el.toEntity(post);
                Expense savedExpense = expenseRepository.save(expense);
                ExpenseResponseDto dto = new ExpenseResponseDto(savedExpense.getId(), el.date());
                res.add(dto);
            // DB에 있으면 id 가져오고, matched에 지울 필요 없다고 true로 표시하기
            } else {
                ExpenseResponseDto dto = new ExpenseResponseDto(expenses.get(index).getId(), el.date());
                res.add(dto);
                matched[index] = true;
            }
        });
        for (int i = 0; i < matched.length; i++) {
            if (!matched[i]) expenseRepository.delete(expenses.get(i));
        }
        return res;
    }
}
