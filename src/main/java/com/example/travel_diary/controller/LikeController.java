package com.example.travel_diary.controller;

import com.example.travel_diary.global.domain.entity.Like;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.response.LikeResponse;
import com.example.travel_diary.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/posts/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    public int likeComment(@AuthenticationPrincipal User user, @PathVariable(name = "postId") Long postId) {
//        if(user.) throw new IllegalArgumentException("로그인 필요");
        return likeService.likeComment(user, postId);
    }

    @GetMapping("/posts")
    public List<LikeResponse> getPosts(@AuthenticationPrincipal User user) {
        return likeService.getPosts(user);
    }

    @GetMapping("/posts/{postId}/count")
    public Long countLike(@PathVariable(name = "postId") Long postId) {
        return likeService.countLike(postId);
    }

    @GetMapping("/posts/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean checkLike(@AuthenticationPrincipal User user, @PathVariable(name = "postId") Long postId) {
        return likeService.checkLike(user, postId);
    }

    @GetMapping("/user/list")
    @ResponseStatus(HttpStatus.CREATED)
    public Page<LikeResponse> list(@AuthenticationPrincipal User user,  @RequestParam(value="page", defaultValue="0") int page) {
        return likeService.getList(user, page);
    }


}
