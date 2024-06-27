package com.example.travel_diary.global.domain.repository;


//import com.example.travel_diary.global.domain.entity.Photo;

import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.domain.type.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByDiaries_Scope(Scope scope);

    @Query("SELECT p " +
            "FROM Post p " +
            "JOIN FETCH p.diaries d " +
            "WHERE d.scope = :scope " +
            "ORDER BY p.createdAt DESC")
    List<Post> findAllByDiaries_ScopeOrderByCreatedAtDesc(@Param("scope") Scope scope);

    @Query("SELECT p " +
            "FROM Post p " +
            "JOIN FETCH p.diaries d " +
            "WHERE d.scope = :scope " +
            "AND d.country = :country " +
            "ORDER BY p.createdAt DESC " +
            "LIMIT 5")
    List<Post> findTop5ByDiaries_ScopeAndDiaries_CountryOrderByCreatedAtDesc(@Param("scope") Scope scope,
                                                                             @Param("country") String country);


    @Query("SELECT p " +
            "FROM Post p " +
            "JOIN FETCH p.diaries d " +
            "WHERE d.scope = :scope " +
            "AND d.country = :country " +
            "ORDER BY p.createdAt DESC")
    List<Post> findAllByDiaries_ScopeAndDiaries_CountryOrderByCreatedAtDesc(@Param("scope") Scope scope,
                                                                            @Param("country")String country);
    @Query("SELECT p " +
            "FROM Post p " +
            "JOIN FETCH p.diaries d " +
            "WHERE d.scope = :scope " +
            "AND p.createdAt BETWEEN :startOfWeek AND :endOfWeek " +
//            "GROUP BY p.id " +
            "ORDER BY p.love DESC " +
            "LIMIT 5")
    List<Post> findTop5ByDiaries_ScopeAndCreatedAtBetweenOrderByLoveDesc(@Param("scope") Scope scope,
                                                                         @Param("startOfWeek") LocalDateTime startOfWeek,
                                                                         @Param("endOfWeek") LocalDateTime endOfWeek);
    List<Post> findAllByDiaries_ScopeAndDiaries_DateBetweenOrderByCreatedAtDesc(Scope scope, LocalDate from, LocalDate to);

    List<Post> findAllByUserOrderByCreatedAtDesc(User user);

    Page<Post> findAllByUser(User user, Pageable pageable);

    List<Post> findTop5ByDiaries_ScopeOrderByCreatedAtDesc(Scope scope);

}

