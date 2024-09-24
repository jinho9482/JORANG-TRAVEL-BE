package com.example.travel_diary.controller;

import com.example.travel_diary.global.domain.entity.CountryInfo;
import com.example.travel_diary.service.AuthService;
import com.example.travel_diary.service.CountryInfoService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "여행지 정보 가져오기")
    public CountryInfo getByCountryName(@PathVariable(name = "countryName") String countryName) {
        return countryInfoService.getByName(countryName);

    }
}