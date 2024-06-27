package com.example.travel_diary.global.response;

import com.example.travel_diary.global.domain.entity.Like;
import com.example.travel_diary.global.domain.entity.Post;

public record LikeResponse(
        Long id, Post post
) {
    public static LikeResponse from(Like like) {
        return new LikeResponse(like.getId(), like.getPost());
    }
}
