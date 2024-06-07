package com.example.travel_diary.controller;

import com.example.travel_diary.global.request.DiaryRequestDto;
import com.example.travel_diary.global.response.DiaryResponse;
import com.example.travel_diary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/diaries")
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;

    @PostMapping
    public void insertDiary(@RequestBody DiaryRequestDto req) {
        diaryService.insertDiary(req);
    }
    @GetMapping("/posts/{postId}")
    public List<DiaryResponse> getAllByPostId(@PathVariable Long postId) {
        return diaryService.getAllByPostId(postId);
    };
    @PutMapping("/{id}")
    public void updateDiary(@PathVariable Long id, @RequestBody DiaryRequestDto req) {
        diaryService.updateDiary(id, req);
    };
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        diaryService.deleteById(id);
    };
}
