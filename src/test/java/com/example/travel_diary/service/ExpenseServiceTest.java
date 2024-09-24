package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.*;
import com.example.travel_diary.global.domain.repository.*;
import com.example.travel_diary.global.domain.type.Scope;
import com.example.travel_diary.global.exception.ExpenseNotFoundException;
import com.example.travel_diary.global.request.ExpenseRequest;
import com.example.travel_diary.global.request.PostRequest;
import com.example.travel_diary.global.request.PostTempRequest;
import com.example.travel_diary.global.response.ExpenseResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
class ExpenseServiceTest {
    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UUID USER_ID = UUID.randomUUID();
    private List<Long> expenseIds = new ArrayList<>();
    private Long postId;

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

        for (int i = 0; i < 5; i++) {
            Expense publicExpense = Expense.builder()
                    .date(LocalDate.now())
                    .post(post)
                    .build();
            Expense savaedExpense = expenseRepository.save(publicExpense);
            expenseIds.add(savaedExpense.getId());
        }
    }

    @Test
    @Transactional
    void saveExpenses_success() {
        List<ExpenseRequest> expenseRequests = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ExpenseRequest expenseRequest = new ExpenseRequest(
                    LocalDate.now().plusDays(i * 3)
            );
            expenseRequests.add(expenseRequest);
        }

        List<ExpenseResponseDto> res = expenseService.saveExpenses(postId, expenseRequests);
        LocalDate targetDate = res.get(1).date();

        assertNotNull(res);
        assertEquals(3, res.size());
        assertEquals(LocalDate.now().plusDays(3), targetDate);
    }

    @Nested
    @Transactional
    class deleteExpenseById {
        @Test
        void success() {
            Long targetId = expenseIds.get(0);
            expenseService.deleteExpenseById(targetId);

            assertThrows(ExpenseNotFoundException.class,
                    () -> expenseService.deleteExpenseById(targetId));
        }

        @Test
        void fail_expense_not_found() {
            assertThrows(ExpenseNotFoundException.class,
                    () -> expenseService.deleteExpenseById(100L));
        }
    }


}