package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Photo;
import com.example.travel_diary.global.request.PhotoRequest;


import java.io.IOException;
import java.util.List;

public interface PhotoService {
    // 저장
    void insert(PhotoRequest req) throws IOException;
    void savePhotos(PhotoRequest req) throws IOException;
    void deletePhotosByDiaryId(Long diaryId) throws IOException;
}