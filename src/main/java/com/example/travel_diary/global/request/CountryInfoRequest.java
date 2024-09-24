package com.example.travel_diary.global.request;

import com.example.travel_diary.global.domain.entity.CountryInfo;

public record CountryInfoRequest(
        String countryName,
        String info
)
{
    public CountryInfo toEntity() {
        CountryInfo countryInfo = CountryInfo.builder()
                .name(countryName)
                .info(info)
                .build();
        return countryInfo;
    }
}
