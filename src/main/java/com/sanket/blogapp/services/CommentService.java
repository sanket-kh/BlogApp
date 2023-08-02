package com.sanket.blogapp.services;

import com.sanket.blogapp.payloads.CommentDto;
import com.sanket.blogapp.util.ResponseObject;


public interface CommentService {
    ResponseObject createComment(CommentDto commentDto, Long userId, Long postId);
    ResponseObject updateComment(CommentDto commentDto,Long userId, Long postId, Long id);
    ResponseObject deleteComment(Long userId, Long postId, Long id);
    ResponseObject getCommentById(Long id);
    ResponseObject getCommentByUser(Long userId);
    ResponseObject getCommentByPost(Long postId);

}
