package com.example.travel_diary.global.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PHOTOS")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PHOTO_ID")
    private Long id;

    @Column(name = "STORAGE_PATH")
    @Setter
    private String storagePath;

    @Column(name = "PHOTO_URL")
    @Setter
    private String photoURL;

    @JsonBackReference
    @JoinColumn(name = "DIARY_ID")
    @ManyToOne
    private Diary diary;
}
