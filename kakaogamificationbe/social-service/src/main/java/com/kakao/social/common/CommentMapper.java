package com.kakao.social.common;

import com.kakao.social.entity.Comment;
import com.kakao.social.payload.CommentResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface CommentMapper extends EntityMapper<CommentResponse, Comment> {
}
