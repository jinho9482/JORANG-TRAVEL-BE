package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.CountryInfo;
import com.example.travel_diary.global.domain.repository.CountryInfoRepository;
import com.example.travel_diary.global.exception.CountryInfoNotFoundException;
import com.example.travel_diary.global.request.CountryInfoRequest;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CountryInfoServiceTest {
    @Autowired
    private CountryInfoService countryInfoService;
    @Autowired
    private CountryInfoRepository countryInfoRepository;
    @BeforeEach
    void setup() {
        CountryInfo info = new CountryInfo(null, "토론토", "나의 고향");
        countryInfoRepository.save(info);
    }

    @Test
    @Transactional
    void createCountryInfo_success() {
        CountryInfoRequest req = new CountryInfoRequest("크로아티아", "두브로브니크는 사랑입니다.");
        countryInfoService.createCountryInfo(req);
        List<CountryInfo> infos = countryInfoRepository.findAll();
        CountryInfo savedInfo = infos.get(infos.size()-1);

        assertNotNull(infos);
        assertEquals("두브로브니크는 사랑입니다.", savedInfo.getInfo());

    }

    @Nested
    @Transactional
    class getByName {
        @Test
        void success() {
            CountryInfo info = countryInfoService.getByName("토론토");

            assertEquals("토론토", info.getName());
            assertEquals("나의 고향", info.getInfo());
        }
        @Test
        void fail_country_info_not_found() {
            assertThrows(CountryInfoNotFoundException.class,
                    () -> countryInfoService.getByName("한국"));
        }
    }
}