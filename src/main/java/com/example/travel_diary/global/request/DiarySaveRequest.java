package com.example.travel_diary.global.request;

import com.example.travel_diary.global.domain.entity.Diary;
import com.example.travel_diary.global.domain.entity.Post;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DiarySaveRequest(
        Long postId,
        String title,
        String content,
        LocalDate date
) {

    public Diary toEntity(Post post) {
        return Diary.builder()
                .title(title)
                .content(content)
                .date(date)
                .createdAt(LocalDateTime.now())
                .post(post)
                .build();
    }
}