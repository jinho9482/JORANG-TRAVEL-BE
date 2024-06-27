package com.example.travel_diary.global.request;

import com.example.travel_diary.global.domain.entity.Diary;
import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.type.Scope;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DiaryRequestDto(
        String title,
        String content,
        LocalDate date,
        Scope scope,
        String country
        ) {
    // update만 쓰기 때문에 필요 없다.
//        public Diary toEntity(Post post, DiaryRequestDto req) {
//            return Diary.builder()
//                    .title(title)
//                    .content(content)
//                    .date(date)
//                    .scope(scope)
//                    .createdAt(LocalDateTime.now())
//                    .country(country)
//                    .post(post)
//                    .build();
//        }
}
