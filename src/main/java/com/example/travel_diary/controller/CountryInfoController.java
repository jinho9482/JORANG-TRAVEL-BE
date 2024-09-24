package com.example.travel_diary.controller;

import com.example.travel_diary.global.domain.entity.CountryInfo;
import com.example.travel_diary.global.request.CountryInfoRequest;
import com.example.travel_diary.service.AuthService;
import com.example.travel_diary.service.CountryInfoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/country")
@RequiredArgsConstructor
public class CountryInfoController {
    private final CountryInfoService countryInfoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "여행지 정보 생성")
    public void createCountryInfo(@RequestBody CountryInfoRequest req) {
        countryInfoService.createCountryInfo(req);
    }

    @GetMapping("/info/{countryName}")
    @Operation(summary = "여행지 정보 가져오기")
    public CountryInfo getByCountryName(@PathVariable(name = "countryName") String countryName) {
        return countryInfoService.getByName(countryName);

    }
}