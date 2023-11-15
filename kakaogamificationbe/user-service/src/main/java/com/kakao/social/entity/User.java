package com.kakao.social.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kakao.enums.AuthProvider;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String accessKey;

    @Column
    private String imageUrl;

    private String providerId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    @Column
    private String username;

    @Column
    @JsonIgnore
    private String password;
}