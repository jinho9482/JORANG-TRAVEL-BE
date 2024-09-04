package com.example.travel_diary.global.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EXPENSES")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EXPENSE_ID")
    private Long id;
// 타이틀
    @Column( name = "DATE")
    @Setter
    private LocalDate date;


    @JsonBackReference
    @JoinColumn (name = "POST_ID")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Post post;


    @JsonManagedReference
    @OneToMany(mappedBy = "expense",cascade = CascadeType.ALL)
    private List<ExpenseDetail> expenseDetails;
    //set 지우고 /
    // enum scope

}
