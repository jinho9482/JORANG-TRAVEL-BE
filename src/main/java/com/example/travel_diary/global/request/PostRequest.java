package com.example.travel_diary.global.request;

import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.domain.type.Scope;

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
                .user(user)
                .isPublished(true)
                .build();
        return post;
    }
}
