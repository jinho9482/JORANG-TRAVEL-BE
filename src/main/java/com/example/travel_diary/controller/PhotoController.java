package com.example.travel_diary.controller;

import com.example.travel_diary.global.domain.entity.Photo;

import com.example.travel_diary.global.request.PhotoRequest;
import com.example.travel_diary.service.PhotoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/photos")
@RequiredArgsConstructor
@Slf4j
public class PhotoController {
    private final PhotoService photoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "사진 저장")
    public void savePhotos(PhotoRequest req) throws IOException {
        log.info(req.toString());
        photoService.savePhotos(req);
    }

    @DeleteMapping("/{diaryId}")
    @Operation(summary = "특정 여행기의 사진 지우기")
    public void deletePhotosByDiaryId(@PathVariable(name = "diaryId") Long diaryId) throws IOException {
        photoService.deletePhotosByDiaryId(diaryId);
    }
}