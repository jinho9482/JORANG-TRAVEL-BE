package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Expense;
import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.repository.ExpenseRepository;
import com.example.travel_diary.global.domain.repository.PostRepository;
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
    private final PostRepository postRepository;
//    @Transactional
//    @Override

//    public long createExpense(Long postId, ExpenseRequest expenseRequest) {
//
////expenseRequestDto.forEach(e -> expenseRepository.save(e.toEntity()));
////        Post post = Post.builder().id(postId).build();
////        Expense expense = expenseRequestDto.toEntity(post);
//        Post post = postRepository.findById(postId).orElseThrow(EntityNotFoundException::new); // Post 객체를 데이터베이스에서 조회
//        Expense expense = expenseRequest.toEntity(post);
////        Expense savedExpense = expenseRepository.save(expense);
//       expenseRepository.save(expense);
//
//       return expense.getId();
//    }

    @Override
    public List<Expense> getAllByPostId(Long postId){ return expenseRepository.findAllByPost_Id(postId);}

    @Override
    public ExpenseResponseDto getExpenseById(Long id){
        Expense expense = expenseRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        // 에러처리(orelseget)
        return ExpenseResponseDto.from(expense);
    }
//    @Transactional
//    @Override
//    public Expense updateExpense(Long id, ExpenseRequest req){
//        Expense expense = expenseRepository.findById(id).orElseThrow(
//                EntityNotFoundException::new);
//        expense.setDate(req.date());
//
//        //set이란 save 중 하나만
//        //save를 하면 중복된 값 생길수도?insert와 update의 구분 기준이 없음 -> 어떤 기준?
//        // set을 쓰면 @Transactional //더티체킹-> 원본데이터
//        return expense;
//    }
    @Transactional
    @Override
    public void deleteExpenseById(Long id){
        Expense expense = expenseRepository.findById(id).orElseThrow(
                EntityNotFoundException::new
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
            if (!matched[i]) {
                expenseRepository.delete(expenses.get(i));
            }
        }
        return res;
    }

    //@Transactional지우다가뭔가 오류-> 실패->롤백하는게 목표
    //    @Override
//    public List<Expense> getAllExpenses(){
//        return expenseRepository.findAll();
//    }
}
