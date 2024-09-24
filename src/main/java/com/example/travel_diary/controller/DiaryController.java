package com.example.travel_diary.controller;

import com.example.travel_diary.global.domain.entity.Diary;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.request.DiarySaveRequest;
import com.example.travel_diary.global.request.DiaryUpdateRequest;
import com.example.travel_diary.service.DiaryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/diaries")
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "여행기 성성")
    public List<Long> createDiary(@RequestBody List<DiarySaveRequest> req) {
        return diaryService.createDiary(req);
    }

    @PutMapping
    @Operation(summary = "여행기 업데이트")
    public void updateDiary(@RequestBody List<DiaryUpdateRequest> req) {
        diaryService.updateDiary(req);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "여행기 삭제")
    public void deleteDiaryById(@PathVariable(name = "id") Long id) {
        diaryService.deleteDiaryById(id);
    };


    @GetMapping("/chatbot")
    @Operation(summary = "나의 여행기 내용 가져오기 (챗봇용)")
    public List<String> getMyDiaryContents(@AuthenticationPrincipal User user) {
        return diaryService.getMyDiaryContents(user);
    }
}
