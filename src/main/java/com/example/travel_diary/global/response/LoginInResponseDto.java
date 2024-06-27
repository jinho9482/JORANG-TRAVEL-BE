package com.example.travel_diary.global.response;

import java.util.UUID;

public record LoginInResponseDto(
        String token, UUID loginId, String nickname
) {
}
