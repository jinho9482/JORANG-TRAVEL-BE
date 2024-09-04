package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Like;
import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.domain.repository.LikeRepository;
import com.example.travel_diary.global.response.LikeResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final PostService postService;

    @Override
    @Transactional
    public int likeComment(User user, Long postId) {
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

//    @Override
//    public List<LikeResponse> getPosts(User user) {
//        List<Like> likes = likeRepository.findAllByUser(user);
//        if (likes.isEmpty()) throw new IllegalArgumentException("좋아요 한 포스트가 존재하지 않습니다.");
//        return likes.stream().map(LikeResponse::from).toList();
//    }

    @Override
    public List<LikeResponse> getPosts(User user) {
        List<Like> likes = likeRepository.findAllByUserOrderByIdDesc(user);
        if (likes.isEmpty()) return null;
        return likes.stream().map(LikeResponse::from).toList();
    }

    @Override
    public Long countLike(Long postId) {
        return likeRepository.countByPost_Id(postId);
    }

    @Override
    public Boolean checkLike(User user, long postId) {
        Optional<Like> like = likeRepository.findByUser_IdAndPost_Id(user.getId(), postId);
        return like.isPresent();
    }


}
