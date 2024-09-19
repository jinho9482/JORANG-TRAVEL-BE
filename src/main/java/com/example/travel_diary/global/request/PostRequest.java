package com.example.travel_diary.global.request;

import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.domain.type.Scope;

import java.time.LocalDateTime;

public record PostRequest(
        String title,
        String country,
        Scope scope
) {
    public Post toEntity(User user) {
        Post post = Post.builder()
                .title(title)
                .country(country)
                .scope(scope)
                .createdAt(LocalDateTime.now())
                .user(user)
                .isPublished(true)
                .build();
        return post;
    }
}
