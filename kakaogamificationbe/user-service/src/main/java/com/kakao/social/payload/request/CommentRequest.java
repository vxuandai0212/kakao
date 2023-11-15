package com.kakao.social.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommentRequest {

    private String commentBody;

    private boolean isReplied = false;

    private boolean isReplyable = true;

    private boolean isDeleted = false;

    private int liked;

    private int disliked;

    private String createBy;

    private Date createDate;
}
