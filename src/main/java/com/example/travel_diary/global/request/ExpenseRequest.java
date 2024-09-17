package com.example.travel_diary.global.request;

import com.example.travel_diary.global.domain.entity.Expense;
import com.example.travel_diary.global.domain.entity.Post;

import java.time.LocalDate;

public record ExpenseRequest(
    LocalDate date

    ){
    public Expense toEntity(Post post){
//        Post post = Post.builder().id(this.postId).build();



        return Expense.builder()
                .date(date)
                .post(post)
                .build();

    }

}
