package com.example.travel_diary.global.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USERS")
public class User implements UserDetails {
    @Id
    @Column(name = "USER_ID")
    private UUID id;
    @Column(name = "USER_LOGIN_ID", unique = true)
    private String loginId;
    @Column(name = "USER_NAME")
    private String name;
    @Column(name = "USER_NICKNAME") @Setter
    private String nickname;
    @Column(name = "USER_PASSWORD") @Setter
    private String password;
    @Column(name = "USER_DATE_OF_BIRTH")
    private LocalDate dateOfBirth;
    @Column(name = "USER_EMAIL", unique = true)
    private String email;
    @Column(name = "USER_CREATED_AT")
    private LocalDateTime createdAt;
    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(()->"ROLE_USER");
    }

    @Override
    public String getUsername() {
        return loginId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
