package com.example.travel_diary.global.domain.repository;

import com.example.travel_diary.global.domain.entity.Diary;
import com.example.travel_diary.global.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findAllByPost_Id(Long postId);
    List<Diary> findAllByPost_User(User user);

}
