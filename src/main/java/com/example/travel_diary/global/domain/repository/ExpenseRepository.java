package com.example.travel_diary.global.domain.repository;

import com.example.travel_diary.global.domain.entity.Expense;
import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findAllByPost_Id(Long postId);

}
