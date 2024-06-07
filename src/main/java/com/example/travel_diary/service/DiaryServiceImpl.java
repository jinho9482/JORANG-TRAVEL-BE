package com.example.travel_diary.service;


import com.example.travel_diary.global.domain.entity.Diary;
import com.example.travel_diary.global.domain.repository.DiaryRepository;
import com.example.travel_diary.global.request.DiaryRequestDto;
import com.example.travel_diary.global.response.DiaryResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {

    private final DiaryRepository diaryRepository;

    @Override
    public void insertDiary(DiaryRequestDto req) {
        diaryRepository.save(req.toEntity());
    }

    @Override
    public List<DiaryResponse> getAllByPostId(Long postId) {
        List<Diary> diaries = diaryRepository.findAllByPost_Id(postId)
                .orElseThrow(IllegalArgumentException::new);
        List<DiaryResponse> diaryResponses = new ArrayList<>();
        diaries.forEach((el) ->
                diaryResponses.add(new DiaryResponse(
                        el.getId()
                        , el.getTitle()
                        , el.getDate()
                        , el.getScope()
                        , el.getCountry()
                        , el.getPhotos()
                ))

        );
        return diaryResponses;
    }

    @Override
    @Transactional
    public void updateDiary(Long id, DiaryRequestDto req) {
        Diary diary = diaryRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        diary.setTitle(req.title());
        diary.setDate(req.date());
        diary.setScope(req.scope());
        diary.setCountry(req.country());
        diary.setPhotos(req.photos());
    }

    @Override
    public void deleteById(Long id) {
        diaryRepository.deleteById(id);
    }
}
