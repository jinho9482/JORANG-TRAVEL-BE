package com.example.travel_diary.controller;

import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.request.PostRequest;
import com.example.travel_diary.global.request.PostTempRequest;
import com.example.travel_diary.global.response.CountryCostDto;
import com.example.travel_diary.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "여행 일지")
public class PostController {
    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "여행 일지 발행")
    public Long createPost(@AuthenticationPrincipal User user, @RequestBody PostRequest req) {
        return postService.createPost(user, req);
    }

    @PostMapping("/temp")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "여행 일지 임시 저장")
    public Long createTemporaryPost(@AuthenticationPrincipal User user, @RequestBody PostTempRequest req) {
        return postService.createTemporaryPost(user, req);
    }

    @PutMapping("/{id}/temp")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "저장된 여행 일지 업데이트")
    public void updateTemporaryPost(@PathVariable(name = "id") Long id, @RequestBody PostTempRequest req) {
        postService.updateTemporaryPost(id, req);
    }
    @PutMapping("/{id}")
    @Operation(summary = "저장된 여행 일지 발행")
    public void updatePost(@PathVariable(name = "id") Long id, @RequestBody PostRequest req) {
        postService.updatePost(id, req);
    }

    @GetMapping("/my-unpublished")
    @Operation(summary = "작성 중인 여행 일지 가져오기")
    public List<Post> getUnpublishedPosts(@AuthenticationPrincipal User user) {
        return postService.getUnpublishedPosts(user);
    }

    @GetMapping("/my/total-cost")
    @Operation(summary = "나라별 지출 총액 가져오기")
    public List<CountryCostDto> getMyTotalCostPerCountry(@AuthenticationPrincipal User user) {
        return postService.getTotalCostPerCountry(user);
    }

    @GetMapping("/search")
    @Operation(summary = "검색 키워드를 포함한 여행 일지 가져오기", description = "여행 일지 제목, 여행기 제목 및 내용, 여행 경비 지출 장소를 기준으로 검색한다")
    public List<Post> getPostsByKeyword(@RequestParam(value = "keyword") String keyword) {
        return postService.getPostsByKeyword(keyword);
    }

    @GetMapping("/public/{id}")
    @Operation(summary = "ID로 여행 일지 가져오기", description = "공개 여행 일지만 가져온다")
    public Post getById(@PathVariable(name = "id") Long id) {
        return postService.getById(id);
    }

    @GetMapping("/my/{id}")
    @Operation(summary = "ID로 나의 여행 일지 가져오기", description = "비공개 여행 일지를 포함해서 가져온다")
    public Post getMyPostById(@AuthenticationPrincipal User user, @PathVariable(name = "id") Long id) {
        return postService.getMyPostById(user, id);
    }

    @GetMapping("/user")
    @Operation(summary = "내 여행 일지 모두 가져오기")
    public List<Post> getAllByUser(@AuthenticationPrincipal User user) {
        log.info("got into getAllByUser in controller");
        return postService.getAllByUser(user);
    }

    @GetMapping("/my-published")
    @Operation(summary = "내가 발행한 여행 일지 가져오기")
    public List<Post> getPublishedPosts(@AuthenticationPrincipal User user) {
        return postService.getPublishedPosts(user);
    }

    @GetMapping("/my-published/page")
    @Operation(summary = "내가 발행한 여행 일지를 페이지 별로 가져오기")
    public Page<Post> getMyPublishedPostsPerPage(@AuthenticationPrincipal User user, @RequestParam(value="page", defaultValue="0") int page) {
        return postService.getMyPublishedPostsPerPage(user, page);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "ID로 여행 일지 삭제하기")
    public void deleteById(@PathVariable(name = "id") Long id) {
        postService.deleteById(id);
    }

    @GetMapping("/top5/diaries")
    @Operation(summary = "특정 나라의 여행 일지를 최신순으로 가져오기")
    public List<Post> getRecent5PostsByCountry(@RequestParam(name = "country") String country) {
        return postService.getRecent5PostsByCountry(country);
    }

    @GetMapping("/recent")
    @Operation(summary = "여행 일지를 최신순으로 가져오기")
    public List<Post> getRecentPostsFirst() {
        return postService.getRecentPostsFirst();
    }

    @GetMapping("/top5/recent")
    @Operation(summary = "가장 최근에 작성된 여행 일지 5개 가져오기")
    public List<Post> getTop5RecentPosts() {
        return postService.getTop5RecentPosts();
    }

    @GetMapping("/top5/like")
    @Operation(summary = "이번 주에 작성된 여행 일지 중 좋아요가 가장 많은 5개 가져오기")
    public List<Post> getTop5LikeOnThisWeek() {
        return postService.getTop5LikeOnThisWeek();
    }

    @GetMapping("/my-countries")
    @Operation(summary = "내가 방문한 나라 목록 가져오기", description = "여행 일지를 발행할 때 선택한 나라를 기준으로 가져온다")
    public List<String> getCountriesVisited(@AuthenticationPrincipal User user) {
        return postService.getCountriesVisited(user);
    }

    @GetMapping("/recent/diaries")
    @Operation(summary = "특정 나라의 여행 일지 최신순으로 가져오기")
    public List<Post> getRecentPostsFirstByCountry(@RequestParam(name = "country") String country) {
        return postService.getRecentPostsFirstByCountry(country);
    }

    @GetMapping("/my-unpublished/page")
    @Operation(summary = "작성 중인 여행 일지 페이지 별로 가져오기")
    public Page<Post> getUnpublishedPostsPerPage(@AuthenticationPrincipal User user, @RequestParam(value="page", defaultValue="0") int page) {
        return postService.getUnpublishedPostsPerPage(user, page);
    }
}
