package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.CountryInfo;

public interface CountryInfoService{
    CountryInfo getByName(String countryName);
}