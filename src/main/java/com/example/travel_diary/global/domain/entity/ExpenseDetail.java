package com.example.travel_diary.global.domain.entity;

import com.example.travel_diary.global.request.ExpenseDetailRequest;
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
    private int cost;

    @Column(name = "PLACE")
    private String place;

    @Column(name = "CATEGORY", nullable = false)
    private String category;

    @Column(name = "CREATED_AT")
    @Setter
    private LocalDateTime createdAt;

    @JsonBackReference
    @JoinColumn(name = "EXPENSE_ID")
    @ManyToOne
    private Expense expense;

    public void update(ExpenseDetailRequest req) {
        if (req.cost() != this.cost) this.cost = req.cost();
        if (req.place().equals(this.place)) this.place = req.place();
        if (req.category().equals(this.category)) this.category = req.category();
    }
}
