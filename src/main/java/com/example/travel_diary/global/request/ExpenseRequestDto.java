package com.example.travel_diary.global.request;

import com.example.travel_diary.global.domain.entity.Expense;
import com.example.travel_diary.global.domain.entity.Post;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record ExpenseRequestDto (
    LocalDate date,
    Long postId


    ){
    public Expense toEntity(){
        Post post = Post.builder().id(this.postId).build();
        return Expense.builder()
                .date(this.date)
                .post(post)
                .build();

    }

}
