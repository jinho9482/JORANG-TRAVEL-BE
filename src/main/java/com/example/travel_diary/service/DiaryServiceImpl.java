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
    public void updateDiary(Long id, DiaryRequestDto req) {
        Diary diary = diaryRepository.findById(id).orElseThrow(DiaryNotFoundException::new);
        diary.setTitle(req.title());
        diary.setContent(req.content());
        diary.setScope(req.scope());
        diary.setDate(req.date());
        diary.setCountry(req.country().toLowerCase());
        diary.setCreatedAt(LocalDateTime.now());
    }

    @Override
    @Transactional
    public void deleteDiaryById(Long id) {
        Diary diary = diaryRepository.findById(id).orElseThrow(DiaryNotFoundException::new);
        diaryRepository.deleteById(id);
    }

    @Override
    public List<String> getDiaryByUserAndCountry(User user) {
        List<Diary> allByPostUser = diaryRepository.findAllByPost_User(user);
        List<String> countryByUser = new ArrayList<>();
        for(Diary diary : allByPostUser) {
            if(!countryByUser.contains(diary.getCountry())) {
                countryByUser.add(diary.getCountry());
            }
        }
        return countryByUser;
    }


}



