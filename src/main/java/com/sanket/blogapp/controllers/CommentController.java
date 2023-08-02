package com.sanket.blogapp.controllers;

import com.sanket.blogapp.payloads.CommentDto;
import com.sanket.blogapp.services.CommentService;
import com.sanket.blogapp.util.ResponseObject;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CommentController {
    private CommentService commentService;


    @PostMapping("/user/{userId}/post/{postId}/comment")
    public ResponseEntity<Object> createComment(@RequestBody CommentDto commentDto,
                                                @PathVariable("userId") Long userId,
                                                @PathVariable("postId") Long postId
    ) {
        ResponseObject responseObject = this.commentService.createComment(commentDto, userId, postId);
        return new ResponseEntity<>(responseObject, responseObject.getHttpStatus());
    }

    @PutMapping("/user/{userId}/post/{postId}/comment/{commentId}")
    public ResponseEntity<Object> updateComment(@RequestBody CommentDto commentDto,
                                                @PathVariable Long userId,
                                                @PathVariable Long postId,
                                                @PathVariable Long commentId) {
        ResponseObject responseObject = this.commentService.updateComment(commentDto, userId, postId, commentId);
        return new ResponseEntity<>(responseObject, responseObject.getHttpStatus());

    }

    @DeleteMapping("/user/{userId}/post/{postId}/comment/{commentId}")
    public ResponseEntity<Object> deleteComment(@PathVariable Long userId,
                                                @PathVariable Long postId,
                                                @PathVariable Long commentId
    ) {
        ResponseObject responseObject = commentService.deleteComment(userId, postId, commentId);
        return new ResponseEntity<>(responseObject, responseObject.getHttpStatus());
    }

    @GetMapping("/user/{userId}/comments/")
    public ResponseEntity<Object> getCommentByUser(@PathVariable Long userId) {
        ResponseObject responseObject = commentService.getCommentByUser(userId);
        return new ResponseEntity<>(responseObject, responseObject.getHttpStatus());
    }

    @GetMapping("/post/{postId}/")
    public ResponseEntity<Object> getCommentByPost(@PathVariable Long postId) {
        ResponseObject responseObject = commentService.getCommentByPost(postId);
        return new ResponseEntity<>(responseObject, responseObject.getHttpStatus());
    }
}
