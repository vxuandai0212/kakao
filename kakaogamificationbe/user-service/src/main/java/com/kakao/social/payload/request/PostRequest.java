package com.kakao.social.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PostRequest {

    private String postBody;

    private String attachImgUrl;

    private String createBy;

    private Date createDate = new Date();
}
