package com.example.travel_diary.global.domain.repository;

import com.example.travel_diary.global.domain.entity.ExpenseDetail;
import com.example.travel_diary.global.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseDetailRepository extends JpaRepository<ExpenseDetail, Long> {
    List<ExpenseDetail> findAllByCountryAndExpense_Post_User(String country, User user);
    List<ExpenseDetail> findAllByExpense_Post_User(User user);

    List<ExpenseDetail> findAllByExpense_Post_Id(Long postId);
    List<ExpenseDetail> findAllByCategoryAndExpense_Post_Id(String category, Long postId);
}

