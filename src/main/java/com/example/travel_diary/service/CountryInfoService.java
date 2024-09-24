package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.CountryInfo;
import com.example.travel_diary.global.request.CountryInfoRequest;

public interface CountryInfoService{
    void createCountryInfo(CountryInfoRequest req);
    CountryInfo getByName(String countryName);
}