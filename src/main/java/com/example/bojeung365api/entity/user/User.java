package com.example.bojeung365api.entity.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "user_entity")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId")
    private Long id;

    @Column(nullable = false, unique = true)
    private String username; // front id

    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public User(String username, String password, String email, String nickname, UserRole role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.role = role;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

}
