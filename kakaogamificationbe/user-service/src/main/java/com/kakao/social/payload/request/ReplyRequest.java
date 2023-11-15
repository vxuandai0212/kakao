package com.kakao.social.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReplyRequest {

    private String replyBody;

    private boolean isReplyable = false;

    private boolean isDeleted = false;

    private int liked;

    private int disliked;

    private Long parentId;

    private String createBy;

    private Date createDate = new Date();
}
