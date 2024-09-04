package com.example.travel_diary.global.request;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

public record PhotoRequestDto(
        Long diaryId,
        MultipartFile[] files
) {
}
