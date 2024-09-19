package com.example.travel_diary.global.domain.repository;

import com.example.travel_diary.global.domain.entity.Diary;
import com.example.travel_diary.global.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findAllByPost_Id(Long postId);
    List<Diary> findAllByPost_User(User user);

    @Query(value = "SELECT DISTINCT d.content FROM diaries d " +
            "INNER JOIN posts p ON p.post_id = d.post_id " +
            "INNER JOIN users u ON u.user_id = p.user_id " +
            "WHERE p.user_id = :userId", nativeQuery = true)
    List<String> findMyDiaryContents(@Param("userId") UUID userId);
}
