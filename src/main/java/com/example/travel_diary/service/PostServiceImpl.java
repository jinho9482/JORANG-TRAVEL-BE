package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.domain.repository.PostRepository;
import com.example.travel_diary.global.domain.type.Scope;
import com.example.travel_diary.global.exception.PostNotPublicException;
import com.example.travel_diary.global.exception.PostNotFoundException;
import com.example.travel_diary.global.request.PostRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    // 여행 일지 작성 누르면 바로 post id를 생성 시킴, 업데이트도 작성일자만 갱신
    @Override
    @Transactional
    public Long createPost(User user) {
        Post post = postRepository.save(Post.builder().user(user).build());
        return post.getId();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        postRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Long id, PostRequestDto req) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        post.setTitle(req.title());
        post.setCountry(req.country());
        post.setScope(req.scope());
        post.setCreatedAt(LocalDateTime.now());
        post.setPublished(true);
    }

    @Override
    public List<Post> getAll() {
        return postRepository.findAllByScopeAndIsPublished(Scope.PUBLIC, true);
    }

    @Override
    public Post getById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        if(!post.getScope().equals(Scope.PUBLIC)) throw new PostNotPublicException();
        return post;
    }

    @Override
    public Post getMyPostById(User user, Long id) {
        return postRepository.findById(id).orElseThrow(PostNotFoundException::new);
    }

    @Override
    public List<Post> getRecentPostsFirst() {
        return postRepository.findAllByScopeAndIsPublishedOrderByCreatedAtDesc(Scope.PUBLIC, true);
    }

    @Override
    public List<Post> getRecent5PostsByCountry(String country) {
        return postRepository.findTop5ByScopeAndCountryAndIsPublishedOrderByCreatedAtDesc(Scope.PUBLIC, country, true);
    }

    @Override
    public List<Post> getRecentPostsFirstByCountry(String country) {
        return postRepository.findAllByScopeAndCountryAndIsPublishedOrderByCreatedAtDesc(Scope.PUBLIC, country, true);
    }

    @Override
    public List<Post> getTop5LikeOnThisWeek() {
        LocalDateTime today = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDateTime endOfWeek = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        System.out.println(today);
        System.out.println(startOfWeek);
        System.out.println(endOfWeek);
        return postRepository.findTop5ByScopeAndIsPublishedAndCreatedAtBetweenOrderByLoveDesc(Scope.PUBLIC, true, startOfWeek, endOfWeek);
    }

    @Override
    public List<Post> getRecentPostsFirstBetweenTheseDates(LocalDate from, LocalDate to) {
        return postRepository.findAllByScopeAndIsPublishedAndCreatedAtBetweenOrderByCreatedAtDesc(Scope.PUBLIC, true, from, to);
    }

    @Override
    public List<Post> getAllByUser(User user) {
        return postRepository.findAllByUserOrderByCreatedAtDesc (user);
    }

    @Override
    public Page<Post> getList(User user, int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createdAt"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.postRepository.findAllByUser(user, pageable);
    }

    @Override
    public List<Post> getTop5RecentPosts() {
        return postRepository.findTop5ByScopeAndIsPublishedOrderByCreatedAtDesc(Scope.PUBLIC, true);
    }

    @Override
    public List<Post> getUnpublishedPosts() {
        return postRepository.findAllByIsPublishedOrderByCreatedAtDesc(false);
    }
}