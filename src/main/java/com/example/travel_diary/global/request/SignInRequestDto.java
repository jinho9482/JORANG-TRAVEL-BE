package com.example.travel_diary.global.request;

import com.example.travel_diary.global.domain.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record SignInRequestDto(
        String loginId, String password
) {

}
