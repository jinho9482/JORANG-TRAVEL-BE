package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Diary;
import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.domain.repository.PostRepository;
import com.example.travel_diary.global.domain.repository.UserRepository;
import com.example.travel_diary.global.domain.type.Scope;
import com.example.travel_diary.global.exception.PostNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostServiceImplTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private User user;
    private Post post;



    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(UUID.randomUUID())
                .loginId("testLoginId")
                .name("testName")
                .nickname("testNickname")
                .password(passwordEncoder.encode("testPassword"))
                .dateOfBirth(LocalDate.now())
                .email("testEmail")
                .createdAt(LocalDateTime.now())
                .posts(new ArrayList<>())
                .build();
        userRepository.save(user);



        for (int i = 0; i < 20; i++) {
            Post post = Post.builder()
                    .createdAt(LocalDateTime.now().plusDays(i))
                    .love(i)
                    .title("testTitle" + i)
                    .likes(new ArrayList<>())
                    .diaries(new ArrayList<>())
                    .user(user)
                    .expenses(new ArrayList<>())
                    .build();

            Diary publicDiary = Diary.builder()
                    .title("publicTitle"+ i)
                    .content("publicContent" + i)
                    .date(LocalDate.now().plusDays(i))
                    .scope(Scope.PUBLIC)
                    .createdAt(LocalDateTime.now())
                    .country("publicCountry" + i)
                    .photos(new ArrayList<>())
                    .post(post)
                    .build();

            post.getDiaries().add(publicDiary);
            postRepository.save(post);
        }

        for (int i = 20; i < 40; i++) {
            Post post = Post.builder()
                    .createdAt(LocalDateTime.now().plusDays(i))
                    .love(i)
                    .title("testTitle" + i)
                    .likes(new ArrayList<>())
                    .diaries(new ArrayList<>())
                    .user(user)
                    .expenses(new ArrayList<>())
                    .build();

            Diary personalDiary = Diary.builder()
                    .title("personalTitle" + i)
                    .content("personalContent" + i)
                    .date(LocalDate.now().plusDays(i))
                    .scope(Scope.PERSONAL)
                    .createdAt(LocalDateTime.now())
                    .country("personalCountry" + i)
                    .photos(new ArrayList<>())
                    .post(post)
                    .build();

            post.getDiaries().add(personalDiary);
            postRepository.save(post);
        }
    }

    @Test
    void createPost_Success() {
        Long postId = postService.createPost(user);
        assertTrue(postRepository.findById(postId).isPresent());
    }

    @Test
    void deleteById_Success() {
        Post post = postRepository.findAll().get(0);
        Long postId = post.getId();
        postService.deleteById(postId);
        assertFalse(postRepository.findById(post.getId()).isPresent());
    }

    @Test
    void deleteById_Fail() {
        Long notExistPostId = -1L;
        assertThrows(PostNotFoundException.class, () -> {
            postService.deleteById(notExistPostId);
        });
    }

    @Test
    void update_Success() {
        Post post = postRepository.findAll().get(0);
        LocalDateTime beforeUpdate = post.getCreatedAt();
        postService.update(post.getId(), "changeTitle");
        Optional<Post> byId = postRepository.findById(post.getId());
        assertTrue(byId.isPresent());
        Post updatePost = byId.get();
        LocalDateTime afterUpdate = updatePost.getCreatedAt();
        assertEquals("changeTitle",updatePost.getTitle());
        assertNotEquals(beforeUpdate, afterUpdate);
    }

    @Test
    void getAll_Success() {
        List<Post> all = postService.getAll();
        for (Post post : all) {
            assertEquals(Scope.PUBLIC,post.getDiaries().get(0).getScope());
        }
    }

    @Test
    void getById_Success() {
        Post post = postRepository.findAll().get(0);
        Post testPost = postService.getById(post.getId());
        Optional<Post> byId1 = postRepository.findById(post.getId());
        assertTrue(byId1.isPresent());
        Post post1 = byId1.get();
        assertEquals(post1,testPost);
    }


    @Test
    void getRecentPostsFirst_Success() {
        List<Post> recentPostsFirst = postService.getRecentPostsFirst();
        assertEquals("testTitle19",recentPostsFirst.get(0).getTitle());
    }

    @Test
    void getRecent5PostsByCountry() {

    }

    @Test
    void getRecentPostsFirstByCountry() {
    }

    @Test
    void getTop5LikeOnThisWeek() {
//        List<Post> top5LikeOnThisWeek = postService.getTop5LikeOnThisWeek();
//        assertEquals(1, top5LikeOnThisWeek.size());
//        assertEquals(0,top5LikeOnThisWeek.get(0).getLove());
    }

    @Test
    void getRecentPostsFirstBetweenTheseDates() {
        List<Post> recentPostsFirstBetweenTheseDates = postService.getRecentPostsFirstBetweenTheseDates(LocalDate.now(), LocalDate.now().plusDays(4));
        assertEquals("testTitle4",recentPostsFirstBetweenTheseDates.get(0).getTitle());
        assertEquals("testTitle3",recentPostsFirstBetweenTheseDates.get(1).getTitle());
        assertEquals("testTitle2",recentPostsFirstBetweenTheseDates.get(2).getTitle());
        assertEquals("testTitle1",recentPostsFirstBetweenTheseDates.get(3).getTitle());
        assertEquals("testTitle0",recentPostsFirstBetweenTheseDates.get(4).getTitle());
    }

    @Test
    void getAllByUser() {
        List<Post> allByUser = postService.getAllByUser(user);
        assertEquals(40, allByUser.size());
        assertEquals("testTitle39",allByUser.get(0).getTitle());
        assertEquals("testTitle0",allByUser.get(39).getTitle());
    }

    @Test
    void getList_Success() {
        int page = 0;
        Page<Post> postPage = postService.getList(user, page);
        assertEquals(10, postPage.getNumberOfElements());
        List<Post> posts = postPage.getContent();
        assertEquals("testTitle39", posts.get(0).getTitle());
        page = 3;
        postPage = postService.getList(user,page);
        assertEquals("testTitle9",postPage.getContent().get(0).getTitle());
    }

    @Test
    void getTop5RecentPosts() {
        List<Post> top5RecentPosts = postService.getTop5RecentPosts();
        assertEquals(5,top5RecentPosts.size());
        assertEquals("testTitle19",top5RecentPosts.get(0).getTitle());
        assertEquals("testTitle18",top5RecentPosts.get(1).getTitle());
        assertEquals("testTitle17",top5RecentPosts.get(2).getTitle());
        assertEquals("testTitle16",top5RecentPosts.get(3).getTitle());
        assertEquals("testTitle15",top5RecentPosts.get(4).getTitle());
    }
}