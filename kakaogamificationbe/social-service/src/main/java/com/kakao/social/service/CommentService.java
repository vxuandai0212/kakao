package com.kakao.social.service;

import com.kakao.social.entity.Comment;
import com.kakao.social.payload.CommentResponse;
import org.springframework.data.domain.Page;

public interface CommentService {

    Page<Comment> getComments(Integer offset, Integer limit, String sort, String sortBy);

    CommentResponse getCommentById(Long id);

    CommentResponse createComment(CommentResponse commentResponse);

    CommentResponse updateComment(Long commentId, CommentResponse commentResponse);

    String deleteComment(Long commentId);
}
