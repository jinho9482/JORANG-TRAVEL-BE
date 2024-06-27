package com.example.travel_diary.global.response;

import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.entity.User;

import java.time.LocalDateTime;

public record PostResponse(
        Long postId, String title, LocalDateTime createdAt
) {
    public static PostResponse from(Post post) {

        return new PostResponse(post.getId(), post.getTitle(), post.getCreatedAt());
    }
}
