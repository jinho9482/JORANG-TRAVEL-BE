package com.example.travel_diary.global.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EXPENSE_DETAILS")
public class ExpenseDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EXPENSE_DETAIL_ID")
    private Long id;

    @Column(name = "COST")
    @Setter
    private int cost;

    @Column(name = "PLACE")
    @Setter
    private String place;

    @Column(name = "CATEGORY", nullable = false)
    @Setter
    private String category;

    @Column(name = "SCOPE", nullable = false)
    @Setter
    private String scope;

    @Column(name = "CREATED_AT")
    @Setter
    private LocalDateTime createdAt;

    @Column(name = "COUNTRY")
    @Setter
    private String country;

    @JsonBackReference
    @JoinColumn(name = "EXPENSE_ID")
    @ManyToOne
    private Expense expense;
}
