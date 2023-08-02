package com.sanket.blogapp.repositories;

import com.sanket.blogapp.entities.Comment;
import com.sanket.blogapp.entities.Post;
import com.sanket.blogapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByUser(User user);

    List<Comment> findCommentsByPost(Post post);

    Comment findCommentByUserAndPost(Long userId, Long postId);


}
