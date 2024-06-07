package com.example.travel_diary.controller;

import com.example.travel_diary.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("api/v1/photos")
@RequiredArgsConstructor
public class PhotoController {
    private final PhotoService photoService;
}
