package com.kakao.social.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.social.entity.Comment;
import com.kakao.social.payload.CommentResponse;
import com.kakao.social.repository.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @PersistenceContext
    private EntityManager entityManager;

    private final CommentRepository commentRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();


    private CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Page<Comment> getComments(Integer offset, Integer limit, String sort, String sortBy) {
        if (offset == null) {
            offset = 0;
        }

        if (limit == null) {
            limit = 10;
        }

        Sort.Direction direction = Sort.Direction.DESC;
        if (sort != null) {
            if (sort.equals("asc")) {
                direction = Sort.Direction.ASC;
            } else {
                sort = "desc";
            }
        }

        if (sortBy.isEmpty()) {
            sortBy = "create_date";
        }

        PageRequest pageRequest = PageRequest.of(offset, limit, direction, sortBy);

        Page<Comment> comments = null;

        StringBuilder sqlLimit = new StringBuilder();
        if (sort != null && !sort.equalsIgnoreCase("asc")) {
            sort = "desc";
        } else {
            sort = "asc";
        }

        if (!sortBy.isEmpty()) {
            sqlLimit.append(" order by ");
            sqlLimit.append(sortBy);
            sqlLimit.append(" ");
            sqlLimit.append(sort);
        }

        StringBuilder sqlQueryBuilder = new StringBuilder("SELECT * FROM comments c WHERE 1=1 ");
        sqlQueryBuilder.append(sqlLimit);

        StringBuilder sqlCountQueryBuilder = new StringBuilder("SELECT count(*) FROM comments c WHERE 1=1 ");

        Query query = entityManager.createNativeQuery(sqlQueryBuilder.toString(), Comment.class);
        query.setMaxResults(limit);
        query.setFirstResult(offset);
        Query countQuery = entityManager.createNativeQuery(sqlCountQueryBuilder.toString());

        BigInteger total = (BigInteger) countQuery.getSingleResult();
        return new PageImpl<Comment>(query.getResultList(), pageRequest, total.longValue());
    }


    @Override
    public CommentResponse getCommentById(Long id) {
        return objectMapper.convertValue(commentRepository.getById(id), CommentResponse.class);
    }

    @Override
    public CommentResponse createComment(CommentResponse commentResponse) {
        Comment comment = commentRepository.save(objectMapper.convertValue(commentResponse, Comment.class));
        return objectMapper.convertValue(comment, CommentResponse.class);
    }

    @Override
    public CommentResponse updateComment(Long commentId, CommentResponse commentResponse) {
        Optional<Comment> commentOps = commentRepository.findById(commentId);
        Comment comment = new Comment();
        if (commentOps.isPresent()) comment = commentOps.get();
        comment.setCommentBody(commentResponse.getCommentBody());
        comment.setLiked(commentResponse.getLiked());
        comment.setDisliked(commentResponse.getDisliked());
        comment.setReplied(commentResponse.isReplied());
        comment.setDeleted(commentResponse.isDeleted());
        comment = commentRepository.save(objectMapper.convertValue(commentResponse, Comment.class));
        return objectMapper.convertValue(comment, CommentResponse.class);
    }

    @Override
    public String deleteComment(Long commentId) {
        Optional<Comment> commentOps = commentRepository.findById(commentId);
        Comment comment = new Comment();
        if (commentOps.isPresent()) comment = commentOps.get();
        commentRepository.delete(comment);
        return "Comment deleted successfully!";
    }
}
