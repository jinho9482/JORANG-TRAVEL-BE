package com.example.travel_diary.global.response;

public record GetUserByIdResponseDto(
        String nickname, String password, String email
) {
}
