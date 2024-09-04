package com.example.travel_diary.service;


import com.example.travel_diary.global.domain.entity.Diary;
import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.domain.repository.DiaryRepository;
import com.example.travel_diary.global.exception.DiaryNotFoundException;
import com.example.travel_diary.global.request.DiaryRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    @Transactional
    public Long createDiary(Long postId) {
        Post post = Post.builder().id(postId).build();
        Diary diary = Diary.builder().post(post).build();
        return diaryRepository.save(diary).getId();
    }

    @Override
    public Diary getById(Long id) {
        return diaryRepository.findById(id).orElseThrow(DiaryNotFoundException::new);
    }

    @Override
    public List<Diary> getAllByPostId(Long postId) {
        return diaryRepository.findAllByPost_Id(postId);
    }


    @Override
    @Transactional
    public void deleteDiaryById(Long id) {
        Diary diary = diaryRepository.findById(id).orElseThrow(DiaryNotFoundException::new);
        diaryRepository.deleteById(id);
    }


    @Override
    @Transactional
    public void updateDiary(List<DiaryRequestDto> req) {
        req.forEach(el -> {
            Diary diary = diaryRepository.findById(el.id()).orElseThrow(DiaryNotFoundException::new);
            diary.setTitle(el.title());
            diary.setContent(el.content());
            diary.setDate(el.date());
            diary.setCreatedAt(LocalDateTime.now());
        });
    }
}



