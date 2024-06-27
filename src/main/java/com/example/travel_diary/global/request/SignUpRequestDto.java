package com.example.travel_diary.global.request;

import com.example.travel_diary.global.domain.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record SignUpRequestDto(
        String loginId, String name, String nickname, String password, LocalDate dateOfBirth, String email
) {
    public User toEntity(String encodedPassword) {
        LocalDateTime now = LocalDateTime.now();
        return User.builder()
                .id(UUID.randomUUID())
                .loginId(loginId)
                .name(name)
                .nickname(nickname)
                .password(encodedPassword)
                .dateOfBirth(dateOfBirth)
                .email(email)
                .createdAt(now)
                .build();
    }
}

