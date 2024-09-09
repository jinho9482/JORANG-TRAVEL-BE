package com.example.travel_diary.controller;

import com.example.travel_diary.global.domain.entity.Diary;
import com.example.travel_diary.global.request.DiarySaveRequest;
import com.example.travel_diary.global.request.DiaryUpdateRequest;
import com.example.travel_diary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/diaries")
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;

//    @PostMapping("/posts/{postId}")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Long createDiary(@PathVariable(name = "postId") Long postId) {
//        return diaryService.createDiary(postId);
//    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Long> createDiary(@RequestBody List<DiarySaveRequest> req) {
        return diaryService.createDiary(req);
    }

    @GetMapping("/posts/{postId}")
    public List<Diary> getAllByPostId(@PathVariable(name = "postId") Long postId) {
        return diaryService.getAllByPostId(postId);
    }

    @GetMapping("/{id}")
    public Diary getById(@PathVariable(name = "id") Long id) {
        return diaryService.getById(id);
    }

    @PutMapping
    public void updateDiary(@RequestBody List<DiaryUpdateRequest> req) {
        diaryService.updateDiary(req);
    }

    @DeleteMapping("/{id}")
    public void deleteDiaryById(@PathVariable(name = "id") Long id) {
        diaryService.deleteDiaryById(id);
    };

//    @GetMapping("/mypage")
//    public List<String> getDiaryByUserAndCountry(@AuthenticationPrincipal User user) {
//        return diaryService.getDiaryByUserAndCountry(user);
//    }

}
