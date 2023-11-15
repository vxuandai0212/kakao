package com.kakao.social.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String commentBody;

    @Column
    private boolean isReplied = false;

    @Column
    private boolean isReplyable = true;

    @Column
    private boolean isDeleted = false;

    @Column
    private int liked;

    @Column
    private int disliked;

    @Column
    private String createBy;

    @Column
    private Date createDate;

}