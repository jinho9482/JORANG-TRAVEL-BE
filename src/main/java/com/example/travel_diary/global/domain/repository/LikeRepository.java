package com.example.travel_diary.global.domain.repository;

import com.example.travel_diary.global.domain.entity.Like;
import com.example.travel_diary.global.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
