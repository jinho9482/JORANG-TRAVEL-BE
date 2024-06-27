package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Like;
import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.response.LikeResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LikeService {

    // 좋아요 누르기 (user id와 post id를 가지고 get 하기, 없으면 생성, 있으면 삭제), user는 인증된 @AuthenticationPrincipal 사용
    int likeComment(User user, Long postId);

    // 특정 user가 좋아요 누른 post id 목록 뽑기 (찜한 목록에 사용)
    List<LikeResponse> getPosts(User user);

    // 특정 post 에 눌린 좋아요 수 뽑기
    Long countLike(Long postId);

    Boolean checkLike(User user, long postId);

    Page<LikeResponse> getList(User user, int page);
}
