package com.example.travel_diary.controller;

import com.example.travel_diary.global.domain.entity.CountryInfo;
import com.example.travel_diary.service.AuthService;
import com.example.travel_diary.service.CountryInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/country")
@RequiredArgsConstructor
public class CountryInfoController {
    private final CountryInfoService countryInfoService;
    @GetMapping("/info/{countryName}")
    public CountryInfo getByCountryName(@PathVariable(name = "countryName") String countryName) {
        return countryInfoService.getByName(countryName);

    }
}