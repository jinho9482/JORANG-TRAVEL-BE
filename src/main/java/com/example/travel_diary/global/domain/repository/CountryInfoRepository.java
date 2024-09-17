package com.example.travel_diary.global.domain.repository;

import com.example.travel_diary.global.domain.entity.CountryInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryInfoRepository extends JpaRepository<CountryInfo, Long> {
    CountryInfo findByName(String name);
}