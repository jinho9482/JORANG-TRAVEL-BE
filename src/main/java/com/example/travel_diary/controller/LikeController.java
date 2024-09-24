package com.example.travel_diary.controller;

import com.example.travel_diary.global.domain.entity.Like;
import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.response.LikePageResponse;
import com.example.travel_diary.global.response.LikeResponse;
import com.example.travel_diary.service.LikeService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "여행 일지 좋아요 생성 및 해제")
    public int likePost(@AuthenticationPrincipal User user, @PathVariable(name = "postId") Long postId) {
        return likeService.likePost(user, postId);
    }

    @GetMapping("/posts/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "내가 특정 여행 일지에 좋아요를 눌렀는지 확인")
    public boolean checkLike(@AuthenticationPrincipal User user, @PathVariable(name = "postId") Long postId) {
        return likeService.checkLike(user, postId);
    }

    @GetMapping("/posts/user/page")
    @Operation(summary = "내가 좋아요를 누른 여행 일지 페이지별로 가져오기")
    public LikePageResponse getLikedPostsByUserPerPage(@AuthenticationPrincipal User user, @RequestParam(value="page", defaultValue="0") int page) {
        return likeService.getLikedPostsByUserPerPage(user, page);
    }
}
