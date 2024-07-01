//package com.example.travel_diary.controller;
//
//import com.example.travel_diary.global.domain.entity.Photo;
//import com.example.travel_diary.global.request.PhotoRequestDto;
//
//import com.example.travel_diary.service.PhotoService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;

//
//import java.io.IOException;

//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.Arrays;

//import java.util.List;
//
//@RestController
//@RequestMapping("api/v1/photos")
//@RequiredArgsConstructor
//public class PhotoController {
//    private final PhotoService photoService;
//
//

//    @PostMapping("/diary/{diaryId}")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void insert(@RequestBody PhotoRequestDto req, @PathVariable(name = "diaryId") Long diaryId) throws IOException {
//        photoService.insert(req, diaryId);

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void insert(@RequestParam(name = "diaryId") Long diaryId, @RequestParam(name = "file") MultipartFile[] files) throws IOException {
//        System.out.println(diaryId);
//        for (MultipartFile file : files) System.out.println(file);
//        photoService.insert(diaryId, files);

//    }
//
//    @GetMapping("/{id}")
//    public Photo getById(@PathVariable(name = "id") Long id) {
//        return photoService.getById(id);
//    }
//
//    @GetMapping("/diaries/{diaryId}")
//    public List<Photo> getByDiaryId(@PathVariable(name = "diaryId") Long diaryId) {
//        return photoService.getByDiaryId(diaryId);
//    }
//

//    @PutMapping("/{id}")
//    public void update(@PathVariable(name = "id") Long id, @RequestBody String path) throws IOException {
//        photoService.update(id, path);

//    @PutMapping
//    public void update(@RequestParam(name = "id") Long id, @RequestParam(name = "file") MultipartFile file) throws IOException {
//        photoService.update(id, file);

//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteById(@PathVariable(name = "id") Long id) {
//        photoService.deleteById(id);
//    }

//}
//

//}

