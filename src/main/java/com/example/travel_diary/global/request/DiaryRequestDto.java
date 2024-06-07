package com.example.travel_diary.global.request;

import com.example.travel_diary.global.domain.entity.Diary;
import com.example.travel_diary.global.domain.entity.Photo;
import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.type.Scope;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public record DiaryRequestDto(
        String title,
        Date date,
        Scope scope,
        String country,
        List<Photo> photos
) {
    public Diary toEntity() {
        return Diary.builder()
                .title(title)
                .date(date)
                .scope(scope)
                .country(country)
                .createdAt(LocalDateTime.now())
                .photos(photos)
                .build();
    }
}
