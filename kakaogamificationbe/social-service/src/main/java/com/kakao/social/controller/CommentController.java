package com.kakao.social.controller;

import com.kakao.social.entity.Comment;
import com.kakao.social.payload.CommentResponse;
import com.kakao.social.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<Page<Comment>> getComments(@RequestParam(value = "offset", required = false) Integer offset,
        @RequestParam(value = "limit", required = false) Integer limit,
        @RequestParam(value = "sort", required = false) String sort,
        @RequestParam(value = "sortBy", required = false) String sortBy) {
        return ResponseEntity.ok(commentService.getComments(offset, limit, sort, sortBy));
    }

    @PostMapping()
    public ResponseEntity<CommentResponse> createComments(@RequestBody CommentResponse commentResponse) {
        return ResponseEntity.ok(commentService.createComment(commentResponse));
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentResponse> patchComments(@PathVariable Long commentId,
        @RequestBody CommentResponse commentResponse) {
        return ResponseEntity.ok(commentService.updateComment(commentId, commentResponse));
    }

    @DeleteMapping()
    public ResponseEntity<String> patchComments(@PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.deleteComment(commentId));
    }
}
