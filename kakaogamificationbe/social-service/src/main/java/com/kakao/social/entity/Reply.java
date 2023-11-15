package com.kakao.social.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "replies")
@Data
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String replyBody;

    @Column
    private boolean isReplyable;

    @Column
    private boolean isDeleted;

    @Column
    private int liked;

    @Column
    private int disliked;

    @Column
    private Long parentId;

    @Column
    private String createBy;

    @Column
    private Date createDate;

}