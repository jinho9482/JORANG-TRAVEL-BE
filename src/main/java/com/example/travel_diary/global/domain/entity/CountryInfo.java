package com.example.travel_diary.global.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "COUNTRYINFO")
public class CountryInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DIARY_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Lob
    @Column(name = "INFO", columnDefinition = "LONGTEXT")
    private String info;
}