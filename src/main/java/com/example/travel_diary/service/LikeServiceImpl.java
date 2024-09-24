package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Like;
import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.domain.repository.LikeRepository;
import com.example.travel_diary.global.response.LikePageResponse;
import com.example.travel_diary.global.response.LikeResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final PostService postService;

    @Override
    @Transactional
    public int likePost(User user, Long postId) {
        Optional<Like> like = likeRepository.findByUser_IdAndPost_Id(user.getId(), postId);
        Post post = postService.getById(postId);
        if (like.isEmpty()) {
            likeRepository.save(Like.builder().user(user).post(post).build());
            // 좋아요를 누른 후 post의 좋아요 수 +1
            post.setLove(post.getLove()+1);
            return +1; // front에서 return 값을 받고 좋아요 수 수정하기
        } else {
            likeRepository.deleteByUser_IdAndPost_Id(user.getId(), postId);
            // 좋아요를 해제 후 post의 좋아요 수 -1
            post.setLove(post.getLove()-1);
            return -1;
        }
    }
    @Override
    public Boolean checkLike(User user, long postId) {
        Optional<Like> like = likeRepository.findByUser_IdAndPost_Id(user.getId(), postId);
        return like.isPresent();
    }

    @Override
    public LikePageResponse getLikedPostsByUserPerPage(User user, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Like> likes = likeRepository.findAllByUser(user, pageable);
        int totalPage = likes.getTotalPages();

        List<Long> postIds = likeRepository.findByUserPerPage(user.getId(), page * 10);
        log.info(postIds.toString());
        List<Post> posts = postIds.stream().map((postId) -> {
            Post post = postService.getById(postId);
            return post;
        }).toList();
        log.info(posts.toString());
        LikePageResponse pageResponses = new LikePageResponse(totalPage, posts);
//        List<LikeResponse> res = likes.stream().map(LikeResponse::from).toList();
        return pageResponses;
    }
}
