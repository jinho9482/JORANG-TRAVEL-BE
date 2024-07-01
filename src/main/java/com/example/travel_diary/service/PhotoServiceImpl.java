
//package com.example.travel_diary.service;
//
//import com.example.travel_diary.global.domain.entity.Diary;
//import com.example.travel_diary.global.domain.entity.Photo;
//import com.example.travel_diary.global.domain.repository.PhotoRepository;
//import com.example.travel_diary.global.exception.PhotoLimitExceededException;
//import com.example.travel_diary.global.exception.PhotoNotFoundException;
//import com.example.travel_diary.global.request.PhotoRequestDto;
//import com.google.cloud.storage.BlobId;
//import com.google.cloud.storage.BlobInfo;
//import com.google.cloud.storage.Storage;
//import com.google.cloud.storage.StorageOptions;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;

//
//import java.io.IOException;

//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;

//import java.nio.file.Paths;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class PhotoServiceImpl implements PhotoService {
//    private final PhotoRepository photoRepository;

//
//
//    private final DiaryService diaryService;
//

//    private final DiaryService diaryService;

//    private final String bucketName = "jorang";
//    private final Storage storage =  StorageOptions.newBuilder().setProjectId("titanium-vision-424101-s9").build().getService();
//
//    @Override
//    @Transactional

//    public void insert(PhotoRequestDto req, Long diaryId) throws IOException {
//        Diary diary = diaryService.getById(diaryId);
//
//        List<Photo> photos = photoRepository.findAllByDiary_Id(diaryId);
//        if (photos.size() + req.paths().length > 5) throw new PhotoLimitExceededException();
//        // photo id를 알 때 google 에서 사진 정보를 어떻게 가져오지? blob Id도 저장을 해야할 거 같다. (ex. diary/1/image/1)
//        for (int i = 0; i < req.paths().length; i++) {
//            String storagePath = "posts/" + diary.getPost().getId() + "/diaries/" + diaryId + "/images/" + (i+1+photos.size());
//            BlobId blobId = BlobId.of(bucketName, storagePath);
//            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
//            storage.createFrom(blobInfo, Paths.get(req.paths()[i]));
//            String googlePath = storage.get(blobId).getMediaLink();
//            photoRepository.save(Photo.builder().storagePath(storagePath).photoURL(googlePath).diary(diary).build());
//        }
//    }

//    public void insert(Long diaryId, MultipartFile[] files) throws IOException {
//        Diary diary = diaryService.getById(diaryId);
//        List<Photo> photos = photoRepository.findAllByDiary_Id(diaryId);
//        if (photos.size() + files.length > 5) throw new PhotoLimitExceededException();
//        // photo id를 알 때 google 에서 사진 정보를 어떻게 가져오지? blob Id도 저장을 해야할 거 같다. (ex. diary/1/image/1)
//        for (int i = 0; i < files.length; i++) {
//            MultipartFile file = files[i];
//            String storagePath = "posts/" + diary.getPost().getId() + "/diaries/" + diaryId + "/images/" + (i+1+photos.size());
//            BlobId blobId = BlobId.of(bucketName, storagePath);
//            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
//            try {
////                    storage.createFrom(blobInfo, Paths.get(el.paths()[i]));
//                InputStream inputStream = file.getInputStream();
//                storage.createFrom(blobInfo, inputStream);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            String googlePath = storage.get(blobId).getMediaLink();
//            photoRepository.save(Photo.builder().storagePath(storagePath).photoURL(googlePath).diary(diary).build());
//        }
//    };
//

//
//    @Override
//    public Photo getById(Long id) {
//        return photoRepository.findById(id).orElseThrow(PhotoNotFoundException::new);
//    }
//
//    @Override
//    public List<Photo> getByDiaryId(Long diaryId) {
//        return photoRepository.findAllByDiary_Id(diaryId);
//    }
//
//    @Override
//    @Transactional

//    public void update(Long id, String path) throws IOException {
//        Photo photo = photoRepository.findById(id).orElseThrow(PhotoNotFoundException::new);
//        BlobId blobId = BlobId.of(bucketName, photo.getStoragePath());
//        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
//        storage.createFrom(blobInfo, Paths.get(path));

//    public void update(Long id, MultipartFile file) throws IOException {
//        Photo photo = photoRepository.findById(id).orElseThrow(PhotoNotFoundException::new);
//        BlobId blobId = BlobId.of(bucketName, photo.getStoragePath());
//        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
//        try {
//            InputStream inputStream = file.getInputStream();
//            storage.createFrom(blobInfo, inputStream);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//        String googlePath = storage.get(blobId).getMediaLink();
//        photo.setPhotoURL(googlePath);
//    }
//
//    @Override
//    @Transactional
//    public void deleteById(Long id) {
//        Photo photo = photoRepository.findById(id).orElseThrow(PhotoNotFoundException::new);
//        BlobId blobId = BlobId.of(bucketName, photo.getStoragePath());
//        storage.delete(blobId);
//        photoRepository.deleteById(id);
//    }

//}
//

//}

