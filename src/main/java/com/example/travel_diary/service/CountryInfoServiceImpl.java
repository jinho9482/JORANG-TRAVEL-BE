package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.CountryInfo;
import com.example.travel_diary.global.domain.repository.CountryInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryInfoServiceImpl implements CountryInfoService{
    private final CountryInfoRepository countryInfoRepository;
    @Override
    public CountryInfo getByName(String countryName) {
        return countryInfoRepository.findByName(countryName);
    }
}