package com.example.travel_diary.global.request;

import com.example.travel_diary.global.domain.type.Scope;

public record PostRequestDto(
        String title,
        String country,
        Scope scope
) {

}
