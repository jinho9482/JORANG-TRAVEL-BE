package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.CountryInfo;
import com.example.travel_diary.global.domain.repository.CountryInfoRepository;
import com.example.travel_diary.global.exception.CountryInfoNotFoundException;
import com.example.travel_diary.global.request.CountryInfoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryInfoServiceImpl implements CountryInfoService{
    private final CountryInfoRepository countryInfoRepository;

    @Override
    public void createCountryInfo(CountryInfoRequest req) {
        CountryInfo countryInfo = req.toEntity();
        countryInfoRepository.save(countryInfo);
    }
    @Override
    public CountryInfo getByName(String countryName) {
        return countryInfoRepository.findByName(countryName).orElseThrow(CountryInfoNotFoundException::new);
    }
}