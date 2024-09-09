package com.example.travel_diary.global.request;

import java.time.LocalDate;

public record DiaryUpdateRequest(
        Long id,
        String title,
        String content,
        LocalDate date
        ) {

}
