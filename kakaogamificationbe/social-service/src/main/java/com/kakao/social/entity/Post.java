package com.kakao.social.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posts")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String postBody;

    @Column
    private String attachImgUrl;

    @Column
    private String createBy;

    @Column
    private Date createDate;

}