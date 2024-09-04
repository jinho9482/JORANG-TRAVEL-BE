
package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Diary;
import com.example.travel_diary.global.domain.entity.Photo;
import com.example.travel_diary.global.domain.repository.PhotoRepository;
import com.example.travel_diary.global.exception.PhotoLimitExceededException;
import com.example.travel_diary.global.exception.PhotoNotFoundException;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {
    private final PhotoRepository photoRepository;
    private final DiaryService diaryService;
    private final String GCP_PROJECT_ID;
    private final String BUCKET_NAME;
    private final Storage storage;

    public PhotoServiceImpl(PhotoRepository photoRepository,
                            DiaryService diaryService,
                            @Value("${gcp.project.id}") String GCP_PROJECT_ID,
                            @Value("${gcp.project.storage.bucket}") String BUCKET_NAME) {
        this.photoRepository = photoRepository;
        this.diaryService = diaryService;
        this.GCP_PROJECT_ID = GCP_PROJECT_ID;
        this.BUCKET_NAME = BUCKET_NAME;
        this.storage = StorageOptions.newBuilder().setProjectId(GCP_PROJECT_ID).build().getService();
    }
    @Override
    @Transactional
    public void insert(Long diaryId, MultipartFile[] files) throws IOException {
        Diary diary = diaryService.getById(diaryId);
        List<Photo> photos = photoRepository.findAllByDiary_Id(diaryId);
        if (photos.size() + files.length > 5) throw new PhotoLimitExceededException();
        // photo id를 알 때 google 에서 사진 정보를 어떻게 가져오지? blob Id도 저장을 해야할 거 같다. (ex. diary/1/image/1)
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            String storagePath = "posts/" + diary.getPost().getId() + "/diaries/" + diaryId + "/images/" + (i+1+photos.size());
            BlobId blobId = BlobId.of(BUCKET_NAME, storagePath);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
            try {
//                    storage.createFrom(blobInfo, Paths.get(el.paths()[i]));
                InputStream inputStream = file.getInputStream();
                storage.createFrom(blobInfo, inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String googlePath = storage.get(blobId).getMediaLink();
            photoRepository.save(Photo.builder().storagePath(storagePath).photoURL(googlePath).diary(diary).build());
        }
    };


    @Override
    public Photo getById(Long id) {
        return photoRepository.findById(id).orElseThrow(PhotoNotFoundException::new);
    }

    @Override
    public List<Photo> getByDiaryId(Long diaryId) {
        return photoRepository.findAllByDiary_Id(diaryId);
    }

    @Override
    @Transactional
    public void update(Long id, MultipartFile file) throws IOException {
        Photo photo = photoRepository.findById(id).orElseThrow(PhotoNotFoundException::new);
        BlobId blobId = BlobId.of(BUCKET_NAME, photo.getStoragePath());
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        try {
            InputStream inputStream = file.getInputStream();
            storage.createFrom(blobInfo, inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String googlePath = storage.get(blobId).getMediaLink();
        photo.setPhotoURL(googlePath);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Photo photo = photoRepository.findById(id).orElseThrow(PhotoNotFoundException::new);
        BlobId blobId = BlobId.of(BUCKET_NAME, photo.getStoragePath());
        storage.delete(blobId);
        photoRepository.deleteById(id);
    }
}
