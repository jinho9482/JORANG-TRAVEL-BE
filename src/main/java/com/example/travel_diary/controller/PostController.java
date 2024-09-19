package com.example.travel_diary.controller;

import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.request.PostRequest;
import com.example.travel_diary.global.request.PostTempRequest;
import com.example.travel_diary.global.response.CountryCostDto;
import com.example.travel_diary.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final PostService postService;

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
////    @RolesAllowed("USER")
//    public Long createPost(@AuthenticationPrincipal User user) {
//        return postService.createPost(user);
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @RolesAllowed("USER")
    public Long createPost(@AuthenticationPrincipal User user, @RequestBody PostRequest req) {
        return postService.createPost(user, req);
    }

    @PostMapping("/temp")
    @ResponseStatus(HttpStatus.CREATED)
    public Long createTemporaryPost(@AuthenticationPrincipal User user, @RequestBody PostTempRequest req) {
        return postService.createTemporaryPost(user, req);
    }

    @PutMapping("/{id}/temp")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateTemporaryPost(@PathVariable(name = "id") Long id, @RequestBody PostTempRequest req) {
        postService.updateTemporaryPost(id, req);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(name = "id") Long id) {
        postService.deleteById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable(name = "id") Long id, @RequestBody PostRequest req) {
        postService.update(id, req);
    }

    @GetMapping
    public List<Post> getAll() {
        return postService.getAll();
    }

    @GetMapping("/public/{id}")
    public Post getById(@PathVariable(name = "id") Long id) {
        return postService.getById(id);
    }


    @GetMapping("/my/{id}")
    public Post getMyPostById(@AuthenticationPrincipal User user, @PathVariable(name = "id") Long id) {
        return postService.getMyPostById(user, id);
    }
    @GetMapping("/recent")
    public List<Post> getRecentPostsFirst() {
        return postService.getRecentPostsFirst();
    }

    @GetMapping("/top5/diaries")
    public List<Post> getRecent5PostsByCountry(@RequestParam(name = "country") String country) {
        return postService.getRecent5PostsByCountry(country);
    }

    @GetMapping("/recent/diaries")
    public List<Post> getRecentPostsFirstByCountry(@RequestParam(name = "country") String country) {
        return postService.getRecentPostsFirstByCountry(country);
    }

    @GetMapping("/top5/like")
    public List<Post> getTop5LikeOnThisWeek() {
        return postService.getTop5LikeOnThisWeek();
    }

    @GetMapping("/top5/recent")
    public List<Post> getTop5RecentPosts() {
        return postService.getTop5RecentPosts();
    }

    @GetMapping("/dates")
    public List<Post> getRecentPostsFirstBetweenTheseDates(@RequestParam(value = "from") LocalDate from,
                                      @RequestParam(value = "to") LocalDate to) {
        return postService.getRecentPostsFirstBetweenTheseDates(from, to);
    }

    @GetMapping("/user")
    public List<Post> getAllByUser(@AuthenticationPrincipal User user) {
        log.info("got into getAllByUser in controller");
        return postService.getAllByUser(user);
    }

    @GetMapping("/my-published/page")
    public Page<Post> getMyPublishedPostsPerPage(@AuthenticationPrincipal User user, @RequestParam(value="page", defaultValue="0") int page) {
        return postService.getMyPublishedPostsPerPage(user, page);
    }

    @GetMapping("/my-unpublished")
    public List<Post> getUnpublishedPosts(@AuthenticationPrincipal User user) {
        return postService.getUnpublishedPosts(user);
    }

    @GetMapping("/my-unpublished/page")
    public Page<Post> getUnpublishedPostsPerPage(@AuthenticationPrincipal User user, @RequestParam(value="page", defaultValue="0") int page) {
        return postService.getUnpublishedPostsPerPage(user, page);
    }


    @GetMapping("/my-published")
    public List<Post> getPublishedPosts(@AuthenticationPrincipal User user) {
        return postService.getPublishedPosts(user);
    }

    //    @GetMapping("/my/countries")
//    public Set<String> getCountriesFromMyPosts(@AuthenticationPrincipal User user) {
//        return postService.getCountriesFromMyPosts(user);
//    }
    @GetMapping("/my")
    public List<Post> getMyPosts(@AuthenticationPrincipal User user) {
        return postService.getMyPosts(user);
    }

    @GetMapping("/my-countries")
    public List<String> getNumberOfCountriesVisited(@AuthenticationPrincipal User user) {
        return postService.getNumberOfCountriesVisited(user);
    }

    @GetMapping("/my/total-cost")
    public List<CountryCostDto> getMyTotalCostPerCountry(@AuthenticationPrincipal User user) {
        return postService.getTotalCostPerCountry(user);
    }

    @GetMapping("/search")
    public List<Post> getPostsByKeyword(@RequestParam(value = "keyword") String keyword) {
        return postService.getPostsByKeyword(keyword);
    }
}
