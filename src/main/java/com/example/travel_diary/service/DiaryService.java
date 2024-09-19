package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Diary;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.request.DiarySaveRequest;
import com.example.travel_diary.global.request.DiaryUpdateRequest;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface DiaryService {
//    Long createDiary(Long postId);
    List<Long> createDiary(List<DiarySaveRequest> req);
    Diary getById(Long id);
    List<Diary> getAllByPostId(Long postId);
    void deleteDiaryById(Long id);
    void updateDiary(List<DiaryUpdateRequest> req);
//    List<String> getDiaryByUserAndCountry(User user);

//    String summarizeByPostId(Long postId);
//
//    String callPythonScript(List<Diary> dataList);

    List<String> getMyDiaryContents(User user);
}
