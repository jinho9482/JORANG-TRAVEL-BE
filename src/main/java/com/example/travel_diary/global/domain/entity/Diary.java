package com.example.travel_diary.global.domain.entity;

import com.example.travel_diary.global.domain.type.Scope;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import java.time.LocalDateTime;
import java.util.List;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DIARIES")
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DIARY_ID")
    private Long id;

    @Column(name = "TITLE")
    @Setter
    private String title;

    @Column(name = "CONTENT")
    @Setter
    private String content;

    @Column(name = "DATE")
    @Setter
    private LocalDate date;

    @Column(name = "SCOPE")
    @Setter
    @Enumerated(EnumType.STRING)
    private Scope scope;

    @Column(name = "CREATED_AT")
    @Setter
    private LocalDateTime createdAt;

    @Column(name = "COUNTRY")
    @Setter
    private String country;

    @JsonBackReference
    @JoinColumn(name = "POST_ID")
    @ManyToOne
    private Post post;

    @JsonManagedReference
    @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL)
    private List<Photo> photos;
}
