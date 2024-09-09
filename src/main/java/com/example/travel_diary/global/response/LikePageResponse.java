package com.example.travel_diary.global.response;

import com.example.travel_diary.global.domain.entity.Post;

import java.util.List;

public record LikePageResponse(
        int totalPage,
        List<Post> content
) {
}
