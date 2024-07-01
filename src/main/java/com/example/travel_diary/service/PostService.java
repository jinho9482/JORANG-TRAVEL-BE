package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.entity.User;
import org.springframework.data.domain.Page;


import java.time.LocalDate;
import java.util.List;

public interface PostService {
    // 생성
    Long createPost(User user);
    // 삭제
    void deleteById(Long id);
    void update(Long id, String title);
    // 전부 가져오기
    List<Post> getAll();
    // 한 개 가져오기
    Post getById(Long id);
    List<Post> getRecentPostsFirst();
    List<Post> getRecentPostsFirstByCountry(String country);

    List<Post> getRecent5PostsByCountry(String country);

    List<Post> getTop5LikeOnThisWeek();
    List<Post> getRecentPostsFirstBetweenTheseDates(LocalDate from, LocalDate to);
    List<Post> getAllByUser(User user);

    Page<Post> getList(User user, int page);

    List<Post> getTop5RecentPosts();

    Post getMyPostById(User user, Long id);
//
//    Page<Post> getSearchInDiary(String word, int page);
//
//    Page<Post> getSearchInExpenseDetail(String word, int page);

}
