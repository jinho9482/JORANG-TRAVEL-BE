package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Expense;
import com.example.travel_diary.global.domain.entity.ExpenseDetail;
import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.domain.repository.ExpenseDetailRepository;
import com.example.travel_diary.global.domain.repository.ExpenseRepository;
import com.example.travel_diary.global.domain.repository.PostRepository;
import com.example.travel_diary.global.domain.repository.UserRepository;
import com.example.travel_diary.global.domain.type.Scope;
import com.example.travel_diary.global.exception.ExpenseDetailNotFoundException;
import com.example.travel_diary.global.request.ExpenseDetailRequest;
import com.example.travel_diary.global.response.ExpenseDetailChartResponseDto;
import com.example.travel_diary.global.response.ExpenseResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExpenseDetailServiceTest {
    @Autowired
    private ExpenseDetailService expenseDetailService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private ExpenseDetailRepository expenseDetailRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UUID USER_ID = UUID.randomUUID();
    private Long postId;
    private Long expenseId;

    @BeforeEach
    void setUp() {
        User user = User.builder()
                .id(USER_ID)
                .loginId("wlshzz123")
                .name("jinho")
                .nickname("Hola")
                .password(passwordEncoder.encode("1234"))
                .dateOfBirth(LocalDate.now())
                .email("jinho@mail.com")
                .createdAt(LocalDateTime.now())
                .posts(new ArrayList<>())
                .build();
        userRepository.save(user);

        Post post = Post.builder()
                .createdAt(LocalDateTime.now())
                .love(100)
                .title("Public")
                .likes(new ArrayList<>())
                .diaries(new ArrayList<>())
                .country("Korea")
                .scope(Scope.PUBLIC)
                .user(user)
                .expenses(new ArrayList<>())
                .isPublished(true)
                .build();

        Post savedPost = postRepository.save(post);

        postId = savedPost.getId();

        Expense publicExpense = Expense.builder()
                .date(LocalDate.now())
                .post(post)
                .build();
        Expense savaedExpense = expenseRepository.save(publicExpense);
        expenseId = savaedExpense.getId();

        for (int i = 0; i < 5; i++) {
            ExpenseDetail publicExpenseDetail = ExpenseDetail.builder()
                    .category("교통비" + i)
                    .place("버스" + i)
                    .cost(10000 + i * 10000)
                    .expense(savaedExpense)
                    .build();
            expenseDetailRepository.save(publicExpenseDetail);
        }

    }

    @Test
    @Transactional
    void saveExpenseDetails_success() {
        List<ExpenseDetailRequest> expenseDetailRequests = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ExpenseDetailRequest expenseDetailRequest = new ExpenseDetailRequest(
                    20000 + i * 10000, "스타벅스" + i, "식비"
            );
            expenseDetailRequests.add(expenseDetailRequest);
        }
        expenseDetailService.saveExpenseDetails(expenseId, expenseDetailRequests);
        List<ExpenseDetail> details =  expenseDetailRepository.findAllByExpense_Id(expenseId);

        ExpenseDetail lastDetail = details.get(details.size()-1);

        assertNotNull(details);
        assertEquals(3, details.size()); // 기존에 있던 것을 다 삭제하고 새로 채움
        assertEquals(40000, lastDetail.getCost());
    }

    @Test
    @Transactional
    void getExpenseDetailChart_success() {
        List<ExpenseDetailChartResponseDto> res = expenseDetailService.getExpenseDetailChart(postId);
        ExpenseDetailChartResponseDto targetDetail = res.get(2);

        assertNotNull(res);
        assertEquals(30000, targetDetail.cost());
        assertEquals("교통비2", targetDetail.category());
    }

    @Nested
    @Transactional
    class deleteAllByExpenseId {
        @Test
        void success() {
            expenseDetailService.deleteAllByExpenseId(expenseId);

            assertThrows(ExpenseDetailNotFoundException.class,
                    () -> expenseDetailService.deleteAllByExpenseId(expenseId));
        }
        @Test
        void fail_expense_detail_not_found() {
            assertThrows(ExpenseDetailNotFoundException.class,
                    () -> expenseDetailService.deleteAllByExpenseId(100L));
        }
    }
}