package com.example.travel_diary.controller;

import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.service.PostService;
import com.example.travel_diary.service.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @RolesAllowed("USER")
    public Long createPost(@AuthenticationPrincipal User user) {
        return postService.createPost(user);
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(name = "id") Long id) {
        postService.deleteById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable(name = "id") Long id, @RequestBody String title) {
        postService.update(id, title);
    }

    @GetMapping()
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
        return postService.getAllByUser(user);
    }

    @GetMapping("/user/list")
    public Page<Post> list(@AuthenticationPrincipal User user, @RequestParam(value="page", defaultValue="0") int page) {
        return this.postService.getList(user, page);
    }

//    @GetMapping("/search/diary/{word}")
//    public Page<Post> getSearchInDiary(@PathVariable(name = "word") String word, @RequestParam(value="page", defaultValue="0") int page) {
//        return postService.getSearchInDiary(word, page);
//    }
//
//    @GetMapping("/search/expense-detail/{word}")
//    public Page<Post> getSearchInExpenseDetail(@PathVariable(name = "word") String word, @RequestParam(value="page", defaultValue="0") int page) {
//        return postService.getSearchInExpenseDetail(word, page);
//    }

}
