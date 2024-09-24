package com.example.travel_diary.global.domain.repository;

import com.example.travel_diary.global.domain.entity.CountryInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryInfoRepository extends JpaRepository<CountryInfo, Long> {
    Optional<CountryInfo> findByName(String name);
}