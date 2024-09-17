package com.example.travel_diary.global.request;

import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.domain.type.Scope;

public record PostTempRequest(
        String title
) {
    public Post toEntity(User user) {
        Post post = Post.builder()
                .title(title)
                .user(user)
                .build();
        return post;
    }
}
