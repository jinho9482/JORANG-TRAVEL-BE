package com.example.travel_diary.global.domain.repository;

import com.example.travel_diary.global.domain.entity.Like;
import com.example.travel_diary.global.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUser_IdAndPost_Id(UUID userId, Long postId);
    Optional<Like> findByPost_Id(Long postId);
    void deleteByUser_IdAndPost_Id(UUID userId, Long postId);
//    List<Like> findAllByUser(User user);
    List<Like> findAllByUserOrderByIdDesc(User user);
    Long countByPost_Id(Long postId);

    Page<Like> findAllByUser(User user, Pageable pageable);
}
