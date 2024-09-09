package com.example.travel_diary.global.request;


import org.springframework.web.multipart.MultipartFile;

public record PhotoRequest(
        Long postId,
        Long diaryId,
        MultipartFile[] files
) {
}
