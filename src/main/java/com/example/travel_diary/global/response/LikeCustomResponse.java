package com.example.travel_diary.global.response;

import com.example.travel_diary.global.domain.entity.Post;

import java.util.List;

public record LikeCustomResponse(
        int totalPage,
        List<Post> content
) {
}
