package com.example.travel_diary.global.domain.repository;

import com.example.travel_diary.global.domain.entity.Photo;
import com.example.travel_diary.global.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
