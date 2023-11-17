package com.supperbit.authentication.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.supperbit.authentication.auth.enums.AuthProvider;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
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