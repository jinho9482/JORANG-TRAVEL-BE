package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Diary;
import com.example.travel_diary.global.domain.entity.Photo;
import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.domain.repository.DiaryRepository;
import com.example.travel_diary.global.domain.repository.PhotoRepository;
import com.example.travel_diary.global.domain.repository.PostRepository;
import com.example.travel_diary.global.domain.repository.UserRepository;
import com.example.travel_diary.global.domain.type.Scope;
import com.example.travel_diary.global.request.PhotoRequest;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class PhotoServiceTest {
    @Autowired
    private PhotoService photoService;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private DiaryRepository diaryRepository;
    @Autowired
    private PostRepository postRepository;

    @Test
    @Transactional
    void insert_success() throws Exception {
        Post post = Post.builder().title("post").build();
        Post savedPost = postRepository.save(post);

        Diary diary = Diary.builder().title("diary").post(savedPost).build();
        Diary savedDiary = diaryRepository.save(diary);

        MultipartFile file1 = new MockMultipartFile("files", "img1", MediaType.MULTIPART_FORM_DATA_VALUE, "img1.jpg".getBytes());
        MultipartFile file2 = new MockMultipartFile("files", "img2", MediaType.MULTIPART_FORM_DATA_VALUE, "img2.jpg".getBytes());
        PhotoRequest req = new PhotoRequest(savedPost.getId(), savedDiary.getId(), new MultipartFile[]{file1, file2});
        photoService.insert(req);

        List<Photo> photos = photoRepository.findAllByDiary_Id(savedDiary.getId());

        assertEquals(2, photos.size());
    }

    @Test
    @Transactional
    void deletePhotosByDiaryId_success() throws IOException {
        Diary diary = Diary.builder().id(10000L).build();
        Diary savedDiary = diaryRepository.save(diary);
        Photo photo = Photo.builder()
                .diary(savedDiary)
                .storagePath("posts/10000/diaries/500/images/1")
                .build();

        photoRepository.save(photo);
        photoService.deletePhotosByDiaryId(10000L);

        List<Photo> photos = photoRepository.findAllByDiary_Id(10000L);

        assertNotNull(photos);
        assertEquals(0, photos.size());
    }
}