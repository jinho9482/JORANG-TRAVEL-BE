package com.example.travel_diary.service;

import com.example.travel_diary.global.request.DiaryRequestDto;
import com.example.travel_diary.global.response.DiaryResponse;

import java.util.List;

public interface DiaryService {
    void insertDiary(DiaryRequestDto req);
    List<DiaryResponse> getAllByPostId(Long postId);
    void updateDiary(Long id, DiaryRequestDto req);
    void deleteById(Long id);
}
