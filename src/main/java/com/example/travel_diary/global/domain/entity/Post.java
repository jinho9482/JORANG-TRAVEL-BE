package com.example.travel_diary.global.domain.entity;

import com.example.travel_diary.global.domain.type.Scope;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long id;

    @Column(name = "TITLE")
    @Setter
    private String title;

    @Column(name = "CREATED_AT")
    @Setter
    private LocalDateTime createdAt;

    @Column(name = "LOVE")
    @Setter
    private int love;

    @Column(name = "COUNTRY")
    @Setter
    private String country;

    @Column(name = "SCOPE")
    @Setter
    private Scope scope;

    @Column(name = "IS_PUBLISHED")
    @Setter
    private boolean isPublished;

    @JsonManagedReference
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Like> likes;

    @JsonManagedReference
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Diary> diaries;

    @JsonManagedReference
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Expense> expenses;

    @JsonBackReference
    @JoinColumn(name = "USER_ID")
    @ManyToOne
    private User user;
}
