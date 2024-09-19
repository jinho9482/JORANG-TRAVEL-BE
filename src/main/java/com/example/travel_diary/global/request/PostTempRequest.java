package com.example.travel_diary.global.request;

import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.domain.type.Scope;

import java.time.LocalDateTime;

public record PostTempRequest(
        String title
) {
    public Post toEntity(User user) {
        Post post = Post.builder()
                .title(title)
                .createdAt(LocalDateTime.now())
                .user(user)
                .build();
        return post;
    }
}
