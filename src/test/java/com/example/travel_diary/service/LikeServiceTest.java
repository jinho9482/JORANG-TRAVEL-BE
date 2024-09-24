package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Diary;
import com.example.travel_diary.global.domain.entity.Like;
import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.domain.repository.*;
import com.example.travel_diary.global.domain.type.Scope;
import com.example.travel_diary.global.response.LikePageResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LikeServiceTest {
    @Autowired
    private LikeService likeService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UUID USER_ID = UUID.randomUUID();
    private List<Long> postIds = new ArrayList<>();

    @BeforeEach
    void setup() {
        User user = User.builder()
                .id(USER_ID)
                .loginId("wlshzz123")
                .name("jinho")
                .nickname("Hola")
                .password(passwordEncoder.encode("1234"))
                .dateOfBirth(LocalDate.now())
                .email("jinho@mail.com")
                .createdAt(LocalDateTime.now())
                .posts(new ArrayList<>())
                .build();
        userRepository.save(user);

        for (int i = 1; i < 15; i++) {
            Post post = Post.builder()
                    .createdAt(LocalDateTime.now())
                    .love(i)
                    .title("Public" + i)
                    .likes(new ArrayList<>())
                    .diaries(new ArrayList<>())
                    .country("Korea")
                    .scope(Scope.PUBLIC)
                    .user(user)
                    .expenses(new ArrayList<>())
                    .isPublished(true)
                    .build();


            Post savedPost = postRepository.save(post);
            postIds.add(savedPost.getId());

            Like like = Like.builder()
                    .user(user)
                    .post(post)
                    .build();

            likeRepository.save(like);
        }
    }
    @Test
    @Transactional
    void likePost_success() {
        User user = User.builder().id(USER_ID).build();
        int res = likeService.likePost(user, postIds.get(0));
        Post post = postRepository.findById(postIds.get(0)).get();

        assertEquals(-1, res);
        assertEquals(0, post.getLove());
    }

    @Test
    @Transactional
    void checkLike_success() {
        User user = User.builder().id(USER_ID).build();
        Boolean res = likeService.checkLike(user, postIds.get(0));

        assertEquals(true, res);
    }

    @Test
    @Transactional
    void getLikedPostsByUserPerPage_success() {
        User user = User.builder().id(USER_ID).build();
        LikePageResponse res = likeService.getLikedPostsByUserPerPage(user, 1);
        res.content().forEach(el -> System.out.println(el.getTitle()));
        int totalPage = res.totalPage();
        Post post = res.content().get(9);

        assertEquals(2, totalPage);
        assertEquals("Public5", post.getTitle());
    }
}