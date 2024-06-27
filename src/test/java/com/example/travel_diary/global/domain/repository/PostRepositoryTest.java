package com.example.travel_diary.global.domain.repository;

import com.example.travel_diary.global.domain.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    @Test
    void fetchAll() {
        List<Post> posts = postRepository.fetchAll();
        posts.forEach(post -> System.out.println(post));

    }
}