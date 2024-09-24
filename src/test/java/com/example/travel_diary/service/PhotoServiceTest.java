//package com.example.travel_diary.service;
//
//import com.example.travel_diary.global.domain.entity.Diary;
//import com.example.travel_diary.global.domain.entity.Post;
//import com.example.travel_diary.global.domain.entity.User;
//import com.example.travel_diary.global.domain.repository.DiaryRepository;
//import com.example.travel_diary.global.domain.repository.PhotoRepository;
//import com.example.travel_diary.global.domain.repository.PostRepository;
//import com.example.travel_diary.global.domain.repository.UserRepository;
//import com.example.travel_diary.global.domain.type.Scope;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class PhotoServiceTest {
//    @Autowired
//    private PhotoService photoService;
//    @Autowired
//    private PostRepository postRepository;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private DiaryRepository diaryRepository;
//    @Autowired
//    private PhotoRepository photoRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    private final UUID USER_ID = UUID.randomUUID();
//    private Long postId;
//    private Long diaryId;
//    @BeforeEach
//    void setup() {
//        User user = User.builder()
//                .id(USER_ID)
//                .loginId("wlshzz123")
//                .name("jinho")
//                .nickname("Hola")
//                .password(passwordEncoder.encode("1234"))
//                .dateOfBirth(LocalDate.now())
//                .email("jinho@mail.com")
//                .createdAt(LocalDateTime.now())
//                .posts(new ArrayList<>())
//                .build();
//        userRepository.save(user);
//
//        Post post = Post.builder()
//                .createdAt(LocalDateTime.now())
//                .love(100)
//                .title("Public")
//                .likes(new ArrayList<>())
//                .diaries(new ArrayList<>())
//                .country("Korea")
//                .scope(Scope.PUBLIC)
//                .user(user)
//                .expenses(new ArrayList<>())
//                .isPublished(true)
//                .build();
//
//        Diary diary = Diary.builder()
//                .title("Toronto")
//                .content("Toronto nice")
//                .date(LocalDate.now())
//                .createdAt(LocalDateTime.now())
//                .photos(new ArrayList<>())
//                .post(post)
//                .build();
//
//        Post savedPost = postRepository.save(post);
//        Diary savedDiary = diaryRepository.save(diary);
//
//        postId = savedPost.getId();
//        diaryId = savedDiary.getId();
//    }
//
//    @Test
//    @Transactional
//    void insert_success() {
//        MockMultipartFile file = new MockMultipartFile("image",
//                "test.jpg",
//                "multipart/form-data",
//                "TestImage/test.jpg".getBytes());
//
//    }
//
//    @Test
//    @Transactional
//    void savePhotos_success() {
//    }
//
//    @Test
//    @Transactional
//    void deletePhotosByDiaryId_success() {
//    }
//}