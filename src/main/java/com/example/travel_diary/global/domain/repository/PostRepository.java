package com.example.travel_diary.global.domain.repository;


import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.domain.type.Scope;
import com.example.travel_diary.global.response.CountryCostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByScope(Scope scope);
    List<Post> findAllByScopeAndIsPublished(Scope scope, boolean isPublished);

    List<Post> findAllByIsPublishedAndUserOrderByCreatedAtDesc(boolean isPublished, User user);
    List<Post> findAllByScopeAndIsPublishedOrderByCreatedAtDesc(Scope scope, boolean isPublished);
    List<Post> findTop5ByScopeAndCountryAndIsPublishedOrderByCreatedAtDesc(Scope scope, String country, boolean isPublished);
    List<Post> findAllByScopeAndCountryAndIsPublishedOrderByCreatedAtDesc(Scope scope, String country, boolean isPublished);
    List<Post> findTop5ByScopeAndIsPublishedAndCreatedAtBetweenOrderByLoveDesc(Scope scope, boolean isPublished, LocalDateTime startOfWeek, LocalDateTime endOfWeek);
    List<Post> findAllByScopeAndIsPublishedAndCreatedAtBetweenOrderByCreatedAtDesc(Scope scope, boolean isPublished, LocalDate from, LocalDate to);
    List<Post> findTop5ByScopeAndIsPublishedOrderByCreatedAtDesc(Scope scope, boolean isPublished);
    List<Post> findAllByUserOrderByCreatedAtDesc(User user);
    Page<Post> findAllByIsPublishedAndUser(boolean isPublished, User user, Pageable pageable);
    List<Post> findAllByUser(User user);
    List<Post> findAllByIsPublishedOrderByCreatedAtDesc(boolean isPublished);
    @Query("SELECT country FROM Post WHERE user = :user AND isPublished = true GROUP BY country")
    List<String> findMyCountry(@Param("user") User user);

    @Query(value = "SELECT p.country, SUM(d.cost) AS totalCost FROM posts p " +
            "INNER JOIN expenses e ON e.post_id = p.post_id " +
            "INNER JOIN expense_details d ON d.expense_id = e.expense_id " +
            "WHERE p.is_published = true " +
            "AND p.user_id = :userId " +
            "GROUP BY p.country", nativeQuery = true)
    List<Object[]> findTotalCostPerCountry(@Param("userId") UUID userId);
}


