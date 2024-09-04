package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Expense;
import com.example.travel_diary.global.domain.repository.ExpenseRepository;
import com.example.travel_diary.global.domain.repository.PostRepository;
import com.example.travel_diary.global.request.ExpenseRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ExpenseServiceTest {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostService postService;
    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private ExpenseRepository expenseRepository;

//    Expense expense = null;
    private Expense expense;
    @BeforeEach
    void beforeEach(){
        //테스트 시작 할 때 마다 expense를 새로 생성
        expense = new Expense();
        LocalDate date = LocalDate.parse("2022-02-02");
        expense.setDate(date);
        expenseRepository.deleteAll();
    }
//    @AfterEach
//    void afterEach(){
////        //테스트 끝날 때 마다 글 전체 삭제
////        if (expense != null && expense.getId() != null) {
////            expenseRepository.delete(expense);
////        }
//        expenseService.deleteExpenseById(Long.valueOf(expense.getId()));
//    }

//    @Test
//    @DisplayName("1.경비 저장")
//    void saveExpense() {
//        //given
//        ExpenseRequestDto dto = new ExpenseRequestDto(LocalDate.parse("2022-02-02"),Long.valueOf("1"));
//        //when
//        expenseService.saveExpense(dto);
//        //then
////        assertNotNull(expense);
////        assertNotNull(expense.getId());
//        assertEquals(LocalDate.parse("2022-02-02"),expense.getDate());
//    }

//    @Test
//    @DisplayName("2.경비 업데이트")
//    void updateExpense() {
//        //given
//      expense.setDate(LocalDate.parse("2022-02-02"));// 원래 날짜
//      expenseService.saveExpense(new ExpenseRequestDto(LocalDate.parse("2022-02-02"), Long.valueOf("1")));
//
//      expenseService.updateExpense(Long.valueOf("1"), new ExpenseRequestDto(LocalDate.parse("2022-02-03"), Long.valueOf("1")));
//      assertThat()
//        //then
////        assertEquals(expense1.getDate(), date);
//    }

    @Test
    void getAllByPostId() {
        //given
        //when
        //then

    }

    @Test
    void getExpenseById() {
        //given
        LocalDate date = LocalDate.now();
        Expense expense = Expense.builder()
                .date(date)
                .build();
        expenseRepository.save(expense);
        //when
        Expense expense1 = expenseRepository.findById(expense.getId()).get();
        expense1.setDate(date);
        //then
        assertEquals(expense1.getDate(), date);

    }

    @Test
    @DisplayName("경비 삭제")
    void deleteExpenseById() {
        //given

        LocalDate date = LocalDate.now();
        Expense expense = Expense.builder()
                .date(date)
                .build();
        expenseRepository.save(expense);

        //when
        Expense expense1 = expenseRepository.findById(expense.getId()).get();
        expense1.setDate(date);

        //then
        expenseRepository.delete(expense1);
        assertEquals(0, expenseRepository.count());
        expenseRepository.findAll().forEach(System.out::println);

    }
}