//package com.example.travel_diary.service;
//
//import com.example.travel_diary.global.domain.entity.Photo;
//import com.example.travel_diary.global.request.PhotoRequestDto;
//import org.springframework.web.multipart.MultipartFile;
//
//
//import java.io.IOException;
//import java.util.List;
//
//public interface PhotoService {
//    // 저장
//    void insert(Long diaryId, MultipartFile[] files) throws IOException;
//    Photo getById(Long id);
//    // 여행기별 사진 가져오기
//    List<Photo> getByDiaryId(Long diaryId);
//    // 사진 수정
//    void update(Long id, MultipartFile file) throws IOException;
//    // 삭제
//    void deleteById(Long id);
//}