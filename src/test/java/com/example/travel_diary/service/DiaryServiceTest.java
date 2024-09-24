package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.*;
import com.example.travel_diary.global.domain.repository.DiaryRepository;
import com.example.travel_diary.global.domain.repository.PostRepository;
import com.example.travel_diary.global.domain.repository.UserRepository;
import com.example.travel_diary.global.domain.type.Scope;
import com.example.travel_diary.global.exception.DiaryNotFoundException;
import com.example.travel_diary.global.request.DiarySaveRequest;
import com.example.travel_diary.global.request.DiaryUpdateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
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
class DiaryServiceTest {

    @Autowired
    private DiaryService diaryService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DiaryRepository diaryRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UUID USER_ID = UUID.randomUUID();
    private List<Long> postIds = new ArrayList<>();
    private List<Long> diaryIds = new ArrayList<>();
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

            Post savedPost = postRepository.save(post);
            Diary savedDiary = diaryRepository.save(publicDiary);

            postIds.add(savedPost.getId());
            diaryIds.add(savedDiary.getId());
        }
    }


    @Test
    @Transactional
    void createDiary_success() {
        List<DiarySaveRequest> diarySaveRequests = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            DiarySaveRequest diarySaveRequest = new DiarySaveRequest(
                postIds.get(0),
                "Toronto" + i,
                "Toronto nice" + i,
                LocalDate.now()
            );
            diarySaveRequests.add(diarySaveRequest);
        }
        List<Long> savedDiaryIds = diaryService.createDiary(diarySaveRequests);
        Long targetDiaryId = savedDiaryIds.get(0);
        Diary diary = diaryRepository.findById(targetDiaryId).get();

        assertNotNull(diary);
        assertEquals("Toronto1", diary.getTitle());
    }

    @Nested
    @Transactional
    class deleteDiaryById {
        @Test
        void success() {
            Long targetDiaryId = diaryIds.get(0);
            diaryService.deleteDiaryById(targetDiaryId);

            assertThrows(DiaryNotFoundException.class, () ->
                    diaryService.deleteDiaryById(targetDiaryId)
            );
        }
        @Test
        void fail_diary_not_found() {
            assertThrows(DiaryNotFoundException.class, () ->
                    diaryService.deleteDiaryById(100L)
            );
        }
    }

    @Nested
    @Transactional
    class updateDiary {
        @Test
        void success() {
            List<DiaryUpdateRequest> diaryUpdateRequests = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                DiaryUpdateRequest diaryUpdateRequest = new DiaryUpdateRequest(
                        diaryIds.get(i),
                        "Korea" + i,
                        "Korea nice" + i,
                        LocalDate.now()
                );
                diaryUpdateRequests.add(diaryUpdateRequest);
            }
            diaryService.updateDiary(diaryUpdateRequests);
            Long targetDiaryId = diaryIds.get(0);
            Diary diary = diaryRepository.findById(targetDiaryId).get();

            assertNotNull(diary);
            assertEquals("Korea0", diary.getTitle());
        }
        @Test
        void fail_diary_not_found() {
            List<DiaryUpdateRequest> diaryUpdateRequests = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                DiaryUpdateRequest diaryUpdateRequest = new DiaryUpdateRequest(
                        100L,
                        "Korea" + i,
                        "Korea nice" + i,
                        LocalDate.now()
                );
                diaryUpdateRequests.add(diaryUpdateRequest);
            }
            assertThrows(DiaryNotFoundException.class, () ->
                    diaryService.updateDiary(diaryUpdateRequests)
            );
        }
    }

    @Test
    @Transactional
    void getMyDiaryContents_success() {
        User user = User.builder().id(USER_ID).build();
        List<String> contents = diaryService.getMyDiaryContents(user);

        assertNotNull(contents);
        assertEquals(5, contents.size());
        assertEquals("PublicDiary1", contents.get(0));
    }
}