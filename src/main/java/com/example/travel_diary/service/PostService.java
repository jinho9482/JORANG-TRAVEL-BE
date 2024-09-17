package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.request.PostRequest;
import com.example.travel_diary.global.request.PostTempRequest;
import com.example.travel_diary.global.response.CountryCostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface PostService {
    // 생성
//    Long createPost(User user);

    Long createPost(User user, PostRequest req);
    Long createTemporaryPost(User user, PostTempRequest req);
    void updateTemporaryPost(Long id, PostTempRequest req);

    // 삭제
    void deleteById(Long id);
    void update(Long id, PostRequest req);
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

    Page<Post> getMyPublishedPostsPerPage(User user, int page);

    List<Post> getTop5RecentPosts();
    List<Post> getUnpublishedPosts(User user);
    Page<Post> getUnpublishedPostsPerPage(User user, int page);
    List<Post> getPublishedPosts(User user);
    Post getMyPostById(User user, Long id);
//    Set<String> getCountriesFromMyPosts(User user);
    List<Post> getMyPosts(User user);
    List<String> getNumberOfCountriesVisited(User user);

    List<CountryCostDto> getTotalCostPerCountry(User user);
}
