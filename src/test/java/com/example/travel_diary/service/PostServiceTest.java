package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.*;
import com.example.travel_diary.global.domain.repository.*;
import com.example.travel_diary.global.domain.type.Scope;
import com.example.travel_diary.global.exception.PostNotFoundException;
import com.example.travel_diary.global.exception.PostNotPublicException;
import com.example.travel_diary.global.request.PostRequest;
import static org.junit.jupiter.api.Assertions.*;

import com.example.travel_diary.global.request.PostTempRequest;
import com.example.travel_diary.global.response.CountryCostDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
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
import java.util.UUID;


@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DiaryRepository diaryRepository;
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private ExpenseDetailRepository expenseDetailRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UUID USER_ID = UUID.randomUUID();
    private final PostRequest postRequest = new PostRequest("토론토", "캐나다", Scope.PUBLIC);
    private final PostTempRequest postTempRequest = new PostTempRequest("토론토");
    private List<Post> posts;
    private int length;
    private Long lastPostId;
    private User savedUser;


    @BeforeEach
    void setUp() {
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


        for (int i = 1; i < 6; i++) {
            boolean isPublished = true;
            if (i == 1) isPublished = false;

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
                    .isPublished(isPublished)
                    .build();

            Diary publicDiary = Diary.builder()
                    .title("PublicDiary"+ i)
                    .content("PublicDiary" + i)
                    .date(LocalDate.now().plusDays(i))
                    .createdAt(LocalDateTime.now())
                    .photos(new ArrayList<>())
                    .post(post)
                    .build();

            Expense publicExpense = Expense.builder()
                    .date(LocalDate.now())
                    .post(post)
                    .build();

            ExpenseDetail publicExpenseDetail = ExpenseDetail.builder()
                    .category("교통비")
                    .place("버스")
                    .cost(10000)
                    .expense(publicExpense)
                    .build();

            postRepository.save(post);
            diaryRepository.save(publicDiary);
            expenseRepository.save(publicExpense);
            expenseDetailRepository.save(publicExpenseDetail);
        }

        for (int i = 1; i < 6; i++) {
            boolean isPublished = true;
            if (i == 1) isPublished = false;
            Post post = Post.builder()
                    .createdAt(LocalDateTime.now().plusDays(i))
                    .love(i)
                    .title("Personal" + i)
                    .likes(new ArrayList<>())
                    .diaries(new ArrayList<>())
                    .country("Korea")
                    .scope(Scope.PERSONAL)
                    .user(user)
                    .expenses(new ArrayList<>())
                    .isPublished(isPublished)
                    .build();

            Diary personalDiary = Diary.builder()
                    .title("PersonalDiary"+ i)
                    .content("PersonalDiary" + i)
                    .date(LocalDate.now().plusDays(i))
                    .createdAt(LocalDateTime.now())
                    .photos(new ArrayList<>())
                    .post(post)
                    .build();

            Expense personalExpense = Expense.builder()
                    .date(LocalDate.now())
                    .post(post)
                    .build();

            ExpenseDetail personalExpenseDetail = ExpenseDetail.builder()
                    .category("교통비")
                    .place("버스")
                    .cost(10000)
                    .expense(personalExpense)
                    .build();


            diaryRepository.save(personalDiary);
            expenseRepository.save(personalExpense);
            expenseDetailRepository.save(personalExpenseDetail);
            postRepository.save(post);
        }

        posts = postRepository.findAll();
        length = posts.size();
        lastPostId = posts.get(length-1).getId();
        savedUser = User.builder().id(USER_ID).build();
    }


    @Test
    @Transactional
    void createPost_success() {
        Long savedPostId = postService.createPost(savedUser, postRequest);
        Post post = postRepository.findById(savedPostId).get();

        assertEquals("토론토", post.getTitle());
    }

    @Test
    @Transactional
    void createTemporaryPost_success() {
        Long savedPostId = postService.createTemporaryPost(savedUser, postTempRequest);
        Post post = postRepository.findById(savedPostId).get();

        assertEquals("토론토", post.getTitle());
    }

    @Nested
    @Transactional
    class updateTemporaryPost {
        @Test
        void success() {
            postService.updateTemporaryPost(lastPostId - 5, postTempRequest);
            Post post = postRepository.findById(lastPostId - 5).get();

            assertEquals(lastPostId - 5, post.getId());
            assertEquals("토론토", post.getTitle());
            assertFalse(post.isPublished());
        }
        @Test
        void fail_post_not_found() {
            assertThrows(PostNotFoundException.class
                    , () -> postService.updateTemporaryPost(100L, postTempRequest));
        }
    }

    @Nested
    @Transactional
    class deleteById {
        @Test
        void success() {
            postService.deleteById(lastPostId);

            assertThrows(PostNotFoundException.class
                    , () -> postService.deleteById(lastPostId));
        }
        @Test
        void fail_post_not_found() {
            assertThrows(PostNotFoundException.class
                    , () -> postService.deleteById(100L));
        }
    }

    @Nested
    @Transactional
    class updatePost {
        @Test
        void success() {
            postService.updatePost(lastPostId, postRequest);
            Post post = postRepository.findById(lastPostId).get();

            assertEquals(lastPostId, post.getId());
            assertEquals("캐나다", post.getCountry());
        }
        @Test
        void fail_post_not_found() {
            assertThrows(PostNotFoundException.class
                    , () -> postService.updatePost(100L, postRequest));
        }
    }

    @Nested
    @Transactional
    class getById {
        @Test
        void success() {
            postService.getById(lastPostId - 5);
            Post post = postRepository.findById(lastPostId - 5).get();

            assertNotNull(post);
            assertEquals(lastPostId - 5, post.getId());
            assertEquals( "Public5", post.getTitle());
        }
        @Test
        void fail_post_not_found() {
            assertThrows(PostNotFoundException.class
                    , () -> postService.getById(100L));
        }
        @Test
        void fail_post_not_public() {
            assertThrows(PostNotPublicException.class
                    , () -> postService.getById(lastPostId));
        }
    }

    @Nested
    @Transactional
    class getMyPostById {
        @Test
        void success() {
            Post post = postService.getMyPostById(savedUser, lastPostId);

            assertNotNull(post);
            assertEquals(lastPostId, post.getId());
            assertEquals("Personal5", post.getTitle());
        }
        @Test
        void fail_post_not_found() {
            assertThrows(PostNotFoundException.class
                    , () -> postService.getMyPostById(savedUser,100L));
        }
    }

    @Test
    @Transactional
    void getRecentPostsFirst_success() {
        List<Post> posts = postService.getRecentPostsFirst();
        Post firstPost = posts.get(0);

        assertNotNull(firstPost);
        assertNotEquals(lastPostId, firstPost.getId()); // 마지막 post는 비공개이므로 안 가져옴
        assertEquals(Scope.PUBLIC, firstPost.getScope());
        assertEquals( "Public5", firstPost.getTitle());
    }

    @Test
    @Transactional
    void getRecent5PostsByCountry_success() {
        List<Post> posts = postService.getRecent5PostsByCountry("Korea");
        Post firstPost = posts.get(0);

        assertEquals(4, posts.size()); // 5개 post 중 1개는 발행 안 되어서 빠짐
        assertNotNull(firstPost);
        assertNotEquals(lastPostId, firstPost.getId()); // 마지막 post는 비공개이므로 안 가져옴
        assertEquals(Scope.PUBLIC, firstPost.getScope());
        assertEquals( "Public5", firstPost.getTitle());
    }

    @Test
    @Transactional
    void getRecentPostsFirstByCountry_success() {
        List<Post> posts = postService.getRecentPostsFirstByCountry("Korea");
        Post firstPost = posts.get(0);

        assertNotNull(firstPost);
        assertNotEquals(lastPostId, firstPost.getId()); // 마지막 post는 비공개이므로 안 가져옴
        assertEquals(Scope.PUBLIC, firstPost.getScope());
        assertEquals( "Public5", firstPost.getTitle());
    }

    @Test
    @Transactional
    void getTop5LikeOnThisWeek_success() {
        List<Post> posts = postService.getTop5LikeOnThisWeek();
        Post firstPost = posts.get(0);

        assertEquals(4, posts.size());
        assertNotNull(firstPost);
        assertEquals(5, firstPost.getLove());
        assertEquals("Public5", firstPost.getTitle());
    }

    @Test
    @Transactional
    void getAllByUser_success() {
        List<Post> posts = postService.getAllByUser(savedUser);

        assertNotNull(posts);
        assertEquals(10, posts.size());
        assertEquals("Personal5", posts.get(0).getTitle());
    }

    @Test
    @Transactional
    void getMyPublishedPostsPerPage_success() {
        Page<Post> posts = postService.getMyPublishedPostsPerPage(savedUser, 10);

        assertNotNull(posts);
        assertEquals(8, posts.getTotalElements()); // 2개는 발행이 안 되어서 빠짐
    }

    @Test
    @Transactional
    void getTop5RecentPosts_success() {
        List<Post> posts = postService.getTop5RecentPosts();
        Post firstPost = posts.get(0);

        assertEquals(5, posts.size()); // 5개 중 1개는 발행이 안 되어서 빠짐
        assertNotNull(firstPost);
        assertNotEquals(lastPostId, firstPost.getId()); // 마지막 post는 비공개이므로 안 가져옴
        assertEquals(Scope.PUBLIC, firstPost.getScope());
        assertEquals( "Public5", firstPost.getTitle());
    }

    @Test
    @Transactional
    void getUnpublishedPosts_success() {
        List<Post> posts = postService.getUnpublishedPosts(savedUser);
        Post firstPost = posts.get(0);

        assertEquals(2, posts.size()); // 공개, 비공개 중 1개씩
        assertNotNull(firstPost);
        assertEquals(Scope.PERSONAL, firstPost.getScope());
        assertEquals( "Personal1", firstPost.getTitle());
    }

    @Test
    @Transactional
    void getUnpublishedPostsPerPage_success() {
        Page<Post> posts = postService.getUnpublishedPostsPerPage(savedUser, 10);

        assertNotNull(posts);
        assertEquals(2, posts.getTotalElements()); // 공개, 비공개 중 1개씩
    }

    @Test
    @Transactional
    void getPublishedPosts_success() {
        List<Post> posts = postService.getPublishedPosts(savedUser);
        Post firstPost = posts.get(0);

        assertEquals(8, posts.size()); // 공개, 비공개 중 1개씩 빠짐
        assertNotNull(firstPost);
        assertEquals(Scope.PERSONAL, firstPost.getScope());
        assertEquals( "Personal5", firstPost.getTitle());
    }

    @Test
    @Transactional
    void getCountriesVisited_success() {
        Long savedPostId = postService.createPost(savedUser, postRequest);
        List<String> countries = postService.getCountriesVisited(savedUser);

        assertEquals(2, countries.size()); // 공개, 비공개 중 1개씩 빠짐
        assertNotNull(countries);
        assertEquals( "캐나다", countries.get(countries.size()-1));
    }

    @Test
    @Transactional
    void getTotalCostPerCountry_success() {
        List<CountryCostDto> countryCosts = postService.getTotalCostPerCountry(savedUser);
        CountryCostDto countryCost = countryCosts.get(0);

        assertNotNull(countryCost);
        assertEquals(1, countryCosts.size());
        assertEquals("Korea", countryCost.country());
        assertEquals(80000, countryCost.totalCost());
    }

    @Test
    @Transactional
    void getPostsByKeyword_success() {
        List<Post> posts = postService.getPostsByKeyword("Public");
        Post firstPost = posts.get(0);

        assertEquals(4, posts.size()); // 공개, 비공개 중 1개씩 빠짐
        assertNotNull(firstPost);
        assertEquals(Scope.PUBLIC, firstPost.getScope());
        assertEquals( "Public5", firstPost.getTitle());
    }
}