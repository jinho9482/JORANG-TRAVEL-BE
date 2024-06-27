package com.example.travel_diary.global.response;

import com.example.travel_diary.global.domain.entity.Diary;
import com.example.travel_diary.global.domain.entity.Post;

import java.time.LocalDate;

public record DiaryResponseDto(
        Long id, LocalDate date
) {
    public static DiaryResponseDto from(Diary diary) {
        return new DiaryResponseDto(diary.getId(), diary.getDate());
    }
}
